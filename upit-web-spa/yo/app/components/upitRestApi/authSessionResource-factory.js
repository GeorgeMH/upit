'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.upitRestApi')
  .service('AuthSessionResource', ['$q', 'SimpleResourceClient', function($q, SimpleResourceClient) {

    var resourceContext = {
      resourceName: 'authSession'
    };

    this.register = function(registrationRequest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/register/',
        method: 'POST',
        data: registrationRequest
      });
    };

    this.login = function(authenticationReuqest) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/login/',
        method: 'POST',
        data: authenticationReuqest
      });
    };

    this.end = function(authSession) {
      return SimpleResourceClient.makeRestRequest(resourceContext, {
        url: '/end/',
        method: 'POST',
        data: authSession
      });
    };


  }]);
