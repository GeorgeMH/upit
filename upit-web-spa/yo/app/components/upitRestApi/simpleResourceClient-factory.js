'use strict';

angular.module('upitWebSpa.upitRestApi')
    .factory('SimpleResourceClient', ['$log', '$q', '$http', function($log, $q, $http) {
        var self = this;

        // TODO: Inject this
        var urlBaseContext = "/api_v1";

        var getResourceURL = function(requestContext) {
            return urlBaseContext + '/' + requestContext.resourceName;
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
                httpReq.data = requestContext.data;
            }

            // Append the context url onto the base Resource URL
            if(requestContext.url) {
               httpReq.url += requestContext.url;
            }

            $http(httpReq).success(function(data, status, headers, config) {
                if(200 == status) {
                    result.resolve(data);
                } else {
                    result.reject(data, status, headers, config);
                }
            }).error(function(data, status, headers, config) {
                result.reject(data, status, headers, config);
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
                data: resource
            });
        };

        var update = function(resourceContext, resource) {
            return makeRestRequest(resourceContext, {
                method: 'PUT',
                data: resource
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
    }]);
