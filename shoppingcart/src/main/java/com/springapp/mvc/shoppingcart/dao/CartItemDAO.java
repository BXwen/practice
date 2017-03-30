package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.CartItem;

import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/22/2016
 */
public interface CartItemDAO {
    /**
     * get items in shopping cart by shopping cart id
     * @param shoppingCartId
     * @return
     */
    List<CartItem> getCartItemsByShoppingCartId(long shoppingCartId);

    /**
     *
     * @param shoppingCartId
     * @param productId
     * @return
     */
    CartItem getCartItemFromCart(Long shoppingCartId, Long productId);

    /**
     * insert
     * @param cartItem
     */
    void insertCartItem(CartItem cartItem);

    /**
     * update
     * @param cartItem
     */
    void updateCartItem(CartItem cartItem);

    /**
     * delete cart item from shopping cart
     * @param shoppingCartId
     * @param productId
     */
    void deleteCartItemByProductId(Long shoppingCartId, Long productId);
}
