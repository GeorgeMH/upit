
angular.module('upit-web.security')
  .service('SecurityService', ['$q', 'AuthSessionResource', function($q, AuthSessionResource) {

    this.currentAuthSession = null;
    this.isLoggedIn = false;
    this.isAuthenticated = false;


    this.authenticateUser = function() {

    };

    this.validateAuthSession = function() {

    };

    this.isLoggedIn = function() {
      return this.isLoggedIn;
    }

    this.isAuthenticated = function() {
      return this.isAuthenticated;
    }

  }]);
