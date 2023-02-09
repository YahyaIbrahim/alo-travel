package com.wellware.data.repositories;

import com.wellware.data.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Long> {

    List<Trip> findAll();

}
