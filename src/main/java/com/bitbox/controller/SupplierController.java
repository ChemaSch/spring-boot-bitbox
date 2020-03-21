package com.bitbox.controller;

import com.bitbox.model.Supplier;
import com.bitbox.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@CrossOrigin(origins = {"*"}, 
             methods = { RequestMethod.GET,
                         RequestMethod.POST,
                         RequestMethod.PUT,
                         RequestMethod.DELETE 
                       })
@RestController
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;
    
    @GetMapping(path = "/suppliers")    
    public ResponseEntity<?> getSuppliers() {
        return supplierService.getSuppliers();
    }
    
    @GetMapping(path = "/suppliers/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable Long id) {
        return supplierService.getSupplier(id);
    }
    
    @PostMapping(path = "/suppliers")
    public ResponseEntity<?> saveSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }
    
    @PutMapping(path = "/suppliers/{id}")
    public ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierService.updateSupplier(supplier, id);
    }
    
    @DeleteMapping(path = "/suppliers/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }
   
}
