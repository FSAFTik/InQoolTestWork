package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Customer> query;

    @InjectMocks
    private CustomerDao customerDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(any(String.class), any(Class.class))).thenReturn(query);
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
    }

    @Test
    public void findByPhoneNumber_ValidPhoneNumber_ReturnsCustomer() {
        Customer customer = new Customer();
        when(query.uniqueResultOptional()).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerDao.findByPhoneNumber("1234567890");

        assertEquals(Optional.of(customer), result);
    }

    @Test
    public void findByPhoneNumber_InvalidPhoneNumber_ReturnsEmpty() {
        when(query.uniqueResultOptional()).thenReturn(Optional.empty());

        Optional<Customer> result = customerDao.findByPhoneNumber("1234567890");

        assertEquals(Optional.empty(), result);
    }
}