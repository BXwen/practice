package com.springapp.mvc.shoppingcart.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Copyright(C) 2016 DB Schenker
 * Project Name: CIS Admin
 *
 * @author tyxu
 * @version 1.0.0
 * @created 4/21/2016
 */
public abstract class AbstractJpaDAO< T extends Serializable > {
    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional
    public T findOne(long id) {
        return entityManager.find(clazz, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

/*    @Transactional
    public List<T> findByColumnValue(String columnName, String columnValue) {
        Query query = entityManager.createQuery("from " + clazz.getName() + " where :column = :Val")
                .setParameter("column", columnName).setParameter("Val", columnValue);

        return query.getResultList();
    }*/

    @Transactional(readOnly = false)
    public void create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        entityManager.remove(entity);
    }



    @Transactional
    public void deleteById(long id) {
        T entity = findOne(id);
        delete(entity);
    }
}
