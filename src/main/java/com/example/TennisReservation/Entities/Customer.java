package com.example.TennisReservation.Entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


/**
 * Entity representing a customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;
}
