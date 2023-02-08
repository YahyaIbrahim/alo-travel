package com.wellware.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review extends AuditModel{


    @ManyToOne
    @JoinColumn(name="profile_id", nullable=false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    private Trip trip;

    @Column(name = "review_body")
    private String reviewBody;

    @Column(name = "star")
    private String star;

}