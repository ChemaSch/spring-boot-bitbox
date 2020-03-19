package com.bitbox.service;

import com.bitbox.model.Country;
import java.util.List;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface CountryService  {
    
    public List<Country> getCountries();
    
    public Country getCountry(Long id);
    
}
