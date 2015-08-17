'use strict';

angular.module('upit-web.page.auth')
  .controller('AuthRegisterController', ['$scope', '$location', 'AuthSessionResource', function ($scope, $location, AuthSessionResource) {

    $scope.model = {

      registrationRequest: {
        requestedUser: {
          userName: "",
          email: ""
        },

        password: "",
        authenticationType: "sha512"
      }
    };

    $scope.registerUser = function () {

      AuthSessionResource.register($scope.model.registrationRequest)
        .then(function (authSession) {
          $location.path('/auth/login');
        }, function (err) {
          //TODO: Common form error handling, flash somehting on the screen for the error
          console.log(err);
        });

    };

  }]);
