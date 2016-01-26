angular.module('app').controller('results', function($rootScope, $routeParams, $scope, $http) {
	
	$scope.examId = $routeParams.id;
	
	$http.get('/exams/' + $scope.examId).then(function (response) {
		$scope.results = response.data;
	});
});