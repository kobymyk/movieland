'use strict';

var module = angular.module('app.controllers', []);

module.controller('LoginController', LoginController);

LoginController.$inject = ['$scope', 'LoginService'];

function LoginController($scope, LoginService) {
    $scope.login = login;

    function login() {
        var promised = LoginService.login($scope.user.email, $scope.user.password);

        promised.then(
            function(value) {
                console.log("value :" + value);
            },
            function(reason) {
                console.log("status :" + reason.status);
            }
        );
    }
};