'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.upload')
    .controller('UploadCtrl', ['$scope', 'FileUploader', function ($scope, FileUploader) {

        var model = {
            uploadedFiles: [

            ]
        };

        $scope.model = model;
        $scope.fileUploader = new FileUploader({
            url: '/api_v1/uploadedFile/uploadFiles',
            removeAfterUpload: true
        });

        $scope.fileUploader.onErrorItem = function(item, response, status, headers){
            console.log('Failed uploading file: ' + status);
        };


        $scope.uploadFile = function(fileItem){
            fileItem.onComplete = function(response, status, headers){
                //TODO: This is a complete hack. Fix it!
                $.each(response, function(idx, item){
                    var extensionIdx = item.fileName.indexOf('.');

                    if(extensionIdx > 0){
                        item.extension = item.fileName.substring(extensionIdx, item.fileName.length);
                    } else {
                        item.extension = '';
                    }
                    model.uploadedFiles.push(item);
                });
            };

            fileItem.upload();
        };


        $scope.getUploadedFileUrl = function(uploadedFile){
           // /api_v1/uploadedFile/download/{{uploadedFile.id}}/{{uploadedFile.hash}}{{uploadedFile.extension}}
            return '/api_v1/uploadedFile/download/' + uploadedFile.id + '/' + uploadedFile.hash + '' + uploadedFile.extension;
        };

    }]);
