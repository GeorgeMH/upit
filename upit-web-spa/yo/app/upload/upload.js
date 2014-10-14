'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upload', [
    'ngRoute',
    'angularFileUpload'
  ])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/upload', {
        templateUrl: 'upload/upload.html',
        controller: 'UploadCtrl'
    })
    .when('/file/:fileIdHash/:fileName?', {
            templateUrl: 'files/files.html',
            controller: 'FilesCtrl',
            resolve: {
                paste: ['$route', 'UploadedFileResource', function($route, UploadedFileResource) {
                    // Conditionally resolve the paste by the ID in the URL
                    if($route.current.params.pasteIdHash) {
                        return UploadedFileResource.getByIdHash($route.current.params.fileIdHash);
                    }
                    return null;
                }]
            }
        });

  }]);