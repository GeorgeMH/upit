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

        var lastUploadTrackerId = 0;

        var model = {
            urlsToDownload: [ ],

            uploads: [ ]
        };

        var TrackedUpload = function(id, theFileItem) {
            this.id = id,
            this.fileItem = theFileItem,
            this.uploadedFile  = null,
            this.meta = { }
        };

        var fileUploader = new FileUploader({
            url: '/api_v1/uploadedFile/upload',
            removeAfterUpload: true
        });

        fileUploader.onAfterAddingFile = function(fileItem) {
            var trackedUpload = new TrackedUpload(lastUploadTrackerId++, fileItem);
            model.uploads.push(trackedUpload);

            trackedUpload.fileItem.onSuccess = function(response, status, headers) {
//                console.log('success: ' + response)
                trackedUpload.uploadedFile = response[0];
            };

//            trackedUpload.fileItem.onProgress = function(progress){
//                console.log(fileItem.file.name + 'Progress: ' + progress);
//            };
//
//            trackedUpload.fileItem.onError = function(response, status, headers) {
//                console.log('error: ' + error);
//            };
//
//            trackedUpload.fileItem.onCancel = function(response, status, headers) {
//                console.log('cancel: ' + response);
//            };
//
//            trackedUpload.fileItem.onComplete = function(response, status, headers) {
//                //console.log('complete: ' + response);
//            };
        };

        $scope.model = model;
        $scope.fileUploader = fileUploader;

        $scope.removeFile = function(upload) {
            fileUploader.removeFromQueue(upload.fileItem);

        }

        $scope.uploadFile = function(fileItem) {
            fileItem.upload();
        };

        $scope.removeAll = function() {
            fileUploader.cancelAll();
            fileUploader.clearQueue();
            model.uploads.length = 0;
        };

        $scope.uploadAll = function() {
            fileUploader.uploadAll();
        }

        $scope.getUploadedFileUrl = function(trackedUpload) {
            var location = $window.location;

            // TODO: Calculate a more accurate file extension from the backend using content analysis on the backend: http://tika.apache.org/
            var extension = '';
            var extIdx = trackedUpload.uploadedFile.fileName.lastIndexOf('.');
            if(extIdx > 0) {
                extension = trackedUpload.uploadedFile.fileName.substring(extIdx, trackedUpload.uploadedFile.fileName.length);
            }

            return location.protocol + '//' + location.hostname + (location.port ? ':'+ location.port: '') + '/api_v1/uploadedFile/download/' + trackedUpload.uploadedFile.idHash + '' + extension;
        };

    }]);
