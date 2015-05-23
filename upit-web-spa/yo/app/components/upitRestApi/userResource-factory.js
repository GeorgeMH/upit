'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.upitRestApi')
  .factory('UserResource', ['$q', 'SimpleResourceClient', function ($q, SimpleResourceClient) {
    var self = this;

    var resourceContext = {
      resourceName: 'user'
    };

    var getById = function (id) {
      return SimpleResourceClient.getById(resourceContext, id);
    };

    var create = function (user) {
      return SimpleResourceClient.create(resourceContext, user);
    };

    var update = function (user) {
      return SimpleResourceClient.update(resourceContext, user);
    };

    var remove = function (user) {
      return SimpleResourceClient.remove(resourceContext, user);
    };

    var getByIdHash = function (hash) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/hash/' + hash,
        method: 'GET'
      });
    };

    var register = function (registrationRequest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/register/',
        method: 'POST',
        data: registrationRequest
      });
    };

    return {
      getById: getById,
      getByIdHash: getByIdHash,
      register: register,
      create: create,
      update: update,
      remove: remove
    };

  }]);
