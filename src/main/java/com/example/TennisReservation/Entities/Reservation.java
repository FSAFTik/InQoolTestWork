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
 * Entity representing a reservation.
 * It extends the SoftDeletable class, inheriting its methods and properties.
 * It is annotated with @Data, @NoArgsConstructor, @AllArgsConstructor, @Entity, and @Table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "reservations")
public class Reservation extends SoftDeletable{
    /**
     * The ID of the reservation. It is the primary key in the database.
     * It is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The customer who made the reservation.
     * It is a many-to-one relationship with the Customer entity.
     * The 'customer_phone_number' column in the 'reservations' table is mapped to this field.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_phone_number", referencedColumnName = "phone_number", nullable = false)
    private Customer customer;

    /**
     * The name of the customer who made the reservation.
     * It is included in the JSON when the reservation is serialized.
     */
    @JsonProperty
    private String customerName;

    /**
     * The phone number of the customer who made the reservation.
     * It is included in the JSON when the reservation is serialized.
     */
    @JsonProperty
    private String customerPhoneNumber;

    /**
     * The ID of the court where the reservation is made.
     * It is included in the JSON when the reservation is serialized.
     */
    @JsonProperty
    private long courtId;

    /**
     * The end time of the reservation.
     * It is a non-nullable column in the 'reservations' table.
     */
    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    /**
     * The start time of the reservation.
     * It is a non-nullable column in the 'reservations' table.
     */
    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    /**
     * The creation time of the reservation.
     * It is a non-nullable column in the 'reservations' table.
     */
    @Column(nullable = false, name = "creation_time")
    private LocalDateTime creationTime;

    /**
     * The court where the reservation is made.
     * It is a many-to-one relationship with the Court entity.
     * The 'court_id' column in the 'reservations' table is mapped to this field.
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    /**
     * Indicates whether the reservation is for a duo.
     * It is a non-nullable column in the 'reservations' table.
     */
    @JsonProperty
    @Column(nullable = false, name = "is_duo")
    private boolean isDuo;

    /**
     * Method that is called before the reservation is persisted.
     * It sets the creation time to the current time.
     */
    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
    }

    /**
     * Indicates whether the reservation is deleted.
     * It is a non-nullable column in the 'reservations' table.
     */
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    /**
     * Method that is called after the reservation is loaded.
     * It sets the customer name, customer phone number, and court ID from the related entities.
     */
    @PostLoad
    private void postLoad() {
        customerName = customer.getName();
        customerPhoneNumber = customer.getPhoneNumber();
        courtId = court.getId();
    }

    /**
     * Calculates the price of the reservation.
     * The price is based on the price per hour of the court's surface and the duration of the reservation.
     * If the reservation is for 4 people, the price is multiplied by 1.5.
     * @return The price of the reservation as a string.
     */
    public String getPrice(){
        int price = court.getSurface().getPricePerHour() * (endTime.getHour() - startTime.getHour());
        return this.isDuo() ? String.format("%.1f", price / 100.0) : String.format("%.1f", (price * 1.5) / 100.0);
    }
}