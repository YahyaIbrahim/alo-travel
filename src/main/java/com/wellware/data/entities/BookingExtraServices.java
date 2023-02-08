package com.wellware.data.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "booking_extra_services")
@Getter
@Setter

public class BookingExtraServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "extra_service", referencedColumnName = "id")
    private ExtraService extraService;

    @Column(name = "cost")
    private Float serviceCost;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

}
