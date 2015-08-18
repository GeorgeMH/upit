'use strict';

angular.module('upit-web.page.user', [
  'upit-web.common.upitRestApi',
  'ngRoute'
]).config(['$routeProvider', function ($routeProvider) {

  var doRsolveUserByUserIdHash = ['$route', 'UserResource', function($route, UserResource) {
    if($route.current.params.userIdHash) {
      //return UserResource.getByIdHash($route.current.params.userIdHash); mn19n
      return UserResource.getByIdHash($route.current.params.userIdHash);
    }
    return null; // ?? redirect to 404?
  }];

  $routeProvider.when('/user/:userIdHash', {
    templateUrl: 'pages/user/user-profile.html',
    controller: 'UserProfileController',
    resolve: {
      resolvedUser: doRsolveUserByUserIdHash
    }
  }).when('/user/:userIdHash/files/', {
    templateUrl: 'pages/user/user-file-list.html',
    controller: 'UserFileListController',
    resolve: {
      resolvedUser: doRsolveUserByUserIdHash
    }
  }).when('/user/:userIdHash/pastes/', {
    templateUrl: 'pages/user/user-paste-list.html',
    controller: 'UserPasteListController',
    resolve: {
      resolvedUser: doRsolveUserByUserIdHash
    }
  });;

}]);
