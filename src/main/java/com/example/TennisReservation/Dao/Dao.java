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

    /**
     * Retrieves the current session from the session factory.
     * @return The current session.
     */
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Saves an entity to the database.
     * @param entity The entity to save.
     * @return The saved entity.
     */
    public T save(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    /**
     * Updates an entity in the database.
     * @param entity The entity to update.
     */
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    /**
     * Marks an entity as deleted in the database.
     * @param entity The entity to delete.
     */
    public void delete(T entity) {
        entity.setDeleted(true);
        update(entity);
    }

    /**
     * Retrieves all entities of type T that have not been marked as deleted.
     * @return A list of all non-deleted entities of type T.
     */
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM " + entityClass.getName() + " e WHERE e.deleted = false").list();
    }
}