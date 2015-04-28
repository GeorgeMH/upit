'use strict';


angular.module('upitWebSpa.auth', [
    'upitWebSpa.upitRestApi',
    'ngRoute'
]).config(['$routeProvider', function ($routeProvider) {

    $routeProvider.when('auth/login/', {
        templateUrl: 'auth/auth-login.html',
        controller: 'AuthLoginController',
        resolve: {
            resolvedUser: {}
            //resolvedPaste: ['$route', 'PasteResource', function($route, PasteResource) {
            //    // Conditionally resolve the paste by the ID in the URL
            //    if($route.current.params.pasteIdHash) {
            //        return PasteResource.getByIdHash($route.current.params.pasteIdHash);
            //    }
            //    return null;
            //}]
        }
    });

    $routeProvider.when('auth/logout', {
        templateUrl: 'auth/auth-logout.html',
        controller: 'AuthLogoutController',
        resolve: {
            resolvedUser: {}
            //resolvedPaste: ['$route', 'PasteResource', function($route, PasteResource) {
            //    // Conditionally resolve the paste by the ID in the URL
            //    if($route.current.params.pasteIdHash) {
            //        return PasteResource.getByIdHash($route.current.params.pasteIdHash);
            //    }
            //    return null;
            //}]
        }
    });

}]);