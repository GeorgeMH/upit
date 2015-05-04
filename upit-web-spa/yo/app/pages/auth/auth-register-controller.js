'use strict';

angular.module('upit-web.page.auth')
    .controller('AuthRegisterController', ['$scope', '$location', 'UserResource', function($scope, $location, UserResource) {

        $scope.model = {
            newUser: {
                userName: "",
                email: ""
            },
            authenticationMetaData: {
              password: "",
              authenticationProviderURI: "",
              userId: -1
            }
        };

        $scope.registerUser = function() {

            UserResource.register({
              requestedUser: $scope.model.newUser,
              authenticationMetaData: $scope.model.authenticationMetaData})
              .then(function(data) {
              $location.path('/auth/login');
            }, function(err) {
              //TODO: Common form error handling?
              alert('failed: ', err);
            });

        };

    }]);
