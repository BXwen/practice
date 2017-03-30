package com.springapp.mvc.shoppingcart.model;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/13/2016
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@javax.persistence.SequenceGenerator(
        name = "SEQ_STORE",
        sequenceName = "shopping_cart_id_seq"
)
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;
    @Column(name = "user_id")
    private String userId;
    @Transient
    private List<CartItem> items;
    @Column(name = "total_count")
    private int totalCount;
    @Column(name = "total_cost")
    private float totalCost;

    public ShoppingCart() {
    }

    /**
     * Constructor
     *
     * @param userId
     */
    public ShoppingCart(String userId) {
        this.userId = userId;
        this.totalCost = 0;
        this.totalCount = 0;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public ShoppingCart(Long shoppingCartId, String userId, int totalCount, float totalCost) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.totalCount = totalCount;
        this.totalCost = totalCost;
    }

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                ", userId='" + userId + '\'' +
                ", totalCount=" + totalCount +
                ", totalCost=" + totalCost +
                '}';
    }
}
