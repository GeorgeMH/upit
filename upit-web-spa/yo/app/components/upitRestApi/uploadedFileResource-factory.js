'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
    .factory('UploadedFileResource', ['$q', 'SimpleResourceClient', function($q, SimpleResourceClient) {
        var self = this;

        var resourceContext = {
            resourceName: 'uploadedFile'
        };

        self.getById = function(id) {
            return SimpleResourceClient.getById(resourceContext, id);
        };

        self.create = function(paste) {
            return SimpleResourceClient.create(resourceContext, paste);
        };

        self.update = function(paste) {
            return SimpleResourceClient.update(resourceContext, paste);
        };

        self.remove = function(paste) {
            return SimpleResourceClient.remove(resourceContext, paste);
        };

        self.getByIdHash = function (hash) {
            return SimpleResourceClient.makeRestRequest(resourceContext, {
                url: '/hash/' + hash,
                method: 'GET'
            });
        };

        return {
            getById: self.getById,
            getByIdHash: self.getByIdHash,
            create: self.create,
            update: self.update,
            remove: self.remove
        };

    }]);
