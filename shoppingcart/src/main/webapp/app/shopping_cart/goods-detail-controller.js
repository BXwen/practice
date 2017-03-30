/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 * @author Tony Xu
 * @version 1.0.0
 * @created 2016/4/5
 */
'use strict';

var app = angular.module("goodsDetailModule", ['ProductListMvcServiceModule']);
app.controller("goodsDetailCtrl", ['$scope', 'productListMvcService', '$routeParams',
    function ($scope, ProductListService, $routeParams) {
        var productId = $routeParams.productId;
        $scope.selectedProduct = {};
        ProductListService.getProduct(productId).then(function(response) {
            $scope.selectedProduct = response.data;
        });
    }]);