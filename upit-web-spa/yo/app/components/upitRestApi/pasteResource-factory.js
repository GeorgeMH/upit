'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
  .factory('PasteResource', ['$q', 'SimpleResourceClient', function($q, SimpleResourceClient) {
    var self = this;

    var resourceContext = {
      resourceName: 'paste'
    };

    var getById = function(id) {
      return SimpleResourceClient.getById(resourceContext, id);
    };

    var create = function(paste) {
      return SimpleResourceClient.create(resourceContext, paste);
    };

    var update = function(paste) {
      return SimpleResourceClient.update(resourceContext, paste);
    };

    var remove = function(paste) {
      return SimpleResourceClient.remove(resourceContext, paste);
    };

    var getByIdHash = function (hash) {
        return SimpleResourceClient.makeRestRequest(resourceContext, {
            url: '/hash/' + hash,
            method: 'GET'
        });
    };

    return {
      getById: getById,
      getByIdHash: getByIdHash,
      create: create,
      update: update,
      remove: remove
    };

  }]);
