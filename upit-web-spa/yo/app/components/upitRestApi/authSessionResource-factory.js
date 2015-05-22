'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.upitRestApi')
  .factory('AuthSessionResource', ['$q', 'SimpleResourceClient', function ($q, SimpleResourceClient) {

    var resourceContext = {
      resourceName: 'authSession'
    };

    var register = function (registrationRequest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/register/',
        method: 'POST',
        data: registrationRequest
      });
    };

    var login = function (authenticationReuqest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/login/',
        method: 'POST',
        data: authenticationReuqest
      });
    };

    var end = function (authSession) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/end/',
        method: 'POST',
        data: authSession
      });
    };

    return {
      register: register,
      login: login,
      end: end
    };


  }]);
