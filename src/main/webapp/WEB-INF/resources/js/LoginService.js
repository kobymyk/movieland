'use strict';

angular
    .module('app')
    .factory('LoginService', ['$http', '$q', function ($http, $q) {
        var factory = {
            login: login,
            logout: logout
        };

        return factory;

        function login(userObj) {
            var deferred = $q.defer();
            let userData = JSON.stringify(userObj);

            $http.post("v1/login", userData).then(
                response => {
                    let data = response.data;
                    console.log("data=", data);
                    $http.defaults.headers.common['Authorization'] = data.token;

                    deferred.resolve(data);
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
                    $http.defaults.headers.common['Authorization'] = null;

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