angular.module('app').factory('examService', ['$http', '$location', function($http, $location) {
  var examService = {};
  
  examService.submit = function(examId) {
	  return $http.put('/exams/' + examId + '/evaluate');
  }
  
  return examService;
}]);