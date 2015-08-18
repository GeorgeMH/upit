/**
 * This service manages the 'Authenticated State' of the current User.
 */
angular.module('upit-web.common.security')
  .service('SecurityService', ['$q', '$cookies', '$interval', 'AuthSessionResource', 'UserResource',
    function ($q, $cookies, $interval, AuthSessionResource, UserResource) {
    var self = this;

    var SERVICE_STATES = {
      STARTING: "STARTING",
      RUNNING: "RUNNING",
      STOPPING: "STOPPING",
      STOPPED:"STOPPED"
    };
    var AUTH_SESSION_ID_COOKIE_NAME = "authSessionId";
    var VALIDATE_TOKEN_INTERVAL = 1000 * 60; // 1 minutes

    var serviceState = SERVICE_STATES.STOPPED;
    var currentAuthSession = null;
    var validateTokenInterval = null;

    var currentUser = null;

    var onAuthTokenChangeHandler;

    this.start = function (updatedAuthSessionCallback) {
      if(serviceState !== SERVICE_STATES.STOPPED) {
        return false; // The service has already been started
      }
      serviceState = SERVICE_STATES.STARTING;
      onAuthTokenChangeHandler = updatedAuthSessionCallback || function() { };

      return resolveAuthSession().then(function() {
        if(currentAuthSession) {
          console.log("AuthSession: " + currentAuthSession.id + " : userId: " + currentAuthSession.userId +
            ", anonymous " + currentAuthSession.anonymous);
        }
        startValidateTokenInterval();
      });
    };

    this.getAuthSession = function() {
      return currentAuthSession;
    };

    this.getUser = function () {
      var deferred = $q.defer();

      if(currentUser) {
        deferred.resolve(currentUser);
        return deferred.promise;
      }
      var authSession = self.getAuthSession();
      if (!authSession || !authSession.userId) {
        deferred.reject("No Auth Session");
        return deferred.promise;
      }

      UserResource.getById(authSession.userId).then(function(user){
        currentUser = user;
        deferred.resolve(currentUser);
      }, function(err){
        deferred.reject(err);
      });

      return deferred.promise;
    };

    this.isAnonymous = function() {
      if(!currentAuthSession) {
        return true;
      }
      return currentAuthSession.anonymous;
    };

    this.getServiceState = function() {
      return serviceState;
    }

    // Private functions
    var startValidateTokenInterval = function() {
      if(validateTokenInterval) {
        return;
      } else {
        console.log("Starting validate token auth interval");
        // Set an interval to validate the AuthSession every so often
        validateTokenInterval = $interval(function() {
          self.validateCurrentToken();
        }, VALIDATE_TOKEN_INTERVAL);
      }
    };

    this.validateCurrentToken = function() {
      return AuthSessionResource.validate(currentAuthSession.id).then(function(validatedCurrentAuthSession){
        console.log("Validated AuthSession: " + validatedCurrentAuthSession.id + " : userId: " +
          validatedCurrentAuthSession.userId + ", anonymous " + validatedCurrentAuthSession.anonymous);
        self.setAuthSession(validatedCurrentAuthSession);
      }, function(err){
        console.log("Failed validating auth token: " + err);
        // TODO: error handling
        self.setAuthSession(null);
      });
    };

    var stopValidateTokenInterval = function() {
      if(validateTokenInterval) {
        validateTokenInterval.cancel();
        validateTokenInterval = null;
      }
    };

    this.setAuthSession = function(newAuthSession) {
      if(null == newAuthSession) {
        currentAuthSession = newAuthSession;
      } else if (currentAuthSession) {
        angular.merge(currentAuthSession, newAuthSession); // merge to preserve any bindings the old token may have had
      } else {
        currentAuthSession = angular.copy(newAuthSession);
      }

      // TODO: What about if currentAuthSession.userId != currentUser.id ?

      if(currentAuthSession && currentAuthSession.userId && !currentAuthSession.anonymous) {
        self.getUser().then(function (user) {
          if (onAuthTokenChangeHandler) {
            onAuthTokenChangeHandler(currentAuthSession, user);
          }
        }, function(err){
          console.log("Failed getting user: " + err);
          if (onAuthTokenChangeHandler) {
            onAuthTokenChangeHandler(currentAuthSession, null);
          }
        });
      } else if (currentAuthSession && onAuthTokenChangeHandler) {
        onAuthTokenChangeHandler(currentAuthSession, null);
      }

    };

    var resolveAuthSession = function () {

      var deferred = $q.defer();

      var handleError = function(err){
        deferred.reject(err);
        return deferred.promise;
      };


      if(currentAuthSession) {
        console.log("currentAuthSession exists and has been validated previously");
        deferred.resolve(currentAuthSession);
        return deferred.promise;
      }

      var getAnonymousAuthSessionPromise = function(){
        return AuthSessionResource.getAnonymousSession().then(function(authSession){
          console.log("Getting anonymous session");
          self.setAuthSession(authSession);
          deferred.resolve(currentAuthSession);
          return deferred.promise;
        }, handleError);
      };

      var authSessionId = $cookies.get(AUTH_SESSION_ID_COOKIE_NAME);

      if(!authSessionId) {
        console.log("No existing AuthSession cookie set")
        return getAnonymousAuthSessionPromise();
      } else {
        console.log("Validating existing auth session id: " + authSessionId);
        AuthSessionResource.validate(authSessionId).then(function(authSession){
          console.log("Successfully validated auth session");
          self.setAuthSession(authSession);
          deferred.resolve(currentAuthSession);
        }, function(err){
          currentAuthSession = null;
          getAnonymousAuthSessionPromise();
        });
      }

      return deferred.promise;
    };

  }]);
