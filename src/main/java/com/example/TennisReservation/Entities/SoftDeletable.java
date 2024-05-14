package com.example.TennisReservation.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

/**
 * This is an abstract class that represents entities that can be soft deleted.
 * Soft deletion is a strategy where you don't permanently delete records from your database, but instead flag them as deleted.
 * This class is annotated with @MappedSuperclass, which means it's not an entity itself but its fields will be mapped to its subclasses.
 * It is also annotated with @Data and @NoArgsConstructor from the Lombok library to automatically generate getters, setters, equals, hashCode, and a no-args constructor.
 */
@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class SoftDeletable {
    /**
     * This field represents whether the entity has been soft deleted.
     * It is false by default, meaning the entity is not deleted.
     */
    private boolean deleted = false;
}