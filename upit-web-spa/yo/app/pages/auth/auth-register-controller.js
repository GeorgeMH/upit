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
        authenticationType: ""
      }
    };

    $scope.registerUser = function () {

      AuthSessionResource.register($scope.model.registrationRequest)
        .then(function (data) {
          $location.path('/auth/login');
        }, function (err) {
          //TODO: Common form error handling?
          console.log('foo');
          console.log(err);
        });

    };

  }]);
