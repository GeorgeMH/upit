'use strict';

/**
 * @ngdoc function
 * @name upitWebSpa.Navigation
 * @description # Navigation Controller
 */
angular.module('upit-web.components.navigation')
  .controller('NavigationController', ['$scope', '$location', '$timeout', 'SecurityService', function ($scope, $location, $timeout, SecurityService) {

    $scope.model = {
      isUserLoggedIn: false
    };

    $scope.menuClass = function (page) {
      var currentPath = $location.path().substring(1);
      var slashIdx = currentPath.indexOf('/');
      if (slashIdx > 0) {
        currentPath = currentPath.substring(0, slashIdx);
      }
      return page === currentPath ? "active" : "";
    };

  }]);
