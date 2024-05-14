package com.example.TennisReservation.Entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity representing a surface.
 * It extends the SoftDeletable class, inheriting its methods and properties.
 * It is annotated with @Data, @Entity, and @Table.
 */
@Entity
@Data
@Table(name = "surfaces")
public class Surface extends SoftDeletable {
    /**
     * The ID of the surface. It is the primary key in the database.
     * It is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The type of the surface.
     */
    private String type;

    /**
     * The price per hour to reserve a court with this surface.
     * It is a non-nullable column in the 'surfaces' table.
     */
    @Column(nullable = false, name = "price_per_hour")
    private int pricePerHour;

    /**
     * Indicates whether the surface is deleted.
     * It is a non-nullable column in the 'surfaces' table.
     */
    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}