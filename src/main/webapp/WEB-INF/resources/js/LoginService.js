'use strict';

var module = angular.module('app.services', []);

module.factory('LoginService', ["$http", function ($http) {
    var service = {};

    service.login = function login(email, password){
        return $http({
            method: 'POST',
            url: 'v1/login',
            data: {email:email, password:password}
        });
    }

    return service;

}]);