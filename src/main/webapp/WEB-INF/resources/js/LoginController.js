'use strict';

var module = angular.module('app.controllers', []);

module.controller('LoginController', ["$scope", "LoginService",
    function ($scope, LoginService) {
        $scope.login = function () {
            LoginService.login($scope.user.email, $scope.user.password)
                .then(function success(response) {
                    console.log("content-length: " + response.headers("content-length"));
                    $scope.errorMessage = '';
                }, function error(response) {
                    $scope.errorMessage = 'Error!';
                    console.log("response.status: " + response.status);
                });
        }

    }
]);