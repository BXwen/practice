/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 * @author tyxu
 * @version 1.0.0
 * @created 4/13/2016
 */

'use strict';

var app = angular.module("ProductListMvcServiceModule", []);
app.service("productListMvcService", ["$http", function ($http) {
    var ProductListService;
    ProductListService = {

        /**
         * get product by productId
         * @param productId
         * @returns {HttpPromise}
         */
        getProduct: function (productId) {
            return $http.get("../shp/get-product?productId=" + productId);
        },

        /**
         * read product list info. from products.json
         * @returns {HttpPromise}
         */
        getProductList: function () {
            return $http.get("../shp/get-product-list");
        },

        /**
         *
         * @param shoppingCart
         * @param cartItem
         * @returns {HttpPromise}
         */
        updateShoppingCart: function (shoppingCart, cartItem) {
            return $http.post("../shp/update-shopping-cart/" + shoppingCart.shoppingCartId, cartItem);
        },

        /**
         *
         * @returns {HttpPromise}
         */
        getShoppingCart: function () {
            return $http.get("../shp/get-cart");
        },

        getShoppingCartWithContent: function () {
            return $http.get("../shp/show-cart");
        },

        /**
         * add product to shopping cart
         * @param product
         */
        addToCart: function (product) {

            return $http.post("../shp/add-to-cart", product);
        },

        /**
         * product ids separated by comma
         * @param productIds
         * @returns {HttpPromise}
         */
        batchDeleteCartItems: function (productIds) {
            return $http.delete('../shp/delete-products?productIds=' + productIds);
        },

        /**
         * delete item in cart
         * @param item
         */
        deleteCartItem: function (item) {
            return $http.delete('../shp/delete-product?productId=' + item.product.productId);
        },

        /**
         *
         * @returns {HttpPromise}
         */
        getWishList: function () {
            return $http.get("../shp/show-wish-list");
        },

        /**
         * add product into wish list
         * @param product
         */
        addToWishList: function (product) {
            $http.post("../shp/add-to-wish-list", product).then(function (response) {
                console.log("add to cart");
                return response.data;
            })
        },

        /**
         * delete item in wish list
         * @param wishItem
         */
        deleteWishItem: function (wishItem) {
            return $http.delete("../shp/delete-wish-list?productId=" + wishItem.product.productId);
        },

        /**
         * move product in shopping cart to wish list
         * @param cartItem
         */
        moveCartItemToWishList: function (cartItem) {
            // delete the item from shopping cart
            //this.deleteCartItem(cartItem);
            // add the product into user collection
            this.addToWishList(cartItem.product);
        },

        /**
         * move product in wish list to shopping cart
         * @param wishItem
         */
        moveToCartFromWishList: function (wishItem) {
            this.addToCart(wishItem.product);
        }
    };

    return ProductListService;
}]);