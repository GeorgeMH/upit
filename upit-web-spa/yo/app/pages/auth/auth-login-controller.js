'use strict';

angular.module('upit-web.page.auth')
  .controller('AuthLoginController', ['$scope', '$location', 'AuthSessionResource', function ($scope, $location, AuthSessionResource) {

    $scope.model = {

      loginForm: {
        userNameOrEmail: "",
        password: "",
        requestType: "sha512"
      }

    };

    $scope.login = function () {
      AuthSessionResource.login($scope.model.loginForm).then(function (results) {
        console.log(results);
      }, function (err) {
        console.log(err);
      });
    };

  }]);
