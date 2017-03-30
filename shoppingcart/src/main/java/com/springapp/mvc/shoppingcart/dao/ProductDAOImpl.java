package com.springapp.mvc.shoppingcart.dao;

import com.springapp.mvc.shoppingcart.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/21/2016
 */
@Repository
public class ProductDAOImpl extends AbstractJpaDAO<Product> implements ProductDAO {
    public ProductDAOImpl() {
        setClazz(Product.class);
    }

    /**
     * get all products from table PRODUCT
     *
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        return findAll();
    }

    /**
     * get product from table PRODUCT by product id
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(long id) {
        return findOne(id);
    }
}
