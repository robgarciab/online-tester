angular.module('app').controller('navigation', function($rootScope, $scope, $http, $location, $route) {

	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":"
							+ credentials.password)
		} : {};
				
		$http.get('/user', {
			headers : headers
		}).then(function successCallback(response) {
			if (response.data.name) {
				$rootScope.authenticated = true;
				$rootScope.username = response.data.name;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback($rootScope.authenticated);
		}, function errorCallback(response) {
			$rootScope.authenticated = false;
			$rootScope.username = null;
			callback && callback(false);
		});

	}

	authenticate();

	$scope.credentials = {};
	$scope.login = function() {
		authenticate($scope.credentials, function(authenticated) {
			if (authenticated) {
				$location.path("/");
				$scope.error = false;
				$rootScope.authenticated = true;
			} else {
				$location.path("/login");
				$scope.error = true;
				$rootScope.authenticated = false;
			}
		})
	};

	$scope.logout = function() {
		$http.post('/logout', {}).then(function successCallback(response) {
			$rootScope.authenticated = false;
			$location.path("/");
		}, function errorCallback(response) {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}

});