'use strict';

angular
    .module('app')
    .controller('UserController', UserController);

UserController.$inject = ['$scope', 'NgTableParams', 'UserService'];

function UserController($scope, NgTableParams, UserService) {
    var self = this;
    $scope.getAll = getAll;

    function getAll() {
        var promised = UserService.getAll();

        promised.then(
            data => {
                console.log("data.length :" + data.length);
                self.userTable = new NgTableParams({}, {dataset: data});
                //$location.path('/');
            },
            reason => {
                console.log("status :" + reason.status);
            }
        )
    }
};