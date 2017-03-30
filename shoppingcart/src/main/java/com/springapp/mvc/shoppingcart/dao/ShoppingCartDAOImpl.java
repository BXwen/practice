package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.ShoppingCart;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/20/2016
 */
@Repository
public class ShoppingCartDAOImpl extends AbstractJpaDAO<ShoppingCart> implements ShoppingCartDAO {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ShoppingCartDAOImpl() {
        setClazz(ShoppingCart.class);
    }

    /**
     * get shopping cart by shopping cart id
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public ShoppingCart getShoppingCartByUserId(String userId) {
        Query query = getEntityManager().createQuery("select c from ShoppingCart c where c.userId = :USER_ID")
                .setParameter("USER_ID", userId);

        List<ShoppingCart> list = query.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * get shopping cart by shoppingCartId
     *
     * @param shoppingCartId
     * @return
     */
    @Override
    @Transactional
    public ShoppingCart getShoppingCart(Long shoppingCartId) {
        return findOne(shoppingCartId);
    }

    /**
     * create shopping cart for user
     *
     * @param userId
     * @return shopping_cart_id
     */
    @Override
    @Transactional
    public Long createShoppingCart(String userId) {
        logger.info("enter create shopping cart");
        ShoppingCart cart = new ShoppingCart(userId);
        logger.info(cart);
        this.create(cart);
        return cart.getShoppingCartId();
    }

    /**
     * update
     *
     * @param shoppingCart
     */
    @Override
    @Transactional
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        update(shoppingCart);
    }
}
