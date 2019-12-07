'use strict';

angular
    .module('app')
    .controller('MovieController', MovieController);

MovieController.$inject = ['$scope', '$location', '$window', 'LoginService'];

function MovieController($scope, $location, $window, MovieService) {
    var self = this;
    $scope.getAll = getAll;

    function getAll() {
        var promised = MovieService.getAll();

        promised.then(
            value => {
                let result = JSON.stringify(value);
                console.log("result :" + result);
                $scope.movies = result;
                self.movies = {};
                //$location.path('/');
            },
            reason => {
                console.log("status :" + reason.status);
            }
        )
    }
};