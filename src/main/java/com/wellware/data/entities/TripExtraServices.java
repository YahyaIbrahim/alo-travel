package com.wellware.data.entities;





import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "trip_extra_services")
@Data
public class TripExtraServices{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "fees")
    private Float fees;

    @JoinColumn(name = "extra_service_id", referencedColumnName = "id")
    @ManyToOne
    private ExtraService extraService;

    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    @ManyToOne
    private Trip trip;

}
