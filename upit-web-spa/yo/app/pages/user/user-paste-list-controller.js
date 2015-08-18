'use strict';

angular.module('upit-web.page.user').controller('UserPasteListController', ['$scope', '$location', 'resolvedUser', function ($scope, $location, resolvedUser) {

  $scope.user = angular.copy(resolvedUser);

}]);
