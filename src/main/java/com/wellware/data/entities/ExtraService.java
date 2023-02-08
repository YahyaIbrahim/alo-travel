package com.wellware.data.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Entity
@Table(name = "extra_services")
@Getter
@Setter

public class ExtraService {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<BookingExtraServices> bookingExtraServicesCollection;


    @OneToMany
    private Collection<TripExtraServices> guidingServices;

}
