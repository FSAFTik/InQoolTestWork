package com.example.TennisReservation.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;


/**
 * Entity representing a reservation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "reservations")
public class Reservation extends SoftDeletable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_phone_number", referencedColumnName = "phone_number", nullable = false)
    private Customer customer;

    @JsonProperty
    private String customerName;

    @JsonProperty
    private String customerPhoneNumber;

    @JsonProperty
    private long courtId;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "creation_time")
    private LocalDateTime creationTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(nullable = false, name = "is_duo")
    private boolean isDuo;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
    }

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @PostLoad
    private void postLoad() {
        customerName = customer.getName();
        customerPhoneNumber = customer.getPhoneNumber();
        courtId = court.getId();
    }

    public String getPrice(){
        int price = court.getSurface().getPricePerHour() * (endTime.getHour() - startTime.getHour());
        return this.isDuo() ? String.format("%.1f", price / 100.0) : String.format("%.1f", (price * 1.5) / 100.0);
    }
}
