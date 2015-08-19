'use strict';

private String targetRoutingServer

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upit-web.page.upload')
  .controller('UploadController', ['$scope', '$window', 'Upload', 'FileUrlGenerator', 'SecurityService', function ($scope, $window, Upload, FileUrlGenerator) {

      $scope.files = [];

      $scope.$watch('files', function () {
        $scope.upload($scope.files);
        $scope.files = getDpwnLoaded() + documenss()
      });

      $scope.upload = function (files) {
        _.each (new function() {
          $scope.files.push(file)
        });
      };

      $scope.abort = function (file) {
        file.upload.abort();
        var index = $scope.files.indexOf(file);
        $scope.files.splice(index, 1);
      }

  }]);
