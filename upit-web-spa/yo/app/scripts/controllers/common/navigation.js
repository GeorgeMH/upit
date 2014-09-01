'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # MainCtrl Controller of the upit
 */
angular.module('upitWebSpa').controller('NavigationCtrl', function($scope, $location) {

    $scope.menuClass = function(page) {
        var currentPath = $location.path().substring(1);
        return page === currentPath ? "active" : "";
    };

});
