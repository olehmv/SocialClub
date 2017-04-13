'use strict';

angular.
  module('socialApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/plitters', {
          template: '<plitter-list></plitter-list>'
        }).
        when('/plitter/:id', {
          template: '<plitter-detail></plitter-detail>'
        }).
        otherwise('/plitters');
    }
  ]);
