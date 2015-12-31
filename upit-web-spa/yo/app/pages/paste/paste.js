'use strict';

/**
 * @ngdoc function
 * @name upit.controller:MainCtrl
 * @description # Paste Module
 */
angular.module('upit-web.page.paste', [
  'upit-web.common.upitRestApi',
  'upit-web.common.security',

  'ngRoute'
])
  .config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('/paste/:pasteIdHash?', {
      templateUrl: 'pages/paste/paste.html',
      controller: 'PasteController',
      resolve: {
        resolvedPaste: ['$route', 'PasteResource', function ($route, PasteResource) {
          // Conditionally resolve the paste by the ID in the URL
          if ($route.current.params.pasteIdHash) {
            return PasteResource.getByIdHash($route.current.params.pasteIdHash);
          }
          return null;
        }]
      }
    });

  }]);
