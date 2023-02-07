package com.wellware.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "activities")
@Getter
@Setter

public class Activities extends AuditModel {

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_description")
    private String activityDescription;

    @Column(name = "activity_cost")
    private Float activityCost;
}
