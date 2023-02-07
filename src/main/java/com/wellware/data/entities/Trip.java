package com.wellware.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Getter
@Setter

@Entity
@Table(name = "trip")
public class Trip extends AuditModel {

    @Column(name = "trip_name")
    private String tripName;

    @Column(name = "trip_description")
    private String tripDescription;

    @Column(name = "trip_cost")
    private Float tripCost;

    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER)
    private Set<TripExtraServices> tripExtraServices;

}