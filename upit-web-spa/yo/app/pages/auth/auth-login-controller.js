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

    };

}]);
