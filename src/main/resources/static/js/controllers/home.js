angular.module('app').controller('home', function($rootScope, $scope, $http) {
	
	if ($rootScope.authenticated == true) {
		
		$scope.currentDate = new Date();
		
		$http.get('/exams/availables').success(function(data) {
			$scope.userExams = data;
		});
	}

});