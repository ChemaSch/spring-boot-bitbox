package com.bitbox.controller;

import com.bitbox.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@CrossOrigin(origins = {"*"}, 
             methods = { RequestMethod.GET,
                         RequestMethod.POST,
                         RequestMethod.PUT,
                         RequestMethod.DELETE 
                       })
@RestController
public class CountryController {
    
    @Autowired
    private CountryService country;
    
    @GetMapping(path= "/countries")
    public ResponseEntity<?> getCountries() {
        return country.getCountries();
    }
    
    @GetMapping(path = "/countries/{id}")
    public ResponseEntity<?> getCountry(@PathVariable Long id) {
        return country.getCountry(id);
    }
    
}
