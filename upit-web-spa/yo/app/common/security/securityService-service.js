/**
 * This service manages the 'Authenticated State' of the current User.
 */
angular.module('upit-web.common.security')
  .service('SecurityService', ['$q', '$cookies', 'AuthSessionResource', 'UserResource', function ($q, $cookies, AuthSessionResource, UserResource) {

    var AUTH_SESSION_ID_COOKIE_NAME = "authSessionId";
    var VALIDATE_TOKEN_INTERVAL = 1000 * 60; // 1 minutes
    var currentAuthSession = null;
    var validateTokenInterval = null;

    this.init = function () {
      if(null === validateTokenInterval) {
        setAuthSession().then(function() {
          console.log("AuthSession: " + currentAuthSession.id);
          // Set an interval to validate the AuthSession every so often
          validateTokenInterval = setInterval(function() {
            AuthSessionResource.validate(currentAuthSession).then(function(validatedCurrentAuthSession){
              console.log("Validated auth session: " + validatedCurrentAuthSession);
              angular.merge(currentAuthSession, validatedCurrentAuthSession);
            }, function(err){
              // TODO? Redirect to the login page? Might be nice to warn a user their token is about to expire
              console.log('TODO: Validate AuthSession failed: ' + err);
            });
          }, VALIDATE_TOKEN_INTERVAL);
        });
      }
    };

    var setAuthSession = function () {
      var deferred = $q.defer();

      var authSessionId = $cookies.get(AUTH_SESSION_ID_COOKIE_NAME);

      var handleError = function(err){
        deferred.reject(err);
        return deferred.promise;
      };

      var getAnonymousAuthSessionPromise = function(){
        AuthSessionResource.getAnonymousSession().then(function(authSession){
          currentAuthSession = authSession;
          deferred.resolve(currentAuthSession);
        }, handleError);
      };

      if(authSessionId) {
        AuthSessionResource.getById(authSessionId).then(function(authSession){
          currentAuthSession = authSession;
          deferred.resolve(currentAuthSession);
        }, function(err){
          getAnonymousAuthSessionPromise(); // fall to anonymous
        });
      } else {
        getAnonymousAuthSessionPromise();
      }

      return deferred.promise;
    };

    this.getUser = function () {
      var authSession = getAuthSession();
      if (!authSession || !authSession.userId) {
        return $q(null);
      }
      return UserResource.getById(authSession.userId);
    };

    this.getAuthSession = function() {
      return currentAuthSession;
    }

    this.isAnonymous = function() {
      if(!currentAuthSession) {
        return true;
      }
      return currentAuthSession.anonymous;
    };

  }]);
