'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upload', [
    'ngRoute'
  ])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/upload', {
        templateUrl: 'upload/upload.html',
        controller: 'UploadCtrl'
    });

  }]);