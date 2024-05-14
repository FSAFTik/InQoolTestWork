package com.example.TennisReservation.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity representing a tennis court.
 * It extends the SoftDeletable class, inheriting its methods and properties.
 * It is annotated with @Data, @NoArgsConstructor, @AllArgsConstructor, @Entity, and @Table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courts")
public class Court extends SoftDeletable {
    /**
     * The ID of the court. It is the primary key in the database.
     * It is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The ID of the surface of the court.
     * It is included in the JSON when the court is serialized.
     */
    @JsonProperty
    private Long surfaceId;

    /**
     * The surface of the court.
     * It is a many-to-one relationship with the Surface entity.
     * The 'surface_id' column in the 'courts' table is mapped to this field.
     */
    @ManyToOne
    @JoinColumn(name = "surface_id", nullable = false)
    private Surface surface;

    /**
     * Indicates whether the court is deleted.
     * It is a column in the 'courts' table.
     */
    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}