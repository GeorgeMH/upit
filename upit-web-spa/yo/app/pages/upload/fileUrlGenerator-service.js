'use strict';

angular.module('upit-web.page.upload')
  .service('FileUrlGenerator', ['$location', function ($location) {
    var self = this;

    self.getBaseURL = function () {
      return $location.protocol() + '://' + $location.host() + ($location.port() ? ':' + $location.port() : '');
    };

    self.directDownload = function (uploadedFile, showExtension) {
      var url = getBaseURL() + '/d/' + encodeURIComponent(uploadedFile.idHash);
      if (showExtension && uploadedFile.extension) {
        url += uploadedFile.extension;
      }
      return url;
    };

    self.directDownloadWithName = function (uploadedFile) {
      if (!uploadedFile.fileName) {
        return null;
      }
      return directDownload(uploadedFile, false) + '/' + uploadedFile.fileName;
    };

    self.spaShort = function (uploadedFile) {
      return getBaseURL() + '/#/file/' + uploadedFile.idHash + '/';
    };

    self.spaShortWithName = function (uploadedFile) {
      if (!uploadedFile.fileName) {
        return null;
      }
      return spaShort(uploadedFile) + uploadedFile.fileName;
    };

    self.getURLs = function (uploadedFile) {
      return {
        directDownload: directDownload(uploadedFile, true),
        directDownloadWithName: directDownloadWithName(uploadedFile),
        spaShort: spaShort(uploadedFile),
        spaShortWithName: spaShortWithName(uploadedFile)
      };
    };

    return {
      getBaseURL: self.getBaseURL,
      directDownload: self.directDownload,
      directDownloadWithName: self.directDownloadWithName,
      spaShort: self.spaShort,
      spaShortWithName: self.spaShortWithName,
      getURLs: self.getURLs
    };

  }]);
