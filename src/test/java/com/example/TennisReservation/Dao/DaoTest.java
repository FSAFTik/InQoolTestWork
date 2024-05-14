package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.SoftDeletable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    private Dao<SoftDeletable> dao;

    public static class ConcreteSoftDeletable extends SoftDeletable {
        private boolean deleted = false;

        @Override
        public boolean isDeleted() {
            return deleted;
        }

        @Override
        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }

    public static class ConcreteDao extends Dao<SoftDeletable> {
        public ConcreteDao(SessionFactory sessionFactory) {
            super(sessionFactory, SoftDeletable.class);
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        Query query = mock(Query.class);
        when(session.createQuery(any(String.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new ConcreteSoftDeletable(), new ConcreteSoftDeletable()));
        dao = new ConcreteDao(sessionFactory);
    }

    @Test
    public void save_ValidEntity_ReturnsSameEntity() {
        SoftDeletable entity = new ConcreteSoftDeletable();
        when(session.save(any())).thenReturn(1L);

        SoftDeletable result = dao.save(entity);

        assertEquals(entity, result);
    }

    @Test
    public void findAll_NoDeletedEntities_ReturnsAllEntities() {
        List<SoftDeletable> result = dao.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void delete_ValidEntity_DeletesEntity() {
        ConcreteSoftDeletable entity = new ConcreteSoftDeletable();

        int expectedAmountOfEntities = dao.findAll().size();
        dao.save(entity);
        dao.delete(entity);

        int result = dao.findAll().size();

        assertEquals(result, expectedAmountOfEntities);
        assertTrue(entity.isDeleted());
    }

    @Test
    public void findAll_DeletedEntities_DoesNotReturnDeletedEntities() {
        ConcreteSoftDeletable entity1 = new ConcreteSoftDeletable();
        ConcreteSoftDeletable entity2 = new ConcreteSoftDeletable();
        int expectedAmountOfEntities = dao.findAll().size();
        dao.save(entity1);
        dao.save(entity2);
        dao.delete(entity1);

        List<SoftDeletable> result = dao.findAll();

        assertEquals(expectedAmountOfEntities, result.size());
        assertEquals(entity2, result.get(0));
        assertTrue(entity1.isDeleted());
    }
}