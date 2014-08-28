'use strict';

/**
 * @ngdoc function
 * @name yoJaxrsTutorialApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the yoJaxrsTutorialApp
 */
angular.module('upitWebSpa')
  .controller('AboutController', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
