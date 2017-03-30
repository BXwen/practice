package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.ShoppingCart;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/13/2016
 */
public interface ShoppingCartDAO {
    /**
     * get shopping cart by shopping cart id
     * @param userId
     * @return
     */
    ShoppingCart getShoppingCartByUserId(String userId);

    /**
     * get shopping cart by shoppingCartId
     * @param shoppingCartId
     * @return
     */
    ShoppingCart getShoppingCart(Long shoppingCartId);

    /**
     * create shopping cart for user
     * @param userId
     * @return shopping_cart_id
     */
    Long createShoppingCart(String userId);

    /**
     * update
     * @param shoppingCart
     */
    void updateShoppingCart(ShoppingCart shoppingCart);
}
