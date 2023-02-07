package com.wellware.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;
import java.util.List;


@Entity
@Table(name = "bookings")
@Getter
@Setter

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name="user_id")
    private Long userId;

   @Column(name = "trip id")
   private Long tripId;

   @Column(name ="reservation_date")
   private Date reservationDate;

   @Column(name = "no_persons")
   private int no_persons;

   @Column(name = "guiding_cost")
   private Float guidingCost;

   @Column(name = "total_cost")
   private Float totalCost;

   @OneToMany(mappedBy = "booking")
   private List<BookingExtraServices> extraServices;

   @Column(name = "trip_start")
   private Date tripStart;

   @Column(name="trip_end")
   private Date tripEnd;
}
