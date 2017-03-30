package com.springapp.mvc.shoppingcart.controller;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: Shopping Cart
 *
 * @author Tony Xu
 * @version 1.0.0
 * @created 2016/4/5
 */

import com.springapp.mvc.shoppingcart.model.*;
import com.springapp.mvc.shoppingcart.service.ShoppingCartManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ShoppingCartController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ShoppingCartManager shoppingCartManager;

    /**
     * get product by productId
     *
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping(value = "/get-product", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@RequestParam("productId") Long productId, HttpServletRequest request) {
        Product product = shoppingCartManager.getProductById(productId);
        return product;
    }

    /**
     * get product list
     *
     * @return
     */
    @RequestMapping(value = "/get-product-list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProductList(HttpServletRequest request) {
        List<Product> list = shoppingCartManager.getAllProducts();

        // initialize shopping cart
        HttpSession session = request.getSession();
        String userId = session.getId();
        ShoppingCart cart = shoppingCartManager.getShoppingCartByUserId(userId);
        if (cart == null) {
            cart = new ShoppingCart(userId);
            shoppingCartManager.createShoppingCart(userId);
        }
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("ShoppingCart", cart);

        return list;
    }

    /**
     * add one product into shopping cart
     *
     * @param product
     * @param request
     * @return
     */
    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    ShoppingCart addToShoppingCart(@RequestBody Product product, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        shoppingCartManager.addToShoppingCart(userId, product);
        ShoppingCart userCart = shoppingCartManager.getShoppingCartByUserId(userId);

        return userCart;
    }

    /**
     * delete product from shopping cart
     *
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete-product", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ShoppingCart deleteCartItem(@RequestParam("productId") Long productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ShoppingCart userCart = shoppingCartManager.getShoppingCartByUserId(userId);
        userCart = shoppingCartManager.deleteCartItem(userCart.getShoppingCartId(), productId);

        List<CartItem> retList = shoppingCartManager.getCartItems(userCart.getShoppingCartId());
        userCart.setItems(retList);

        return userCart;
    }

    /**
     * delete cart items from shopping cart by product ids separated by comma
     *
     * @param productIdStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete-products", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ShoppingCart batchDeleteCartItems(@RequestParam("productIds") String productIdStr, HttpServletRequest request) {
        String[] productIdArr = productIdStr.split(",");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        ShoppingCart userCart = shoppingCartManager.getShoppingCartByUserId(userId);
        for (int i = 0; i < productIdArr.length; i++) {
            userCart = shoppingCartManager.deleteCartItem(userCart.getShoppingCartId(), Long.valueOf(productIdArr[i]));
        }

        List<CartItem> retList = shoppingCartManager.getCartItems(userCart.getShoppingCartId());
        userCart.setItems(retList);
        return userCart;
    }

    /**
     * get user's shopping cart
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/get-cart", method = RequestMethod.GET)
    public
    @ResponseBody
    ShoppingCart getShoppingCart(HttpServletRequest request) {
        logger.info("enter get cart");
        String userId = (String) request.getSession().getAttribute("userId");
        ShoppingCart cart = shoppingCartManager.getShoppingCartByUserId(userId);
        logger.info(cart);

        return cart;
    }

    /**
     * get shopping cart content for showing
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/show-cart", method = RequestMethod.GET)
    public
    @ResponseBody
    ShoppingCart getShoppingCartWithContent(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        ShoppingCart cart = shoppingCartManager.getShoppingCartByUserId(userId);
        List<CartItem> retList = shoppingCartManager.getCartItems(cart.getShoppingCartId());
        cart.setItems(retList);

        return cart;
    }

    /**
     * update shopping cart after changing the count of product
     *
     * @param shoppingCartId
     * @param cartItem
     * @param request
     * @return
     */
    @RequestMapping(value = "/update-shopping-cart/{shoppingCartId}", method = RequestMethod.POST)
    public
    @ResponseBody
    ShoppingCart updateShoppingCart(@PathVariable Long shoppingCartId, @RequestBody CartItem cartItem, HttpServletRequest request) {
        cartItem.setTotalPrice(cartItem.getProductCount() * cartItem.getProduct().getPrice());
        shoppingCartManager.updateCartItem(cartItem);
        List<CartItem> cartItems = shoppingCartManager.getCartItems(shoppingCartId);
        int len = cartItems.size();
        float totalCost = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            totalCost += cartItems.get(i).getTotalPrice();
            count += cartItems.get(i).getProductCount();
        }
        ShoppingCart userCart = new ShoppingCart();
        userCart.setTotalCost(totalCost);
        userCart.setTotalCount(count);
        userCart.setUserId((String) request.getSession().getAttribute("userId"));
        userCart.setShoppingCartId(shoppingCartId);
        shoppingCartManager.updateShoppingCart(userCart);

        List<CartItem> retList = shoppingCartManager.getCartItems(shoppingCartId);
        userCart.setItems(retList);

        return userCart;
    }

    /**
     * add product to wish list
     *
     * @param product
     * @param request
     * @return
     */
    @RequestMapping(value = "/add-to-wish-list", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    WishList addToWishList(@RequestBody Product product, HttpServletRequest request) {
        HttpSession session = request.getSession();
        WishList wishList = (WishList) session.getAttribute("WishList");
        if (wishList == null) {
            wishList = new WishList();
            session.setAttribute("WishList", wishList);
        }

        wishList.getList().add(new WishItem(new Date(), product));

        return wishList;
    }

    /**
     * get wish list
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/show-wish-list", method = RequestMethod.GET)
    public
    @ResponseBody
    WishList getWishList(HttpServletRequest request) {
        WishList wishList = (WishList) request.getSession().getAttribute("WishList");
        return wishList;
    }

    /**
     * delete product in wish list
     *
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete-wish-list", method = RequestMethod.DELETE)
    public
    @ResponseBody
    WishList deleteWishItem(@RequestParam("productId") Long productId, HttpServletRequest request) {
        WishList wishList = (WishList) request.getSession().getAttribute("WishList");
        wishList.deleteWishItem(productId);

        return wishList;
    }
}












































