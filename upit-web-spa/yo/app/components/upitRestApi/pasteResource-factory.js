'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
  .factory('PasteResource', function($q, simpleResourceClient) {

    var self = this;

    self.resourceName = 'Paste';

    self.get = function(id) {
      return simpleResourceClient.get(self, id);
    };

    self.create = function(paste) {
      return simpleResourceClient.create(self, paste);
    };

    self.update = function(paste) {
      return simpleResourceClient.update(self, paste);
    };

    self.delete = function(paste) {
      return simpleResourceClient.delete(self, paste);
    };

    return {
      get: self.get,
      create: self.create,
      update: self.update,
      delete: self.delete
    };

  });
