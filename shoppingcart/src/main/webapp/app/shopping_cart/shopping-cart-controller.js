/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 * @author Tony Xu
 * @version 1.0.0
 * @created 2016/4/5
 */
'use strict';

var app = angular.module("shoppingCartModule", ['ProductListMvcServiceModule']);
app.controller("shoppingCartCtrl", ['productListMvcService', '$scope', '$filter',
    function (ProductListService, $scope, $filter) {

        /**
         * initialize the scope
         */
        $scope.init = function() {
            ProductListService.getShoppingCartWithContent().then(function (response) {
                $scope.shoppingCart = response.data;
            });

            $scope.chkAll = false;  // whether all the checkbox button are checked
            $scope.chkNum = 0;      // checked checkbox button number
        };

        /**
         * return the total price of products in cart item
         * @param item
         * @returns {number|*}
         */
        $scope.getItemTotalPrice = function (item) {
            item.totalPrice = item.product.price * item.productCount;
            return item.totalPrice;
        };

        /**
         * delete item in cart
         * @param item
         */
        $scope.deleteCartItem = function (item) {
            ProductListService.deleteCartItem(item).then(function(response) {
                $scope.shoppingCart = response.data;
            });
        };

        $scope.showShoppingCart = function() {
            ProductListService.getShoppingCart().then(function (response) {
                $scope.shoppingCart = response.data;
            });
        };

        $scope.updateShoppingCart = function(cartItem) {
            ProductListService.updateShoppingCart($scope.shoppingCart, cartItem).then(function(response) {
                $scope.shoppingCart = response.data;
            });
        };

        /**
         * handler of selectAll checkbox button's check event
         * @param chkAll
         */
        $scope.chkSelectAll = function (chkAll) {
            angular.forEach($scope.shoppingCart.items, function (item) {
                item.checked = chkAll;
            });
        };

        /**
         * watch the change of items in cart
         */
        $scope.$watch('shoppingCart.items', function (newValue, oldValue) {
            if (newValue == oldValue) {
                return;
            }

            $scope.chkNum = 0;

            angular.forEach(
                // count the checked checkbox button number
                $filter('filter')(newValue, {checked: true}), function () {
                    $scope.chkNum++;
                }
            );
            // chkAll is true if all checkbox are checked, else false
            $scope.chkAll = ($scope.chkNum == $scope.shoppingCart.items.length);
        }, true);

        /**
         * delete multiple items in cart
         */
        $scope.batchDelete = function () {
            var toBeDeletedProductIds = "";
            for (var i = $scope.shoppingCart.items.length - 1; i >= 0; i--) {
                if ($scope.shoppingCart.items[i].checked) {
                    toBeDeletedProductIds += $scope.shoppingCart.items[i].product.productId + ",";
                }
            }
            if (toBeDeletedProductIds != "") {
                toBeDeletedProductIds = toBeDeletedProductIds.substr(0, toBeDeletedProductIds.length - 1);
                ProductListService.batchDeleteCartItems(toBeDeletedProductIds).then(function(response) {
                    $scope.shoppingCart = response.data;
                });
            }
        };

        /**
         * move product in shopping cart to wish list
         * @param cartItem
         */
        $scope.moveCartItemToWishList = function (cartItem) {
            $scope.deleteCartItem(cartItem);
            ProductListService.moveCartItemToWishList(cartItem);
        };

        $scope.init();
    }]);

app.directive("validateNumber", function($parse) {
        return function (scope, element, attrs) {
            var modelGetter = $parse(attrs.validateNumber);
            var modelSetter = modelGetter.assign;

            element.bind('input', function() {
                var value = element.val();
                var regex = /^[0-9]{1,2}$/;
                if (!regex.test(value)) {
                    modelSetter(scope, "1");
                    element.val("1");
                }
            });
        }
    });