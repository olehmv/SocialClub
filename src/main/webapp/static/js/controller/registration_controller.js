'use strict';

App.controller('RegistrationController',['$scope', 'Profile',function($scope,Profile){
	 var self = this;
     self.profile= new Profile();
      
     self.profiles=[];
          
     self.fetchAllProfiles = function(){
         self.profiles = Profile.query();
     };
       
     self.createProfile = function(){
         self.profile.$save(function(){
             self.fetchAllProfiles();
         });
     };

     self.updateProfile = function(){
       self.profile.$update(function(){
        self.fetchAllProfiles();
         });
     };

    self.deleteProfile = function(identity){
      var profile = Profile.get({id:identity}, function() {
          profile.$delete(function(){
                 console.log('Deleting user with id ', identity);
                 self.fetchAllProfiles();
             });
        });
     };

    self.fetchAllProfiles();

     self.submit = function() {
         if(self.profile.id==null){
             console.log('Saving New User', self.profile);    
             self.createProfile();
         }else{
             console.log('Upddating user with id ', self.profile.id);
             self.updateProfile();
             console.log('User updated with id ', self.profile.id);
         }
         self.reset();
     };
          
     self.edit = function(id){
         console.log('id to be edited', id);
         for(var i = 0; i < self.profiles.length; i++){
             if(self.profiles[i].id === id) {
                self.profile = angular.copy(self.profiles[i]);
                break;
             }
         }
     };
          
     self.remove = function(id){
         console.log('id to be deleted', id);
         if(self.profile.id === id) {//If it is the one shown on screen, reset screen
            self.reset();
         }
         self.deleteProfile(id);
     };

      
     self.reset = function(){
         self.profile= new Profile();
         $scope.myForm.$setPristine(); //reset Form
     };

 }]);





