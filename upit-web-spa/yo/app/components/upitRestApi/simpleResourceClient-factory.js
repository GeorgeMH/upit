'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.upitRestApi')
    .factory('SimpleResourceClient', function($log, $q, $http) {
        var self = this;

        // TODO: Inject this
        var urlBaseContext = "/api_v1/";

        var getResourceURL = function(requestContext) {
            return urlBaseContext + requestContext.resourceName + '/';
        };

        var makeRestRequest = function(resourceContext, requestContext) {
            var result = $q.defer();

            if(!resourceContext.resourceName) {
                result.reject("Resource Context does not have ResourceName set: " + resourceContext);
                return result.promise;
            }

            var httpReq = {
                method: requestContext.method,
                url: getResourceURL(resourceContext)
            };

            if(requestContext.data) {
                //httpReq.data = JSON.stringify(requestContext.data);
                httpReq.data = requestContext.data;
            }

            // Append the context url onto the base Resource URL
            if(requestContext.url) {
               httpReq.url += requestContext.url;
            }

            $log.debug('RequestContext: ' + requestContext);

            $http(httpReq).success(function(data, status, headers, config) {
                result.resolve(data);
            }).error(function(data, status, headers, config) {
                result.reject(data);
            });

            return result.promise;
        };

        var getById = function(resourceContext, resourceId) {
            return makeRestRequest(resourceContext, {
                url: resourceId,
                method: 'GET'
            });
        };

        var create = function(resourceContext, resource) {
            return makeRestRequest(resourceContext, {
                method: 'POST',
                data: JSON.stringify(resource)
            });
        };

        var update = function(resourceContext, resource) {
            return makeRestRequest(resourceContext, {
                method: 'PUT',
                data: JSON.stringify(resource)
            });
        };

        var remove = function(resourceContext, resource) {
            return makeRestRequest(resourceContext, {
                method: 'DELETE',
                data: resource
            });
        };

        return {
            getResourceURL: getResourceURL,
            makeRestRequest: makeRestRequest,
            getById: getById,
            create: create,
            update: update,
            remove: remove
        };
    });
