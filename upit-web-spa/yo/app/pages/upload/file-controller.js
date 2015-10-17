'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upit-web.page.upload')
  .controller('FileController', ['$scope', 'FileUrlGenerator', 'resolvedUploadedFile', function ($scope, FileUrlGenerator, resolvedUploadedFile) {

    $scope.model = {
      uploadedFile: resolvedUploadedFile,
      urls: FileUrlGenerator.getURLs(resolvedUploadedFile)
    };


  }]);
