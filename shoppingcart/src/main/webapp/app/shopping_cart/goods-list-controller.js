/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 * @author Tony Xu
 * @version 1.0.0
 * @created 2016/4/5
 */
'use strict';

var app = angular.module('goodsListModule', ['ProductListMvcServiceModule']);
app.controller("goodsListCtrl", ['productListMvcService', '$scope', '$location',
    function (ProductListService, $scope, $location) {

        /**
         * load product info. & display
         */
        $scope.showGoodsList = function () {
            // ../app/shoppingcart/json/products.json
            // load products info for the first time
            if ($scope.productList.length == 0) {
                ProductListService.getProductList().then(function(response) {
                    $scope.productList = response.data;
                });
            }

            $scope.getShoppingCart();
        };

        /**
         * add product to shopping cart
         * @param product
         */
        $scope.addToShoppingCart = function (product) {
            ProductListService.addToCart(product).then(function (response) {
                if (response.status == 200) {
                    console.log("Add to cart successfully");
                    $scope.shoppingCart = response.data;
                }
            });
        };

        /**
         * get shopping cart
         */
        $scope.getShoppingCart = function() {
            ProductListService.getShoppingCart().then(function (response) {
                console.log("get shopping cart");
                $scope.shoppingCart = response.data;
            });
        };

        /**
         * go to shopping cart
         */
        $scope.goToShoppingCart = function () {
            $location.url("shoppingCart/" + $scope.shoppingCart.userId);
        };

        /**
         * go to wish list
         */
        $scope.goToWishList = function () {
            $location.url("wishList/");
        };

        /**
         * save product into wish list
         * @param product
         */
        $scope.addToWishList = function (product) {
            $scope.wishList = ProductListService.addToWishList(product);
            $scope.getWishList();
        };

        $scope.getWishList = function() {
            ProductListService.getWishList().then(function (response) {
                console.log("get wish list");
                $scope.wishList = response.data;
                console.log($scope.wishList);
            });
        };

        /**
         * initialize the scope
         */
        $scope.init = function () {
            $scope.userId = "12345";
            $scope.productList = [];
            $scope.wishList = [];

            $scope.showGoodsList();
            $scope.getWishList();
        };

        $scope.init();
    }]);