'use strict';

angular
    .module('app')
    .controller('HomeController', HomeController);

HomeController.$inject = ['$scope', '$location', '$window', 'LoginService'];

function HomeController($scope, $location, $window, LoginService) {
    var self = this;

    self.email = "xxx";
    $scope.name = "yyy";

    $scope.logout = logout;

    function logout() {
        var promised = LoginService.logout();

        promised.then(
            value => {
                $window.sessionStorage.removeItem("userData");
                console.log("value :" + value);
            },
            reason => {
                console.log("status :" + reason.status);
            }
        )
    }
};