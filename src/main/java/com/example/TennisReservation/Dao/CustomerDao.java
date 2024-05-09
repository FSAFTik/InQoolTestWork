package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Customer;
import org.hibernate.SessionFactory;

import java.util.Optional;


public class CustomerDao extends Dao<Customer>{
    public  CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return getCurrentSession().createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber AND c.deleted = false", Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .uniqueResultOptional();
    }
}
