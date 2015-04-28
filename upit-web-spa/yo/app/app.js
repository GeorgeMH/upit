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
    'upitWebSpa.auth',
    'upitWebSpa.user',
    'upitWebSpa.main',
    'upitWebSpa.paste',
    'upitWebSpa.upload',


    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap'
  ]);
