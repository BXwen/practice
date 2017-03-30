package com.springapp.mvc.shoppingcart.controller;

import com.springapp.mvc.shoppingcart.model.ShoppingCart;
import com.springapp.mvc.shoppingcart.service.ShoppingCartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/shp")
public class HelloController {

    @Autowired
    ShoppingCartManager shoppingCartManager;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest request) {

        model.addAttribute("message", "<a href='/shop/html/#/goodslist.html'>Go to shop</a>");
        HttpSession session = request.getSession();
        String userId = session.getId();
        ShoppingCart cart = shoppingCartManager.getShoppingCartByUserId(userId);
        if (cart == null) {
            cart = new ShoppingCart(userId);
            shoppingCartManager.createShoppingCart(userId);
        }
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("ShoppingCart", cart);

        return "hello";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:../html/goodslist.html";
    }
}