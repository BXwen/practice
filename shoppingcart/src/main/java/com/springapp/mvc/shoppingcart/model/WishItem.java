package com.springapp.mvc.shoppingcart.model;

import java.util.Date;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/18/2016
 */
public class WishItem {
    private Date collectedTime;
    private Product product;

    /**
     * Constructor
     */
    public WishItem() {
    }

    /**
     * Constructor
     */
    public WishItem(Date collectedTime, Product product) {
        this.collectedTime = collectedTime;
        this.product = product;
    }

    public Date getCollectedTime() {
        return collectedTime;
    }

    public void setCollectedTime(Date collectedTime) {
        this.collectedTime = collectedTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
