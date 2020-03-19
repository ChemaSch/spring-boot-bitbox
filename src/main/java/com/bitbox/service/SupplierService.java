package com.bitbox.service;

import com.bitbox.model.Supplier;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface SupplierService {
    
    public ResponseEntity<?> getSuppliers();
    
    public ResponseEntity<?> getSupplier(Long id);
    
    public ResponseEntity<?> saveSupplier(Supplier supplier);
    
    public ResponseEntity<?> updateSupplier(Supplier supplier, Long id);
    
    public ResponseEntity<?> deleteSupplier(Long id);
    
}
