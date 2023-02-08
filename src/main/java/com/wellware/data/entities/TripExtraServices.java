package com.wellware.data.entities;





import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "trip_extra_services")
@Getter
@Setter

public class TripExtraServices{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "fees")
    private Float fees;


    @ManyToOne
    @JoinColumn(name = "extra_service_id", referencedColumnName = "id")
    private ExtraService extraService;


    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

}
