'use strict';

/**
 * @ngdoc function
 * @name upit.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of upit
 */
angular.module('upitWebSpa.paste').controller('CreatePasteCtrl',
    ['$scope', '$location', 'PasteResource', function ($scope, $location, PasteResource) {

        var model = {
            pasteForm: {
                text: "",
                userId: null,
                parentId: null,
                syntaxId: null
            }
        };

        $scope.model = model;

        $scope.createPaste = function() {
            var paste = angular.copy(model.pasteForm);

            PasteResource.create(paste).then(function(createdPaste) {
                $location.path('/paste/' + createdPaste.id);
            }, function(failure) {
                console.log('failure! ' + failure);
            });
        };

    }]);
