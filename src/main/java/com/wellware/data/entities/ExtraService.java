package com.wellware.data.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "extra_services")
@Getter
@Setter

public class ExtraService {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "extraService", fetch = FetchType.EAGER)
    private Set<BookingExtraServices> bookingExtraServices;


    @OneToMany(mappedBy = "extraService", fetch = FetchType.EAGER)
    private Set<TripExtraServices> tripExtraServices;

}
