'use strict';

angular.module('upit-web.page.auth')
    .controller('AuthLoginController', ['$scope', '$location', 'AuthSession', function($scope, $location, AuthSession) {

    $scope.model = {

        loginForm: {
          userName: "",
          password: ""
        }

    };

    $scope.login = function() {
      AuthSession.login($scope.model.loginForm).then(function(results) {
        console.log(results);
      }).fail(function(err) {
        console.log(err);
      });
    };

}]);
