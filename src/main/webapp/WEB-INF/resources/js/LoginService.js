'use strict';

angular
    .module('app')
    .factory('LoginService', ['$http', '$q', function ($http, $q) {
        var factory = {
            login: login,
            logout: logout
        };

        return factory;

        function login(email, password) {
            var deferred = $q.defer();
            var user = {email: email, password: password};

            $http.post("v1/login", user).then(
                function(response) {
                    var result = response.data;
                    $http.defaults.headers.common['uuid'] = result.uuid;
                    deferred.resolve(result);
                },
                function(error) {
                    console.log("statusText: " + error.statusText);
                    deferred.reject(error);
                }
            )
            return deferred.promise;
        }

        function logout() {
            var deferred = $q.defer();

            $http.delete("v1/logout").then(
                function(response) {
                    $http.defaults.headers.common['uuid'] = '';
                    deferred.resolve(response);
                },
                function(error) {
                    console.log("statusText: " + error.statusText);
                    deferred.reject(error);
                }
            )
            return deferred.promise;
        }
    }
]);