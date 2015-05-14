'use strict';

angular.module('upitWebSpa.upload')
    .factory('FileUrlGenerator', ['$location', function ($location) {
        var self = this;

        var getBaseURL = function() {
            return $location.protocol() + '://' + $location.host() + ($location.port() ? ':'+ $location.port(): '');
        };

        var directDownload = function(uploadedFile, showExtension) {
            var url = getBaseURL() +  '/d/' + encodeURIComponent(uploadedFile.idHash);
            if(showExtension && uploadedFile.extension) {
                url += uploadedFile.extension;
            }
            return url;
        };

        var directDownloadWithName = function(uploadedFile) {
            if(!uploadedFile.fileName) {
                return null;
            }
            return directDownload(uploadedFile, false) + '/' + uploadedFile.fileName;
        };

        var spaShort = function(uploadedFile) {
            return getBaseURL() + '/#/file/' + uploadedFile.idHash + '/' ;
        };

        var spaShortWithName = function(uploadedFile) {
            if(!uploadedFile.fileName) {
                return null;
            }
            return spaShort(uploadedFile) + uploadedFile.fileName;
        };

        var getURLs = function(uploadedFile) {
            return {
                directDownload: directDownload(uploadedFile, true),
                directDownloadWithName: directDownloadWithName(uploadedFile),
                spaShort: spaShort(uploadedFile),
                spaShortWithName: spaShortWithName(uploadedFile)
            };
        };

        return {
            getBaseURL: getBaseURL,
            directDownload: directDownload,
            directDownloadWithName: directDownloadWithName,
            spaShort: spaShort,
            spaShortWithName: spaShortWithName,
            getURLs: getURLs
        };

    }]);
