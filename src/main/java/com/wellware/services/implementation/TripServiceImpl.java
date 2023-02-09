package com.wellware.services.implementation;

import com.wellware.data.entities.Trip;
import com.wellware.data.repositories.TripRepository;
import com.wellware.services.framework.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> findAllByPlace(String placeName) {
        return null;
    }

    @Override
    public List<Trip> findAllByCost(Float cost) {
        return null;
    }

    @Override
    public void save(Trip trip) {

    }
}
