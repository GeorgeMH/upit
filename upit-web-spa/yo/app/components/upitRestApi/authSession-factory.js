'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.upitRestApi')
  .factory('AuthSession', ['$q', 'SimpleResourceClient', function($q, SimpleResourceClient) {
    var self = this;

    var resourceContext = {
      resourceName: 'authSession'
    };

    var getById = function(id) {
      return SimpleResourceClient.getById(resourceContext, id);
    };

    var create = function(user) {
      return SimpleResourceClient.create(resourceContext, user);
    };

    var update = function(user) {
      return SimpleResourceClient.update(resourceContext, user);
    };

    var remove = function(user) {
      return SimpleResourceClient.remove(resourceContext, user);
    };

    var login = function(authenticationReuqest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/login/',
        method: 'POST',
        data: authenticationReuqest
      });
    };

    return {
      getById: getById,
      create: create,
      update: update,
      remove: remove,
      login: login
    };

  }]);
