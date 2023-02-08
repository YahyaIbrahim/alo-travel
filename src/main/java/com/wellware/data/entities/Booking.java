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
public class Booking extends AuditModel {


   @ManyToOne
   @JoinColumn(name="profile_id", nullable=false)
   private Profile profile;

   @ManyToOne
   @JoinColumn(name="trip_id", nullable=false)
   private Trip trip;

   @Column(name ="reservation_date")
   private Date reservationDate;

   @Column(name = "no_persons")
   private int no_persons;

   @Column(name = "guiding_cost")
   private Float guidingCost;

   @Column(name = "total_cost")
   private Float totalCost;

   @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
   private List<BookingExtraServices> extraServices;

   @Column(name = "trip_start")
   private Date tripStart;

   @Column(name="trip_end")
   private Date tripEnd;
}
