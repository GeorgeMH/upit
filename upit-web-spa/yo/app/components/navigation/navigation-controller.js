'use strict';

/**
 * @ngdoc function
 * @name upitWebSpa.Navigation
 * @description # Navigation Controller
 */
angular.module('upitWebSpa.navigation')
  .controller('NavigationCtrl', function($scope, $location) {

    $scope.menuClass = function(page) {
        var currentPath = $location.path().substring(1);
        return page === currentPath ? "active" : "";
    };

  });
