package com.example.TennisReservation.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class SoftDeletable {
    private boolean deleted;
}