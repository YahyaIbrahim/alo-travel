package com.wellware.data.repositories;

import com.wellware.data.entities.Country;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long> {

}
