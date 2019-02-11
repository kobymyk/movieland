'use strict';

angular
    .module('app')
    .controller('LoginController', LoginController);

LoginController.$inject = ['$scope', '$location', '$window', 'LoginService'];

function LoginController($scope, $location, $window, LoginService) {
    $scope.login = login;

    function login() {
        let userObj = {
            email: $scope.user.email,
            password: $scope.user.password
        }
        var promised = LoginService.login(userObj);

        promised.then(
            value => {
                let userData = JSON.stringify(value);
                console.log("userData :" + userData);
                $window.sessionStorage.setItem("userData", userData);
                $location.path('/');
            },
            reason => {
                console.log("status :" + reason.status);
            }
        )
    }
};