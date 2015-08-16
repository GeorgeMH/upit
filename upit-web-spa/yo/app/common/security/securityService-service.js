/**
 * This service manages the 'Authenticated State' of the current User.
 */
angular.module('upit-web.common.security')
  .service('SecurityService', ['$q', '$cookies', '$interval', 'AuthSessionResource', 'UserResource', function ($q, $cookies, $interval, AuthSessionResource, UserResource) {

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

    var onAuthTokenChangeHandler;;

    var isFetchingToken = false;


    this.start = function (updatedAuthSessionCallback) {
      if(serviceState !== SERVICE_STATES.STOPPED) {
        return false; // The service has already been started
      }
      serviceState = SERVICE_STATES.STARTING;
      onAuthTokenChangeHandler = updatedAuthSessionCallback || function() { };

      return resolveAuthSession().then(function() {
        isFetchingToken = false;
        if(currentAuthSession) {
          console.log("AuthSession: " + currentAuthSession.id + " : userId: " + currentAuthSession.userId + ", anonymous " + currentAuthSession.anonymous);
        }
        startValidateTokenInterval();
      });
    };

    this.getAuthSession = function() {
      return currentAuthSession;
    };

    this.getUser = function () {
      var authSession = getAuthSession();
      if (!authSession || !authSession.userId) {
        return $q(null);
      }
      return UserResource.getById(authSession.userId);
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
          AuthSessionResource.validate(currentAuthSession.id).then(function(validatedCurrentAuthSession){
            console.log("Validated auth session: " + validatedCurrentAuthSession);
            setAuthSession(validatedCurrentAuthSession);
          }, function(err){
            console.log("Failed validating auth token: " + err);
            setAuthSession(null);
          });

        }, VALIDATE_TOKEN_INTERVAL);
      }
    };

    var stopValidateTokenInterval = function() {
      if(validateTokenInterval) {
        validateTokenInterval.cancel();
        validateTokenInterval = null;
      }
    };

    var setAuthSession = function(newAuthSession) {
      if(null == newAuthSession){
        currentAuthSession = newAuthSession;
      } else if (currentAuthSession) {
        angular.merge(currentAuthSession, newAuthSession); // merge to preserve any bindings the old token may have had
      } else {
        currentAuthSession = angular.copy(newAuthSession);
      }

      if(onAuthTokenChangeHandler) {
        onAuthTokenChangeHandler(currentAuthSession);
      }
    };

    var resolveAuthSession = function () {

      var deferred = $q.defer();

      var handleError = function(err){
        deferred.reject(err);
        return deferred.promise;
      };


      if(currentAuthSession) {
        deferred.resolve(currentAuthSession);
        return deferred.promise;
      }

      var authSessionId = $cookies.get(AUTH_SESSION_ID_COOKIE_NAME);

      var getAnonymousAuthSessionPromise = function(){
        return AuthSessionResource.getAnonymousSession().then(function(authSession){
          currentAuthSession = authSession;
          deferred.resolve(currentAuthSession);
          return deferred.promise;
        }, handleError);
      };

      if(authSessionId) {
        return AuthSessionResource.getById(authSessionId).then(function(authSession){
          setAuthSession(authSession);
          deferred.resolve(currentAuthSession);
          return deferred.promise;
        }, function(err){
          return getAnonymousAuthSessionPromise();
        });
      } else {
        return getAnonymousAuthSessionPromise();
      }

      return deferred.promise;
    };

  }]);
