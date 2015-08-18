'use strict';

angular.module('upit-web.page.user').controller('UserFileListController',
  ['$scope', '$location', 'resolvedUser', 'UploadedFileResource',
    function ($scope, $location, resolvedUser, UploadedFileResource) {

  var init = function() {
    $scope.user = angular.copy(resolvedUser);
    $scope.model = {
      isDataLoading: true
    };
    loadFiles();
  };

  var loadFiles = function() {
    $scope.model.isDataLoading = true;
    // TODO Pagination later, find a real frontend control for this
    UploadedFileResource.getByUserId($scope.user.id).then(function(uploadedFileList){
      angular.merge($scope.model.files, uploadedFileList);
    }, function(err){
      console.logger("Failed fetching files for user: " + $scope.user.id);
    });
  };





 init();

}]);
