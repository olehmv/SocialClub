'use strict';

angular.
  module('plitterList').
  component('plitterList', {
    templateUrl: '/social/static/js/plitter-list/plitter-list.template.html',
    controller: ['Plitter',
      function PlitterListController(Plitter) {
        this.plitters = Plitter.query();
        this.orderProp = 'registrationDate';

      }
    ]
  });
