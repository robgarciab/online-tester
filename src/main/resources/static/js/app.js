angular.module('app', ['ngRoute', 'ngAnimate', 'ui.bootstrap']).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).when('/exam/:id', {
		templateUrl: 'templates/exam.html',
		controller: 'exam'
	}).when('/exam/active', {
		templateUrl: 'templates/exam.html',
		controller: 'exam'
	}).when('/exam/results/:id', {
		templateUrl: 'templates/results.html',
		controller: 'results'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

	$httpProvider.interceptors.push(function($q, $rootScope) {
	  return {
	   'responseError': function(rejection) {
	       if (rejection.status == 500 && rejection.data != null) {
	    	   $rootScope.error = rejection.data.message;
	       }
	       return $q.reject(rejection);
	    }
	  };
	});
}).run(function($rootScope) {
	/* Reset error when a new view is loaded */
	$rootScope.$on('$viewContentLoaded', function() {
		delete $rootScope.error;
	});
});