package com.wellware.services.implementation;

import com.wellware.data.entities.Country;
import com.wellware.data.repositories.CountryRepository;
import com.wellware.services.framework.AutoCompleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AutoCompleteServiceImpl implements AutoCompleteService {

    @Autowired
    private CountryRepository countryRepository;



    @Override
    public List<String> doAutoCompleteCityName(String cityName) {
        return null;
    }

    @Override
    public List<String> doAutoCompleteCountryName(String countryName) {

        List<Country> countries = countryRepository.findAll().stream().filter(s -> s.getCountryName().toLowerCase().startsWith(countryName)).toList();
        List<String> filteredCountry = new ArrayList<>();
        countries.forEach(country -> filteredCountry.add(country.getCountryName()));
        return filteredCountry;
    }
}
