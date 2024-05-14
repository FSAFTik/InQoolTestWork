package com.example.TennisReservation.Dao;

import com.example.TennisReservation.Entities.Customer;
import org.hibernate.SessionFactory;

import java.util.Optional;

/**
 * This class is responsible for handling customer-related data operations.
 * It extends the Dao class, inheriting its methods and properties.
 */
public class CustomerDao extends Dao<Customer>{

    /**
     * Constructor for the CustomerDao class.
     * @param sessionFactory The session factory to be used for database operations.
     */
    public  CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }

    /**
     * Retrieves a customer by their phone number.
     * @param phoneNumber The phone number of the customer to retrieve.
     * @return An Optional containing the customer if found, or an empty Optional if not.
     */
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return getCurrentSession().createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber AND c.deleted = false", Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .uniqueResultOptional();
    }
}