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
            var paste = angular.copy($scope.pasteForm);

            PasteResource.create(paste).then(function(createdPaste){
                console.log('success! ' + createdPaste);
            }, function(failure){
                console.log('failure! ' + failure);
            });
        };

  });
