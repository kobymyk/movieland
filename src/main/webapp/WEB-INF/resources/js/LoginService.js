'use strict';

var module = angular.module('app.services', []);

module.factory('LoginService', ['$http', '$q',
    function ($http, $q) {
        var factory = {
            login: login
        };

        return factory;

        function login(email, password) {
            var deferred = $q.defer();

            $http.post("v1/login", {email:email, password:password}).then(
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
    }
]);