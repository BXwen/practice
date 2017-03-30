package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.CartItem;
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
 * @created 4/22/2016
 */
@Repository
public class CartItemDAOImpl extends AbstractJpaDAO<CartItem> implements CartItemDAO {
    /**
     * get items in shopping cart by shopping cart id
     *
     * @param shoppingCartId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CartItem> getCartItemsByShoppingCartId(long shoppingCartId) {
        Query query = getEntityManager().createQuery("select c from CartItem c where c.shopping_cart_id = :shoppingCartId")
                .setParameter("shoppingCartId", shoppingCartId);
        List<CartItem> retList = query.getResultList();
        return retList;
    }

    /**
     * @param shoppingCartId
     * @param productId
     * @return
     */
    @Override
    @Transactional
    public CartItem getCartItemFromCart(Long shoppingCartId, Long productId) {
        Query query = getEntityManager().createQuery("select c from CartItem c where c.shopping_cart_id = :shoppingCartId and c.product_id = :productId")
                .setParameter("shoppingCartId", shoppingCartId).setParameter("productId", productId);
        List<CartItem> list = query.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * insert
     *
     * @param cartItem
     */
    @Override
    @Transactional
    public void insertCartItem(CartItem cartItem) {
        create(cartItem);
    }

    /**
     * update
     *
     * @param cartItem
     */
    @Override
    @Transactional
    public void updateCartItem(CartItem cartItem) {
        update(cartItem);
    }

    /**
     * delete cart item from shopping cart
     *
     * @param shoppingCartId
     * @param productId
     */
    @Override
    @Transactional
    public void deleteCartItemByProductId(Long shoppingCartId, Long productId) {
        Query query = getEntityManager().createQuery("delete from CartItem c where c.shopping_cart_id = :shoppingCartId and c.product_id = :productId")
                .setParameter("shoppingCartId", shoppingCartId).setParameter("productId", productId);
        query.executeUpdate();
    }


}
