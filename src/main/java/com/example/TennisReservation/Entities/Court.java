package com.example.TennisReservation.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Table;


/**
 * Entity representing a tennis court.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "courts")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "surface_type")
    private Surface surfaceType;

    @Column(name = "is_available", nullable = false)
    private boolean available;
}