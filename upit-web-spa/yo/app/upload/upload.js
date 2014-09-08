'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upload', [
    'ngRoute'
  ])
  .config(function ($routeProvider) {

    $routeProvider.when('/upload', {
        templateUrl: 'upload/upload.html',
        controller: 'UploadCtrl'
    });

  });