'use strict';

angular.module('upit-web.page.auth')
  .controller('AuthRegisterController', ['$scope', '$location', 'AuthSessionResource', 'SecurityService', function ($scope, $location, AuthSessionResource, SecurityService) {

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
          SecurityService.setAuthSession(authSession);
        }, function (err) {
          //TODO: Common form error handling
          console.log(err);
        });

    };

  }]);
