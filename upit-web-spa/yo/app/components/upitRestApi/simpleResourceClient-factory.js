'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
    .factory('SimpleResourceClient', function($q, $http) {
        var self = this;

        // TODO: Inject this
        var baseUrl = "/api_v1/";

        var makeRestRequest = function(resourceContext, requestContext) {
            var result = $q.defer();

            if(!resourceContext.resourceName) {
                result.reject("Resource Service does not have ResourceName set: " + resourceService);
                return result.promise;
            }

            if(!requestContext.url) {
                requestContext.url = baseUrl + resourceContext.resourceName;
            }
            var foo = JSON.stringify(requestContext.data)
            $http({
                method: requestContext.method,
                url: requestContext.url,
                data: JSON.stringify(requestContext.data)
            })
            .success(function(data, status, headers, config) {
                result.resolve(data);
            })
            .error(function(data, status, headers, config) {
                result.reject(data);
            });

            return result.promise;
        };

        var get = function(resourceContext, object) {
            return makeRestRequest(resourceContext, {
                method: 'GET',
                data: object
            });
        };

        var create = function(resourceContext, object) {
            return makeRestRequest(resourceContext, {
                method: 'POST',
                data: object
            });
        };

        var update = function(resourceContext, object) {
            return makeRestRequest(resourceContext, {
                method: 'PUT',
                data: object
            });
        };

        var remove = function(resourceContext, object) {
            return makeRestRequest(resourceContext, {
                method: 'DELETE',
                data: object
            });
        };

        return {
            makeRestRequest: makeRestRequest,
            get: get,
            create: create,
            update: update,
            remove: remove
        };
    });
