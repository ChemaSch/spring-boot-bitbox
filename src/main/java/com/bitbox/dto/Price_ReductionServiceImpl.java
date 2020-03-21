package com.bitbox.dto;

import com.bitbox.dao.DAOPrice_Reduction;
import com.bitbox.model.Price_Reduction;
import com.bitbox.service.Price_ReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Service
public class Price_ReductionServiceImpl implements Price_ReductionService {
    
    @Autowired
    private DAOPrice_Reduction price_Reduction;

    @Override
    public ResponseEntity<?> savePriceReduction(Price_Reduction price_reduction) {
        return new ResponseEntity<>(this.price_Reduction.save(price_reduction), HttpStatus.CREATED);
    }

}
