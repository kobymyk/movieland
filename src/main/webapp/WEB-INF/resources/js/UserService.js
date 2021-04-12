'use strict';

angular
    .module('app')
    .factory('UserService', ['$http', '$q', function ($http, $q) {
        var factory = {
            getAll: getAll
        };

        return factory;

        function getAll() {
            var deferred = $q.defer();

            $http.get("v1/user").then(
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
    }
]);