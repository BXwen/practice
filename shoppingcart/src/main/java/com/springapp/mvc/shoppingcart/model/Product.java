package com.springapp.mvc.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "Product", schema = "J2EE")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "authors")
    private String authors;
    @Column(name = "price")
    private float price;
    @Column(name = "description")
    private String description;
    @Column(name = "full")
    private String full;
    @Column(name = "thumb")
    private String thumb;

    /**
     * Constructor
     */
    public Product() {
    }

    /**
     * Constructor
     * @param productId
     * @param name
     */
    public Product(long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    /**
     * Constructor
     * @param productId
     * @param name
     * @param authors
     * @param price
     * @param description
     * @param full
     * @param  thumb
     */
    public Product(long productId, String name, String authors, float price, String description, String full, String thumb) {
        this.productId = productId;
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.description = description;
        this.full = full;
        this.thumb = thumb;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
