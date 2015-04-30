'use strict';

angular.module('upit-web.page.auth')
    .controller('AuthRegisterController', ['$scope', '$location', 'UserResource', function($scope, $location, UserResource) {

        $scope.model = {
            newUser: {
                userName: "",
                email: "",
                password: ""
            }
        };

        $scope.registerUser = function() {

            UserResource.create($scope.model.newUser).then(function(data){
                console.log('Got Data: ');
                console.log(data);
            }, function(err) {
                console.log(err);
            });

        };

    }]);
