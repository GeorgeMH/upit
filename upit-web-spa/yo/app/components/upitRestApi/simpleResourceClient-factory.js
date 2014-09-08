'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
    .factory('SimpleResourceClient', function($q, $http) {
        var self = this;

        self.makeRestRequest = function(resourceService, requestContext) {
            var result = $q.defer();

            if(!resourceService.resourceName) {
                result.reject("Resource Service does not have ResourceName set: " + resourceService);
                return result.promise;
            }

            // DO IT

            return result.promise;
        };

        self.get = function(resourceService, id) {
            var result = $q.defer();

            var isArray = angular.isArray(id);

            var resultPromises = [];

            if(angular.isArray(id)) {

            } else {

            }

            return result.promise;
        };

        self.create = function(resourceService, object) {

        };


        self.update = function(resourceService, object) {

        };


        self.delete = function(resourceService, object) {

        };


        return {
            get: self.get,
            create: self.create,
            update: self.update,
            delete: self.delete
        };
    });
