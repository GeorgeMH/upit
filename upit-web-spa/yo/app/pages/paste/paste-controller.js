'use strict';

/**
 * @ngdoc function
 * @name upit.controller:PasteCtrl
 * @description
 * # PasteCtrl
 * PasteCtrl of upit
 */
angular.module('upit-web.page.paste').controller('PasteController',
  ['$scope', '$location', '$filter', '$timeout', 'PasteResource', 'resolvedPaste', function ($scope, $location, $filter, $timeout, PasteResource, resolvedPaste) {

    var model = {
      paste: resolvedPaste,

      showLineNumbers: true,

      languages: $filter('orderBy')($filter('filter')(Object.keys(Prism.languages), function (value) {
        return 'DFS' !== value && 'extend' !== value && 'insertBefore' != value;
      }), function (value) {
        return value;
      }),

      newPasteForm: {
        text: resolvedPaste ? resolvedPaste.text : "",
        userId: null,
        parentId: resolvedPaste ? resolvedPaste.id : "",
        syntaxId: resolvedPaste ? resolvedPaste.syntaxId : ""
      }
    };

    $scope.model = model;

    var resetHighlighting = function () {
      if (!model || !model.paste || !model.paste.syntaxId) {
        return;
      }
      $timeout(function () {
        var codeBlock = $('#codeBlock');
        if (codeBlock) {
          Prism.highlightElement(codeBlock.find('code')[0]);
        }
      }, 200);
    }

    $scope.$watch('model.paste.text', resetHighlighting);
    $scope.$watch('model.showLineNumbers', resetHighlighting);


    $scope.createPaste = function () {
      var paste = angular.copy(model.newPasteForm);

      PasteResource.create(paste).then(function (createdPaste) {
        $location.path('/paste/' + createdPaste.idHash);
      }, function (failure) {
        console.log('failure! ' + failure);
      });
    };

    $scope.getPasteLanguageClass = function () {
      if (!model.paste || !model.paste.syntaxId) {
        return null;
      }

      if (Prism.languages[model.paste.syntaxId]) {
        return 'language-' + model.paste.syntaxId;
      }
      return null;
    };

  }]);
