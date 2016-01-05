angular.module('app').controller('exam', function($scope, $http, $routeParams, $location, $timeout, examService) {
	
	$scope.examId = $routeParams.id;
	
	var examDetailsUrl = '/exams/active';
	
	if ($scope.examId != null) {
		var examDetailsUrl = '/exams/' + $scope.examId;
	}
	
	$http.get(examDetailsUrl).success(function(data) {
		$scope.userExam = data;
		if ($scope.userExam.startTime != null) {
			$http.get('/questions').success(function(data) {
				$scope.examQuestions = data;
			});
			// start timer. See timer.js
			$scope.$emit('exam-start', $scope.userExam.timeToComplete, $scope.userExam.exam.id);
		}
	});
	
	$scope.start = function() {
		// start exam
		$http.put('/exams/start/' + $scope.examId).success(function(data) {
			$scope.userExam = data;
			// get questions
			$http.get('/questions').success(function(data) {
				$scope.examQuestions = data;
			});
			// start timer. See timer.js
			$scope.$emit('exam-start', $scope.userExam.timeToComplete, $scope.userExam.exam.id);
		});
	};
	
	$scope.loadQuestion = function(sequence) {
		$http.get('/questions/' + sequence).success(function(data) {
			$scope.examQuestion = data;
			
			if ($scope.examQuestion.question.questionType == 'MULTIPLE_CHOICE') {
				$scope.selectedChoices = [];
				for (var i = 0; i < $scope.examQuestion.question.choices.length; i++) {
					var choice = {id: $scope.examQuestion.question.choices[i].id, value: false}
					$scope.selectedChoices.push(choice);
				}
			} else { //SINGLE_CHOICE
				$scope.selectedChoice = {id: 0};
			}
			
			$http.get('/answers?examQuestionSequence=' + sequence).success(function(data) {
				$scope.answer = data;
				if ($scope.answer != "") {
					if ($scope.examQuestion.question.questionType == 'MULTIPLE_CHOICE') {
						for (var i = 0; i < $scope.selectedChoices.length; i++) {
							if ($scope.answer.selectedChoices.indexOf($scope.selectedChoices[i].id) > -1) {
								$scope.selectedChoices[i].value = true;
							}
						}
					} else { //SINGLE_CHOICE
						$scope.selectedChoice.id = $scope.answer.selectedChoices[0];
					}
				} else {
					$scope.answer = new Object();
				}
				// finally update $scope.sequence to update UI
				$scope.sequence = sequence;
			});
		});
	};
	
	$scope.saveQuestion = function() {
		if ($scope.answer.questionSequence == null) {
			$scope.answer.questionSequence = $scope.sequence;
		}
		
		$scope.answer.selectedChoices = [];
		if ($scope.examQuestion.question.questionType == 'SINGLE_CHOICE') {
			$scope.answer.selectedChoices.push($scope.selectedChoice.id);
		}
		if ($scope.examQuestion.question.questionType == 'MULTIPLE_CHOICE') {
			for (var i = 0; i < $scope.selectedChoices.length; i++) {
				if ($scope.selectedChoices[i].value) {
					$scope.answer.selectedChoices.push($scope.selectedChoices[i].id);
				}
			}
		}

		$http.put('/answers', $scope.answer).success(function(data) {
			$scope.sequence = null;
			// reload questions
			$http.get('/questions').success(function(data) {
				$scope.examQuestions = data;
			});
		});
	};
	
	$scope.submit = function() {
		examService.submit($scope.userExam.exam.id).success(function(data) {
			$location.path('/exam/results/' + data.exam.id);
			// stop timer. See timer.js
			$scope.$emit('exam-finish');
		});
	};
});