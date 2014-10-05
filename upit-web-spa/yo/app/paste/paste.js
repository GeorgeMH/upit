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
    .when('/paste/:id?', {
      templateUrl: 'paste/paste.html',
      controller: 'PasteCtrl',
      resolve: {
          paste: function(PasteResource, $route){
            if($route.current.params.id) {
                return PasteResource.getById($route.current.params.id);
            }
            return null;
          }
      }
    });

  }]);