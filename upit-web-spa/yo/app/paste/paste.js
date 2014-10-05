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

    $routeProvider.when('/paste', {
      templateUrl: 'paste/createPaste.html',
      controller: 'CreatePasteCtrl'
    })
    .when('/paste/:id', {
      templateUrl: 'paste/showPaste.html',
      controller: 'ShowPasteCtrl',
      resolve: {
          paste: function(PasteResource, $route){
            return PasteResource.getById($route.current.params.id);
          }
      }
    });

  }]);