package com.bitbox.dto;

import com.bitbox.dao.DAOCountry;
import com.bitbox.error.CountryNotFoundException;
import com.bitbox.model.Country;
import com.bitbox.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public class CountryServiceImpl implements CountryService {

    @Autowired
    private DAOCountry country;
    
    @Override
    @Transactional(readOnly = true)
    public List<Country> getCountries() {
        return (List<Country>) country.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountry(Long id) {
        return country.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }
    
}
