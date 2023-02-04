package com.wellware.data.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "extra_services")
@Data
public class ExtraService {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "service_id")
//    private Collection<BookingExtraServices> bookingExtraServicesCollection;


    @OneToMany(mappedBy = "extraService")
    private Collection<TripExtraServices> guidingServices;

}
