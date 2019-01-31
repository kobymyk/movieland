'use strict';

angular
    .module('app')
    .controller('LoginController', LoginController);

LoginController.$inject = ['$scope', '$location', '$window', 'LoginService'];

function LoginController($scope, $location, $window, LoginService) {
    $scope.login = login;

    function login() {
        var promised = LoginService.login($scope.user.email, $scope.user.password);

        promised.then(
            function(value) {
                console.log("value :" + value);
                $window.sessionStorage.setItem("userData", value);
                $location.path('/');
            },
            function(reason) {
                console.log("status :" + reason.status);
            }
        );
    }
};