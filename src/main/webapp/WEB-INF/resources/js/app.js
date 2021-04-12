'use strict';
 
angular.module('app', ['ngRoute', 'ngTable'])
    .config(config)
    .run(run);

config.$inject = ['$routeProvider', '$locationProvider'];
function config($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'resources/home.view.html',
            controllerAs: '$home'
        })
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'resources/login.view.html',
            controllerAs: '$login'
        })
        .when('/movie', {
                    controller: 'MovieController',
                    templateUrl: 'resources/movie.view.html',
                    controllerAs: '$movie'
        })
        .when('/user', {
                    controller: 'UserController',
                    templateUrl: 'resources/user.view.html',
                    controllerAs: '$user'
        })
        .otherwise({
            redirectTo: '/login'
        });
}

run.$inject = ['$rootScope', '$location', '$http', '$window'];
function run($rootScope, $location, $http, $window) {
    //var userData = $window.localStorage.getItem('userData');
    var userData = $window.sessionStorage.getItem('userData');
    if (userData) {
        try {
            let userObj = JSON.parse(userData);
           // $http.defaults.headers.common['uuid'] = userObj.uuid;
        } catch(e) {
            console.log("Error :" + e.message);
        }
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        console.log("next :" + next);
        var restrictedPage = $.inArray($location.path(), ['/login']) === -1;
        let userData = $window.sessionStorage.getItem('userData');

        if (restrictedPage && !userData) {
            $location.path('/login');
        }
    });
};
