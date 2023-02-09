package com.wellware.services.implementation;

import com.wellware.DTO.SearchCriteria;
import com.wellware.data.entities.Trip;
import com.wellware.services.framework.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {



    @Override
    public List<Trip> getTrips(SearchCriteria searchCriteria) {
        return null;
    }
}
