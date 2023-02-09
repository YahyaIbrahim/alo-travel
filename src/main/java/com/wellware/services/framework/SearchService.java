package com.wellware.services.framework;

import com.wellware.DTO.SearchCriteria;
import com.wellware.data.entities.Trip;

import java.util.List;

public interface SearchService {
    List<Trip> getTrips(SearchCriteria searchCriteria);
}
