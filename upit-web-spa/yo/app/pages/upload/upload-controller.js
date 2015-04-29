'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upit-web.page.upload')
    .controller('UploadController', ['$scope', '$window', 'FileUploader', 'FileUrlGenerator', function ($scope, $window, FileUploader, FileUrlGenerator) {

        var lastUploadTrackerId = 0;

        var model = {
            urlsToDownload: [ ],
            uploads: [ ]
        };

        var TrackedUpload = function(id, theFileItem) {
            this.id = id,
            this.fileItem = theFileItem,
            this.uploadedFile  = null,
            this.urls = {

            }
        };

        var fileUploader = new FileUploader({
            url: '/api_v1/uploadedFile/upload',
            removeAfterUpload: true
        });

        fileUploader.onAfterAddingFile = function(fileItem) {
            var trackedUpload = new TrackedUpload(lastUploadTrackerId++, fileItem);
            model.uploads.push(trackedUpload);

            trackedUpload.fileItem.onSuccess = function(response, status, headers) {
                // TODO: Validation?
                trackedUpload.uploadedFile = response[0];
                angular.copy(FileUrlGenerator.getURLs(trackedUpload.uploadedFile), trackedUpload.urls);
            };

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
        };

    }]);
