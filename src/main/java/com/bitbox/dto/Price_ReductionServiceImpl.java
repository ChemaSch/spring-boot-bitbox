package com.bitbox.dto;

import com.bitbox.dao.DAOPrice_Reduction;
import com.bitbox.model.Price_Reduction;
import com.bitbox.service.Price_ReductionService;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<?> getPriceReductions() {
        List<Price_Reduction> priceReductionDB = (List<Price_Reduction>) this.price_Reduction.findAll();
       
        if(priceReductionDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(priceReductionDB, HttpStatus.OK);
        }
        
    }
    
    @Override
    public ResponseEntity<?> getPriceReduction(Long id) {
        Optional<Price_Reduction> price_ReductionDB = this.price_Reduction.findById(id);
        
        if(price_ReductionDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(price_ReductionDB, HttpStatus.OK);
        }
        
    }
    
    @Override
    public ResponseEntity<?> savePriceReduction(Price_Reduction price_reduction) {       
        return new ResponseEntity<>(this.price_Reduction.save(price_reduction), HttpStatus.CREATED);
    }

}
