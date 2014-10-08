'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upitWebSpa.paste', [
    'upitWebSpa.upitRestApi',

    'ngRoute'
  ])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    .when('/paste/:pasteId?', {
      templateUrl: 'paste/paste.html',
      controller: 'PasteCtrl',
      resolve: {
          paste: ['$route', 'PasteResource', function($route, PasteResource) {
            // Conditionally resolve the paste by the ID in the URL
            if($route.current.params.pasteIdHash) {
                return PasteResource.getByIdHash($route.current.params.pasteIdHash);
            }
            return null;
          }]
      }
    });

  }]);