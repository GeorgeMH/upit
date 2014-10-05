'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.paste').controller('ShowPasteCtrl',
    ['$scope', 'PasteResource', 'paste', function ($scope, PasteResource, paste) {

        var model = {
            paste: paste
        };

        $scope.model = model;


    }]);
