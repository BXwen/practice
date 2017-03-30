package com.springapp.mvc.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/18/2016
 */
public class WishList {
    private List<WishItem> list;
    private int length;

    /**
     * Constructor
     */
    public WishList() {
        this.list = new ArrayList<WishItem>();
        this.length = 0;
    }

    /**
     * Constructor
     */
    public WishList(List<WishItem> list, int length) {
        this.list = list;
        this.length = length;
    }

    /**
     * delete wish list item by product id
     *
     * @param productId
     */
    public void deleteWishItem(Long productId) {
        int itemSize = this.list.size();
        for (int i = 0; i < itemSize; i++) {
            if (this.list.get(i).getProduct().getProductId() == productId) {
                this.list.remove(i);
                break;
            }
        }
    }

    public List<WishItem> getList() {
        return list;
    }

    public void setList(List<WishItem> list) {
        this.list = list;
    }

    public int getLength() {
        return this.list.size();

    }

    public void setLength(int length) {
        this.length = length;
    }
}
