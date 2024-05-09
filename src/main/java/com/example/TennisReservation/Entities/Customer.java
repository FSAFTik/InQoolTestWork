package com.example.TennisReservation.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * Entity representing a customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends SoftDeletable {
    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}