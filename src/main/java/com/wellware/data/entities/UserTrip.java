package com.wellware.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_trips")
public class UserTrip extends AuditModel {

    @ManyToOne(targetEntity = Profile.class, optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private Profile profile;

    @Column(name = "trip_url")
    private String tripUrl;
}
