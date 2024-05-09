package com.example.TennisReservation.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Entity representing a tennis court.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courts")
public class Court extends SoftDeletable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private Long surfaceId;

    @ManyToOne
    @JoinColumn(name = "surface_id", nullable = false)
    private Surface surface;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}