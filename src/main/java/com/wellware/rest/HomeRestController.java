package com.wellware.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellware.services.framework.AutoCompleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HomeRestController {

    @Autowired
    private AutoCompleteService autoCompleteService;

    @GetMapping(value = "/autocomplete")
    public ResponseEntity<String> doAutoComplete(@RequestParam(value = "q",required = false) final String input){
        List<String> countriesName = autoCompleteService.doAutoCompleteCountryName(input);
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";
        try {
            resp = mapper.writeValueAsString(countriesName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info(resp);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
