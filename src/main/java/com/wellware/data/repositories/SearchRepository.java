package com.wellware.data.repositories;

import com.wellware.data.entities.Trip;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SearchRepository extends JpaRepository<Trip, Id>, JpaSpecificationExecutor<Trip> {

}
