package com.bitbox.service;

import com.bitbox.model.Supplier;
import java.util.List;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface SupplierService {
    
    public List<Supplier> getSuppliers();
    
    public Supplier getSupplier(Long id);
    
    public Supplier saveSupplier(Supplier supplier);
    
    public Supplier updateSupplier(Supplier supplier);
    
    public void deleteSupplier(Long id);
    
}
