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
  ]).controller("IndexController", ['$scope', 'SecurityService', function($scope, SecurityService){
    SecurityService.init();

    $scope.authSession
  }]);
