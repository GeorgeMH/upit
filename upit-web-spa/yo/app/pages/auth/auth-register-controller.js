'use strict';

angular.module('upit-web.page.auth')
    .controller('AuthRegisterController', ['$scope', '$location', 'UserResource', function($scope, $location, UserResource) {

        $scope.model = {
            newUser: {
                userName: "",
                email: ""
            }
        };

        $scope.registerUser = function() {

            UserResource.create($scope.model.newUser).then(function(data) {
              console.log('Registered User:', data);
              $location.path('/auth/login');
            }, function(err) {
                //TODO:
                console.log(err);
            });

        };

    }]);
