package com.bitbox.dto;

import com.bitbox.dao.DAOSupplier;
import com.bitbox.error.SupplierNotFoundException;
import com.bitbox.model.Supplier;
import com.bitbox.service.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private DAOSupplier supplier;
    
    @Override
    @Transactional(readOnly = true)
    public List<Supplier> getSuppliers() {
        return (List<Supplier>) this.supplier.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Supplier getSupplier(Long id) {
        return this.supplier.findById(id)
                .orElseThrow( () -> {
                    return new SupplierNotFoundException(id);
                });
    }

    @Override
    @Transactional
    public Supplier saveSupplier(Supplier supplier) {
        return this.supplier.save(supplier);
    }
    
    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplier;
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        this.supplier.deleteById(id);
    }

}
