'use strict';
 
var app = angular.module('app',[]);

app.controller('LoginController', ['$scope', 'LoginService', function ($scope, LoginService) {

    $scope.login = function () {
        if ($scope.user != null && $scope.user.email) {
            LoginService.login($scope.user.email, $scope.user.password)
              .then (function success(response){
                  $scope.message = 'User added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding user!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }

}]);

app.service('LoginService', ['$http', function ($http) {

    this.login = function login(name, email){
        return $http({
          method: 'POST',
          url: 'login',
          data: {name:email, email:password}
        });
    }

}]);