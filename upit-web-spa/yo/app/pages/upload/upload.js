'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upload', ['ngFileUpload'])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/upload', {
      templateUrl: 'upload/upload.html',
      controller: 'UpCtrl'
    })
      .when('/file/:fileIdHash/:fileName?', {
        templateUrl: 'upload/file.html',
        controller: 'FileCtrl',
        resolve: {
          resolvedUploadedFile: ['$route', 'UploadedFileResource', function($route, UploadedFileResource) {
            // Conditionally resolve the uploaded file by the ID in the URL
            if($route.current.params.fileIdHash) {
              return UploadedFileResource.getByIdHash($route.current.params.fileIdHash);

            }
            // TODO: If the resolve fails we need to alert the user?
            return null;
          }]
        }
      });
  }]);
