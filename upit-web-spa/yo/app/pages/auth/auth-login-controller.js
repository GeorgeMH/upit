'use strict';

angular.module('upit-web.page.auth')
  .controller('AuthLoginController', ['$scope', '$location', 'AuthSessionResource', 'SecurityService', function ($scope, $location, AuthSessionResource, SecurityService) {

    $scope.model = {

      loginForm: {
        userNameOrEmail: "",
        password: "",
        requestType: "sha512"
      }

    };

    $scope.login = function () {
      AuthSessionResource.login($scope.model.loginForm).then(function (results) {
        console.log("Logged in: " + results);
        SecurityService.setAuthSession(results);
      }, function (err) {
        console.log(err);
      });
    };

  }]);
