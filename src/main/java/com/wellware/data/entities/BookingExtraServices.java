package com.wellware.data.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "booking_extraservices")
@Data
public class BookingExtraServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private ExtraService serviceId;

    @Column(name = "cost")
    private Float serviceCost;

    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Booking booking;

}
