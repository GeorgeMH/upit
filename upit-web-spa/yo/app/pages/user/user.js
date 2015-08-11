'use strict';


angular.module('upit-web.page.user', [
  'upit-web.common.upitRestApi',
  'ngRoute'
]).config(['$routeProvider', function ($routeProvider) {


  // TODO: Does this alias to /user/{id} of the currently logged in user?
  //$routeProvider.when('user/profile', {
  //    templateUrl: 'auth/user-profile.html',
  //    controller: 'UserProfileController',
  //    resolve: {
  //        resolvedUser: {}
  //        //resolvedPaste: ['$route', 'PasteResource', function($route, PasteResource) {
  //        //    // Conditionally resolve the paste by the ID in the URL
  //        //    if($route.current.params.pasteIdHash) {
  //        //        return PasteResource.getByIdHash($route.current.params.pasteIdHash);
  //        //    }
  //        //    return null;
  //        //}]
  //    }
  //});

  $routeProvider.when('/user/{id}', {
    templateUrl: 'user/user-profile.html',
    controller: 'UserProfileController',
    resolve: {
      resolvedUser: {}
      //resolvedPaste: ['$route', 'PasteResource', function($route, PasteResource) {
      //    // Conditionally resolve the paste by the ID in the URL
      //    if($route.current.params.pasteIdHash) {
      //        return PasteResource.getByIdHash($route.current.params.pasteIdHash);
      //    }
      //    return null;
      //}]
    }
  });

}]);
