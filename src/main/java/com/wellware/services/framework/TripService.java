package com.wellware.services.framework;

import com.wellware.data.entities.Trip;

import java.util.List;

public interface TripService {

    List<Trip> findAll();

    List<Trip> findAllByPlace(String placeName);

    List<Trip> findAllByCost(Float cost);

    void save(Trip trip);



}
