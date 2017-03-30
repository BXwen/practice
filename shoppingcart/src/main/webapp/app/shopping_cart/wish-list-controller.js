/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 * @author Tony Xu
 * @version 1.0.0
 * @created 2016/4/5
 */
'use strict';

var app = angular.module("wishListModule", ['ProductListMvcServiceModule']);
app.controller("wishListCtrl", ['$scope', 'productListMvcService', function ($scope, ProductListService) {

    /**
     * initialize the scope
     */
    $scope.init = function() {
        $scope.showWishList();

    };

    /**
     *
     */
    $scope.showWishList = function() {
        ProductListService.getWishList().then(function (response) {
            console.log("get wish list");
            $scope.wishList = response.data;
            console.log($scope.wishList);
        });
    };

    /**
     * move product in wish list to shopping cart
     * @param wishItem
     */
    $scope.moveToCart = function (wishItem) {
        $scope.deleteWishItem(wishItem);
        ProductListService.moveToCartFromWishList(wishItem);
    };

    /**
     * delete item in wish list
     * @param wishItem
     */
    $scope.deleteWishItem = function (wishItem) {
        $scope.wishList = ProductListService.deleteWishItem(wishItem).then(function(response) {
            $scope.wishList = response.data;
            console.log($scope.wishList);
        });

    };

    $scope.init();
}]);
