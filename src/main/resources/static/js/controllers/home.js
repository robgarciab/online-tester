angular.module('app').controller('home', function($rootScope, $scope, $http, $log) {
	
	if ($rootScope.authenticated == true) {
		
		$scope.currentDate = new Date();
		
		$http.get('/exams/availables').then(function (response) {
			$scope.userExams = response.data;
		});
	}

});