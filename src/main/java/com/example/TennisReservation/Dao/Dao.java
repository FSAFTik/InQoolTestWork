package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.SoftDeletable;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Abstract DAO class, to perform CRUD operations
 * on entities that implement the SoftDeletable interface.
 *
 * @param <T> - Entity type, this DAO class works with, must extend SoftDeletable
 */

@RequiredArgsConstructor
public abstract class Dao<T extends SoftDeletable> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T save(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        entity.setDeleted(true);
        update(entity);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + entityClass.getName() + " e WHERE e.deleted = false").list();
    }
}