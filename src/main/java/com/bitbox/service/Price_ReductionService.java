package com.bitbox.service;

import com.bitbox.model.Price_Reduction;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface Price_ReductionService {
    
    public ResponseEntity<?> getPriceReductions();
    
    public ResponseEntity<?> getPriceReduction(Long id);
        
    public ResponseEntity<?> savePriceReduction(Price_Reduction price_reduction);
    
}
