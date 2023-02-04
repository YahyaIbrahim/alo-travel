package com.wellware.data.entities;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "reviews")
public class Review extends AuditModel{

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "review_body")
    private String reviewBody;

    @Column(name = "star")
    private String star;

}