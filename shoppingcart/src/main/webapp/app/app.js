'use strict';
angular.module('AngularSpringApp', ['ngRoute', 'goodsListModule', 'shoppingCartModule',
                                        'goodsDetailModule', 'wishListModule']);

angular.module('AngularSpringApp')

    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/goodslist', {
                    templateUrl: 'shopping_cart/goods-list.html',
                    controller: 'goodsListCtrl'
                })
                .when('/shoppingCart/:userId', {
                    templateUrl: 'shopping_cart/shopping-cart.html',
                    controller: 'shoppingCartCtrl'
                })
                .when('/goodsDetail/:productId', {
                    templateUrl: 'shopping_cart/goods-detail.html',
                    controller: 'goodsDetailCtrl'
                })
                .when('/wishList', {
                    templateUrl: 'shopping_cart/wish-list.html',
                    controller: 'wishListCtrl'
                })
                /* Anything else go to goods list page */
                .otherwise({
                    redirectTo: '/goodslist'
                })

        }]);