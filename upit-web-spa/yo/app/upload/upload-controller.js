'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.upload')
  .controller('UpCtrl', ['$scope', '$window', 'Upload', 'FileUrlGenerator', function ($scope, $window, Upload, FileUrlGenerator) {
    $scope.$watch('files', function () {
      $scope.upload($scope.files);
    });
    $scope.log = '';

    $scope.upload = function (files) {
      if (files && files.length) {
        for (var i = 0; i < files.length; i++) {
          var file = files[i];
          file.index = i;
          Upload.upload({
            url: '/api_v1/uploadedFile/upload',
            file: file
          }).progress(function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            var fileInProgress = $scope.files[evt.config.file.index];
            if (fileInProgress) {fileInProgress.progress = progressPercentage};
          }).success(function (data, status, headers, config) {
            var completedFile = $scope.files[config.file.index];
            completedFile.urls = {};
            angular.copy(FileUrlGenerator.getURLs(data[0]), completedFile.urls);
            completedFile.uploadedFile = true;
          });
        }
      }
    };
  }]);