'use strict';

/**
 * @ngdoc function
 * @name upiWebSpa.main
 * @description # Main module definition
 */

angular.module('upit-web.page.main', [
  'upit-web.security',
  'ngRoute'
])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/', {
      templateUrl: 'pages/main/main.html',
      //controller: 'MainController'
      redirectTo: '/upload/'
    });

  }]);
