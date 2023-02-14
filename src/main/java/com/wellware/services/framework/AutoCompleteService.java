package com.wellware.services.framework;

import com.wellware.data.entities.Country;

import java.util.List;

public interface AutoCompleteService {

    List<String> doAutoCompleteCityName(String cityName);

    List<String> doAutoCompleteCountryName(String countryName);
}
