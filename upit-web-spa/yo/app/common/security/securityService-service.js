/**
 * This service manages the 'Authenticated State' of the current User.
 */
angular.module('upit-web.common.security')
  .service('SecurityService', ['$q', '$cookies', 'AuthSessionResource', 'UserResource', function ($q, $cookies, AuthSessionResource, UserResource) {

    var AUTH_SESSION_ID_COOKIE_NAME = "authSessionId";
    var VALIDATE_TOKEN_INTERVAL = 1000 * 60; // 1 minutes
    var currentAuthSession = null;
    var validateTokenInterval = null;

    var isFetchingToken = false;

    this.init = function (updatedAuthSessionCallback) {
      if(false === isFetchingToken && (null === currentAuthSession || null === validateTokenInterval)) {
        var handleError = function(err){
          clearInterval(validateTokenInterval);
          currentAuthSession = null;
          updatedAuthSessionCallback(currentAuthSession);
          console.log('TODO: Validate AuthSession failed: ' + err);
        };
        isFetchingToken = true;
        setAuthSession().then(function() {
          isFetchingToken = false;
          console.log("AuthSession: " + currentAuthSession.id + " : userId: " + currentAuthSession.userId);

          // Set an interval to validate the AuthSession every so often
          validateTokenInterval = setInterval(function() {
            AuthSessionResource.validate(currentAuthSession.id).then(function(validatedCurrentAuthSession){
              console.log("Validated auth session: " + validatedCurrentAuthSession);
              currentAuthSession = validatedCurrentAuthSession;
              updatedAuthSessionCallback(currentAuthSession);
            }, handleError);
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
