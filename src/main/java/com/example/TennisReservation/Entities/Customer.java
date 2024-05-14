package com.example.TennisReservation.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * Entity representing a customer.
 * It extends the SoftDeletable class, inheriting its methods and properties.
 * It is annotated with @Data, @NoArgsConstructor, @AllArgsConstructor, @Entity, and @Table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends SoftDeletable {
    /**
     * The phone number of the customer. It is the primary key in the database.
     */
    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * The name of the customer.
     * It is a non-nullable column in the 'customers' table.
     */
    @Column(nullable = false, name = "name")
    private String name;

    /**
     * The reservations made by the customer.
     * It is a one-to-many relationship with the Reservation entity.
     * The 'customer' field in the Reservation entity is mapped to this field.
     * The reservations are fetched eagerly, meaning they are retrieved from the database as soon as the customer is retrieved.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    /**
     * Indicates whether the customer is deleted.
     * It is a column in the 'customers' table.
     */
    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}