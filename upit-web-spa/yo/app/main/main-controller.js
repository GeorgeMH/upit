'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # MainCtrl Controller of the upit
 */
angular.module('upitWebSpa.main')
  .controller('MainCtrl', ['$scope', '$location', function($scope, $location) {

    $location.path('/upload');

  }]);
