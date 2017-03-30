package com.springapp.mvc.shoppingcart.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/13/2016
 */
@Entity
@Table(name = "cart_item")
@IdClass(CartItemPrimaryKey.class)
public class CartItem implements Serializable {
    @Id
    private Long shopping_cart_id;
    @Id
    private Long product_id;

    @Transient
    private Product product;

    @Column(name = "product_count")
    private int productCount;
    @Column(name = "total_price")
    private float totalPrice;
    @Column(name = "checked")
    @Type(type = "yes_no")
    private boolean checked;

    /**
     * Constructor
     */
    public CartItem() {
    }

    /**
     * Constructor
     *
     * @param productCount
     * @param totalPrice
     * @param checked
     */
    public CartItem(Long product_id, int productCount, float totalPrice, boolean checked) {
        this.product_id = product_id;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.checked = checked;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public long getShopping_cart_id() {
        return shopping_cart_id;
    }

    public void setShopping_cart_id(long shoppingCartId) {
        this.shopping_cart_id = shoppingCartId;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long productId) {
        this.product_id = productId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shopping_cart_id = shoppingCartId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "shoppingCartId=" + shopping_cart_id +
                ", productId=" + product_id +
                ", productCount=" + productCount +
                ", totalPrice=" + totalPrice +
                ", checked=" + checked +
                '}';
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
