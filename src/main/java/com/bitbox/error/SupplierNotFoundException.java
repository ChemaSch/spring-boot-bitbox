package com.bitbox.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException {
    
    public SupplierNotFoundException(Long id) {
        super("Supplier id not found:  " + id);
    }
    
}