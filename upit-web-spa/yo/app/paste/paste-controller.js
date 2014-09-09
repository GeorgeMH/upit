'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.paste')
  .controller('PasteCtrl', function ($scope, PasteResource) {

        $scope.pasteForm = {
            text: "",
            userId: 0,
            parentId: null,
            syntaxId: null
        };

        $scope.createPaste = function() {
            console.log('bleh: ' + PasteResource);

            var paste = angular.copy($scope.pasteForm);

            var foo = PasteResource.create(paste);
            //var foo = PasteResource.get(1);
            console.log('' + foo);
                /*.then(function(paste){
                console.log('success! ' + paste);
            }, function(failure){
                console.log('failure! ' + failure);
            });*/
        };

  });
