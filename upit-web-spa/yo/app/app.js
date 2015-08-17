'use strict';

/**
 * @ngdoc overview
 * @name upitWebSpa
 * @description
 * # upit
 *
 * Main module of the application.
 */
angular
  .module('upit-web', [
    'upit-web.common.upitRestApi',
    'upit-web.common.security',

    'upit-web.components.navigation',
    'upit-web.page.auth',
    'upit-web.page.user',
    'upit-web.page.main',
    'upit-web.page.paste',
    'upit-web.page.upload',

    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap'
  ]).config(['$routeProvider', function($routeProvider) {

    $routeProvider.otherwise({ redirectTo:"/404" });

  }])
  .run(['$rootScope', 'SecurityService', function($rootScope, SecurityService) {
    // Minimal Auth Session impl that is used while the SecurityService starts and validates the users credentials
    $rootScope.userAuthSession = { isActive: false };
    $rootScope.currentUser = { };

    // Start the Security Management Service. The supplied callback will keep the rootScope userAuthSession updated
    SecurityService.start(function(authSession, user) {
      if(authSession) {
        console.log("AuthSessionChange: " + authSession.id);
        angular.merge($rootScope.userAuthSession, authSession);
        angular.merge($rootScope.currentUser, user);
      } else {
        console.log("AuthSession Failed");
      }
    });

  }]);
