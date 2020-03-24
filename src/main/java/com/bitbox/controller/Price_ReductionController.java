package com.bitbox.controller;

import com.bitbox.model.Price_Reduction;
import com.bitbox.service.Price_ReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@CrossOrigin(origins = {"*"},
        methods = {RequestMethod.GET,
                   RequestMethod.POST                   
        })
@RestController
public class Price_ReductionController {
    
    @Autowired
    private Price_ReductionService price_ReductionService;
    
    @GetMapping(path = "/price_reductions")
    public ResponseEntity<?> getPriceReductions() {
        return price_ReductionService.getPriceReductions();
    }
    
    @GetMapping(path = "/price_reductions/{id}")
    public ResponseEntity<?> getPriceReduction(@PathVariable Long id) {
        return price_ReductionService.getPriceReduction(id);
    }
    
    @PostMapping(path = "/price_reductions")
    public ResponseEntity<?> savePriceReduction(@RequestBody Price_Reduction price_Reduction) {
        return price_ReductionService.savePriceReduction(price_Reduction);
    }
   
}
