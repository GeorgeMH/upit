'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.upitRestApi')
    .factory('UserResource', ['$q', 'SimpleResourceClient', function($q, SimpleResourceClient) {
        var self = this;

        var resourceContext = {
            resourceName: 'user'
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