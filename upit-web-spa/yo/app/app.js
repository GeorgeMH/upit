'use strict';

/**
 * @ngdoc overview
 * @name upitWebSpa
 * @description
 * # upit
 *
 * Main module of the application.
 */
angular
  .module('upitWebSpa', [

    'upitWebSpa.navigation',
    'upitWebSpa.main',
    'upitWebSpa.paste',
    'upitWebSpa.upload',

     // Third party dependencies
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ]);
