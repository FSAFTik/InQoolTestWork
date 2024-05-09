package com.example.TennisReservation.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "surfaces")
public class Surface extends SoftDeletable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @Column(nullable = false, name = "price_per_hour")
    private int pricePerHour;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}