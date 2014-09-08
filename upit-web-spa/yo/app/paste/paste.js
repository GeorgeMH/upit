'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.paste', [
    'ngRoute'
  ])
  .config(function ($routeProvider) {

    $routeProvider.when('/paste', {
      templateUrl: 'paste/paste.html',
      controller: 'PasteCtrl'
    });

  });