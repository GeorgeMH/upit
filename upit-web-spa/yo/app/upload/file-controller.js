'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.upload')
  .controller('FileCtrl', ['$scope', '$window', 'resolvedUploadedFile', function ($scope, $window, resolvedUploadedFile) {

        var model = {
            uploadedFile: resolvedUploadedFile
        };

        $scope.model = model;

  }]);