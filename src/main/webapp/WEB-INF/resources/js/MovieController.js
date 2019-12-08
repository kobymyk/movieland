'use strict';

angular
    .module('app')
    .controller('MovieController', MovieController);

MovieController.$inject = ['$scope', 'NgTableParams', 'MovieService'];

function MovieController($scope, NgTableParams, MovieService) {
    var self = this;
    $scope.getAll = getAll;

    function getAll() {
        var promised = MovieService.getAll();

        promised.then(
            data => {
                console.log("data.length :" + data.length);
                self.movieTable = new NgTableParams({}, {dataset: data});
                //$location.path('/');
            },
            reason => {
                console.log("status :" + reason.status);
            }
        )
    }
};