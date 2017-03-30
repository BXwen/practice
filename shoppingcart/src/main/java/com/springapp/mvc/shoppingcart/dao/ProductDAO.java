package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.Product;

import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/21/2016
 */
public interface ProductDAO {
    /**
     * get all products from table PRODUCT
     * @return
     */
    List<Product> getAllProducts();

    /**
     * get product from table PRODUCT by product id
     * @param id
     * @return
     */
    Product getProductById(long id);
}
