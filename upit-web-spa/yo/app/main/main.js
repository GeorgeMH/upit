'use strict';

/**
 * @ngdoc function
 * @name upiWebSpa.main
 * @description # Main module definition
 */

angular.module('upitWebSpa.main', [
    'ngRoute'
  ])
  .config(function ($routeProvider) {

    $routeProvider.when('/', {
      templateUrl: 'main/main.html',
      controller: 'MainCtrl'
    });

  });