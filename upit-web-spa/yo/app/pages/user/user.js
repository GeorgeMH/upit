'use strict';


angular.module('upit-web.page.user', [
  'upit-web.common.upitRestApi',
  'ngRoute'
]).config(['$routeProvider', function ($routeProvider) {

  $routeProvider.when('/user/:userIdHash', {
    templateUrl: 'pages/user/user-profile.html',
    controller: 'UserProfileController',
    resolve: {
      resolvedUser: ['$route', 'UserResource', function($route, UserResource) {
          if($route.current.params.userIdHash) {
              //return UserResource.getByIdHash($route.current.params.userIdHash); mn19n
            return UserResource.getByIdHash($route.current.params.userIdHash);
          }
          return null; // ?? redirect to 404?
      }]
    }
  });

}]);
