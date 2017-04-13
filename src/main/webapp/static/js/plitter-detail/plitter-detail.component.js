'use strict';

// Register `phoneDetail` component, along with its associated controller and template
angular.
  module('plitterDetail').
  component('plitterDetail', {
    templateUrl: '/social/static/js/plitter-detail/plitter-detail.template.html',
    controller: ['$routeParams', 'Plitter',
      function PlitterDetailController($routeParams, Plitter) {
        var self = this;
        self.plitter = Plitter.get({id: $routeParams.id}, function(plitter) {
            self.setImage(plitter.photos[0]);
            });
        self.setImage = function setImage(imageUrl) {
            self.mainImageUrl = imageUrl;
          };
      }
    ]
  });
//controller: ['$routeParams', 'Phone',
//    function PhoneDetailController($routeParams, Phone) {
//      var self = this;
//      self.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
//        self.setImage(phone.images[0]);
//      });
//
//      self.setImage = function setImage(imageUrl) {
//        self.mainImageUrl = imageUrl;
//      };
//    }
//  ]
//});
