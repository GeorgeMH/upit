'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # MainCtrl Controller of the upit
 */
angular.module('upit-web.page.main').controller('MainController', ['$scope', '$location', function ($scope, $location) {

  $location.path('/upload/');

}]);
