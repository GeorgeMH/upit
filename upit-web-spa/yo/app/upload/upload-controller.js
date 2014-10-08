'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.upload')
    .controller('UploadCtrl', ['$scope', '$window', 'FileUploader', function ($scope, $window, FileUploader) {

        var model = {
            urlsToDownload: [ ],
            uploadedFiles: [ ]
        };

        var fileUploader = new FileUploader({
            url: '/api_v1/uploadedFile/upload',
            removeAfterUpload: true
        });

        $scope.model = model;
        $scope.fileUploader = fileUploader;


        $scope.removeFile = function(fileItem){
            fileUploader.removeFromQueue(fileItem);
        }

        $scope.uploadFile = function(fileItem){

            fileItem.onComplete = function(response, status, headers){
                $.each(response, function(idx, item){
                    //TODO: This is a complete hack. Fix it!
                    var extensionIdx = item.fileName.lastIndexOf('.');

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


        $scope.getUploadedFileUrl = function(uploadedFile) {
            var location = $window.location;
            return location.protocol + '//' + location.hostname + (location.port ? ':'+location.port: '') + '/api_v1/uploadedFile/download/' + uploadedFile.idHash + '' + uploadedFile.extension;
        };

    }]);
