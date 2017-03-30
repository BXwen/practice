package com.springapp.mvc.shoppingcart.service;

import com.springapp.mvc.shoppingcart.dao.CartItemDAO;
import com.springapp.mvc.shoppingcart.dao.ProductDAO;
import com.springapp.mvc.shoppingcart.dao.ShoppingCartDAO;
import com.springapp.mvc.shoppingcart.model.CartItem;
import com.springapp.mvc.shoppingcart.model.Product;
import com.springapp.mvc.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/20/2016
 */
@Service
public class ShoppingCartManagerImpl implements ShoppingCartManager {

    @Autowired
    ShoppingCartDAO shoppingCartDAO;

    @Autowired
    CartItemDAO cartItemDAO;

    @Autowired
    ProductDAO productDAO;

    private final static String webRoot = System.getProperty("webapp.root");

    private final static String filePathProduct = webRoot + File.separatorChar + "product.json";

    /**
     * create shopping cart for user
     *
     * @param userId
     * @return
     */
    @Override
    public Long createShoppingCart(String userId) {
        return shoppingCartDAO.createShoppingCart(userId);
    }

    /**
     * get shopping cart
     *
     * @param userId
     * @return
     */
    @Override
    public ShoppingCart getShoppingCartByUserId(String userId) {
        return shoppingCartDAO.getShoppingCartByUserId(userId);
    }

    /**
     * get all products
     *
     * @return
     */
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    /**
     * get product by id fron table PRODUCT
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(long id) {
        return productDAO.getProductById(id);
    }

    /**
     * add product into user's shopping cart
     *
     * @param userId
     * @param product
     */
    public void addToShoppingCart(String userId, Product product) {
        ShoppingCart userCart = this.getShoppingCartByUserId(userId);
        CartItem cartItem = cartItemDAO.getCartItemFromCart(userCart.getShoppingCartId(), product.getProductId());
        if (cartItem == null) {
            // product does not exist in cart.
            cartItem = new CartItem();
            cartItem.setShopping_cart_id(userCart.getShoppingCartId());
            cartItem.setProduct_id(product.getProductId());
            cartItem.setProductCount(1);
            cartItem.setTotalPrice(product.getPrice());
            cartItem.setChecked(false);
            // insert
            cartItemDAO.insertCartItem(cartItem);
        } else {
            // exists, update cart item
            cartItem.setProductCount(cartItem.getProductCount() + 1);
            cartItem.setTotalPrice(cartItem.getProductCount() * product.getPrice());
            cartItemDAO.updateCartItem(cartItem);
        }
        // update shopping cart
        userCart.setTotalCount(userCart.getTotalCount() + 1);
        userCart.setTotalCost(userCart.getTotalCost() + product.getPrice());
        shoppingCartDAO.updateShoppingCart(userCart);
    }

    /**
     * get user's shopping cart content
     *
     * @param shoppingCartId
     * @return
     */
    @Override
    public List<CartItem> getCartItems(Long shoppingCartId) {
        List<CartItem> retList = cartItemDAO.getCartItemsByShoppingCartId(shoppingCartId);
        // get product info for each item
        int lenth = retList.size();
        for (int i = 0; i < lenth; i++) {
            CartItem item = retList.get(i);
            item.setProduct(productDAO.getProductById(item.getProduct_id()));
        }
        return retList;
    }

    /**
     * delete cart item from shopping cart
     *
     * @param shoppingCartId
     * @param productId
     * @return
     */
    @Override
    public ShoppingCart deleteCartItem(Long shoppingCartId, Long productId) {
        // get the cart item to be deleted
        CartItem toBeDeleted = cartItemDAO.getCartItemFromCart(shoppingCartId, productId);

        // delete items from table cart_item
        cartItemDAO.deleteCartItemByProductId(shoppingCartId, productId);

        // update record in shopping_cart
        ShoppingCart cart = shoppingCartDAO.getShoppingCart(shoppingCartId);
        cart.setTotalCount(cart.getTotalCount() - toBeDeleted.getProductCount());
        cart.setTotalCost(cart.getTotalCost() - toBeDeleted.getTotalPrice());
        shoppingCartDAO.updateShoppingCart(cart);

        return cart;
    }

    /**
     * update cart item
     *
     * @param cartItem
     * @return
     */
    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    /**
     * update shopping cart
     *
     * @param shoppingCart
     */
    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartDAO.updateShoppingCart(shoppingCart);
    }
}

