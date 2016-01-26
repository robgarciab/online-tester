describe("Online tester", function() {

	beforeEach(module('app'));
	var $httpBackend, $controller, $http;
	beforeEach(inject(function($injector) {
		$httpBackend = $injector.get('$httpBackend');
		$controller = $injector.get('$controller');
		$http = $injector.get('$http');
	}));
	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	describe("Default headers", function() {

		it("should include X-Requested-With", function() {
			var $scope = {};
			$httpBackend.expectGET('', function(headers) {
				expect(headers['X-Requested-With']).toEqual('XMLHttpRequest');
				return true;
			}).respond(200);
			$http.get('')
			$httpBackend.flush();
		});

	});

	describe("Navigation Controller", function() {

		var controller;
		var $scope = {};
		var $rootScope = {};

		beforeEach(inject(function($injector) {
			$httpBackend.expectGET('/user').respond(401);
			$httpBackend.expectGET('home.html').respond(200);
			controller = $controller('navigation', {
				$scope : $scope,
				$rootScope : $rootScope
			});
			$httpBackend.flush();
		}));

		it("should authenticate when controller loads", function() {
			expect($rootScope.authenticated).toEqual(false);

		});

		describe("Login", function() {

			it("should authenticate successfully with correct credentials", function() {
				$httpBackend.expectGET('/user', function(headers) {
					expect(headers.authorization).toBeDefined();
					return true;
				}).respond(200, {
					name : 'user'
				});
				$scope.credentials = {
					username : 'user',
					password : 'pwd'
				};
				$scope.login();
				$httpBackend.flush();
				expect($rootScope.authenticated).toEqual(true);
			});

			it("should not authenticate successfully if credentials are bad", function() {
				$httpBackend.expectGET('/user', function(headers) {
					expect(headers.authorization).toBeDefined();
					return true;
				}).respond(401);
				$httpBackend.expectGET('login.html').respond(200);
				$scope.credentials = {
					username : 'user',
					password : 'foo'
				};
				$scope.login();
				$httpBackend.flush();
				expect($rootScope.authenticated).toEqual(false);
			});

		});

		describe("Logout", function() {

			it("should logout", function() {
				$httpBackend.expectPOST('/logout').respond(200);
				$scope.logout();
				$httpBackend.flush();
				expect($rootScope.authenticated).toEqual(false);
			});

			it("should not logout", function() {
				$httpBackend.expectPOST('/logout').respond(400);
				$scope.logout();
				$httpBackend.flush();
				expect($rootScope.authenticated).toEqual(false);
			});

		});
		
		describe("Home Controller", function() {
		
			it("should display available exams", function() {
				// first we need to be authenticated
				$httpBackend.expectGET('/user', function(headers) {
					expect(headers.authorization).toBeDefined();
					return true;
				}).respond(200, {
					name : 'user'
				});
				$scope.credentials = {
					username : 'user',
					password : 'pwd'
				};
				$scope.login();
				$httpBackend.flush();
				expect($rootScope.authenticated).toEqual(true);
				
				$httpBackend.expectGET('/exams/availables').respond(200, [
				    {id : 1},
				    {id : 2}
				]);
				var controller = $controller('home', {
					$scope : $scope,
					$rootScope : $rootScope
				});
				$httpBackend.flush();
				expect($scope.userExams.length).toEqual(2);
			});
		});

	});

})
