package com.bitbox.dto;

import com.bitbox.dao.DAOCountry;
import com.bitbox.model.Country;
import com.bitbox.service.CountryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private DAOCountry country;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getCountries() {
        List<Country> countriesDB = (List<Country>) country.findAll();

        if (countriesDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(countriesDB, HttpStatus.OK);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getCountry(Long id) {
        Optional<Country> countryDB = country.findById(id);

        if (countryDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(countryDB, HttpStatus.OK);
        }

    }

}
