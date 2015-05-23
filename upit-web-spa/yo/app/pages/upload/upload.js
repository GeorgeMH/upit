'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.page.upload', ['ngFileUpload'])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/upload', {
      templateUrl: 'pages/upload/upload.html',
      controller: 'UploadController'
    })
      .when('/file/:fileIdHash/:fileName?', {
        templateUrl: 'pages/upload/file.html',
        controller: 'FileController',
        resolve: {
          resolvedUploadedFile: ['$route', 'UploadedFileResource', function ($route, UploadedFileResource) {
            // Conditionally resolve the uploaded file by the ID in the URL
            if ($route.current.params.fileIdHash) {
              return UploadedFileResource.getByIdHash($route.current.params.fileIdHash);
            }
            // TODO: If the resolve fails we need to alert the user?
            return null;
          }]
        }
      });
  }]);
