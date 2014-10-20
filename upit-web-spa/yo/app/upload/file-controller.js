'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.upload')
  .controller('FileCtrl', ['$scope', '$window', 'FileUrlGenerator', 'resolvedUploadedFile', function ($scope, $window, FileUrlGenerator, resolvedUploadedFile) {

        var model = {
            uploadedFile: resolvedUploadedFile,
            urls: FileUrlGenerator.getURLs(resolvedUploadedFile)
        };

        $scope.model = model;

  }]);
