'use strict';

angular
    .module('app')
    .factory('MovieService', ['$http', '$q', function ($http, $q) {
        var factory = {
            getAll: getAll,
            logout: logout
        };

        return factory;

        function getAll() {
            var deferred = $q.defer();
            //let userData = JSON.stringify(userObj);

            $http.get("v1/movie").then(
                response => {
                    console.log("response: " + response);
                    let result = response.data;
                    console.log("statusText: " + response.statusText);

                    deferred.resolve(result);
                },
                error => {
                    console.log("statusText: " + error.statusText);

                    deferred.reject(error);
                }
            )

            return deferred.promise;
        }

        function logout() {
            var deferred = $q.defer();

            $http.delete("v1/logout").then(
                response => {
                    $http.defaults.headers.common['uuid'] = '';
                    console.log("statusText: " + error.statusText);

                    deferred.resolve(response.status);
                },
                error => {
                    console.log("statusText: " + error.statusText);

                    deferred.reject(error);
                }
            )

            return deferred.promise;
        }
    }
]);