'use strict';


angular.module('upit-web.page.auth', [
  'upit-web.upitRestApi',
  'upit-web.security',
  'ngRoute'
]).config(['$routeProvider', function ($routeProvider) {

  $routeProvider.when('/auth/login/', {
    templateUrl: 'pages/auth/auth-login.html',
    controller: 'AuthLoginController',
    resolve: {}
  });

  $routeProvider.when('/auth/logout/', {
    templateUrl: 'pages/auth/auth-register.html',
    controller: 'AuthLogoutController',
    resolve: {}
  });

  $routeProvider.when('/auth/register/', {
    templateUrl: 'pages/auth/auth-register.html',
    controller: 'AuthRegisterController',
    resolve: {}
  });

}]);
