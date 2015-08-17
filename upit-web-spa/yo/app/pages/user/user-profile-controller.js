'use strict';

angular.module('upit-web.page.user').controller('UserProfileController', ['$scope', '$location', 'resolvedUser', function ($scope, $location, resolvedUser) {

  $scope.user = angular.copy(resolvedUser);

}]);
