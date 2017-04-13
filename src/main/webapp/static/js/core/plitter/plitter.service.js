/**
 * 
 */
'use strict';

angular.module("core.plitter")
.factory('Plitter', [ '$resource', function($resource) {
	// $resource() function returns an object of resource class
	return $resource('http://localhost:8080/social/plitters/:id', {
		id : '@id'
	},{
		update: {
		      method: 'PUT' // To send the HTTP Put request when calling this custom update method.
		}
		
	},
	{
	    stripTrailingSlashes: false
	}
);
}]);
