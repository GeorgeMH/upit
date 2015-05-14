'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upit-web.page.upload')
    .filter('humanFileSize', function () {

        return function(input) {
            if(0 == input){
                return '0 B';
            }
            var i = Math.floor( Math.log(input) / Math.log(1024) );
            return ( input / Math.pow(1024, i) ).toFixed(2) * 1 + ' ' + ['B', 'kB', 'MB', 'GB', 'TB'][i];
        };

    });
