angular.module('app').directive('timer', function() {
	return {
		restrict: 'E',
		templateUrl: '/templates/directives/timer.html',
		controller: function($scope, $location, $http, $timeout, examService) {

			$scope.poll = function() {
				if ($scope.stop) {
		    		return;
		    	}
				
				if ($scope.secondsLeft <= 0) {
					examService.submit($scope.examId).success(function(data) {
						$location.path('/exam/results/' + data.exam.id);
					});
		    		return;
		    	}
		    	
		    	if ($scope.secondsLeft % 10 == 0) { // get seconds left from server every 10 seconds
		    		$http.get('/exams/active/secondsLeft').success(function(data) {
		    			$scope.secondsLeft = data;
						$timeout($scope.poll, 1000);
					});
		    	} else { // reduce counter every second
		    		$scope.secondsLeft--;
		    		$timeout($scope.poll, 1000);
		    	}
		    };
		    
		    $scope.startTimer = function(timeToComplete, examId) {
		    	$scope.secondsLeft = timeToComplete;
		    	$scope.examId = examId;
		    	$scope.stop = false;
				$scope.poll();
		    }
		    
		    $scope.stopTimer = function() {
		    	$scope.stop = true;
		    }
		    
		    $scope.$on('exam-start', function(event, timeToComplete, examId) {
		    	$scope.startTimer(timeToComplete, examId);
			});
		    
		    $scope.$on('exam-finish', function(event) {
		    	$scope.stopTimer();
			});
		},
		controllerAs: 'timer'
	};
});