package com.bitbox.service;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface CountryService  {
    
    public ResponseEntity<?> getCountries();
    
    public ResponseEntity<?> getCountry(Long id);
    
}
