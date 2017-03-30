package com.springapp.mvc.shoppingcart.service;

import com.springapp.mvc.shoppingcart.model.CartItem;
import com.springapp.mvc.shoppingcart.model.Product;
import com.springapp.mvc.shoppingcart.model.ShoppingCart;

import java.util.List;


/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/13/2016
 */
public interface ShoppingCartManager {

    /**
     * get all products
     *
     * @return
     */
    List<Product> getAllProducts();

    /**
     * get product by id fron table PRODUCT
     *
     * @param id
     * @return
     */
    Product getProductById(long id);

    /**
     * create shopping cart for user
     *
     * @param userId
     * @return
     */
    Long createShoppingCart(String userId);

    /**
     * get shopping cart
     *
     * @param userId
     * @return
     */
    ShoppingCart getShoppingCartByUserId(String userId);

    /**
     * add product into user's shopping cart
     *
     * @param userId
     * @param product
     */
    void addToShoppingCart(String userId, Product product);

    /**
     * get user's shopping cart content
     *
     * @param shoppingCartId
     * @return
     */
    List<CartItem> getCartItems(Long shoppingCartId);

    /**
     * delete cart item from shopping cart
     *
     * @param shoppingCartId
     * @param productId
     * @return
     */
    ShoppingCart deleteCartItem(Long shoppingCartId, Long productId);

    /**
     * update cart item
     *
     * @param cartItem
     * @return
     */
    void updateCartItem(CartItem cartItem);

    /**
     * update shopping cart
     *
     * @param shoppingCart
     */
    void updateShoppingCart(ShoppingCart shoppingCart);
}
