package com.bitbox.dto;

import com.bitbox.dao.DAOSupplier;
import com.bitbox.model.Country;
import com.bitbox.model.Supplier;
import com.bitbox.service.CountryService;
import com.bitbox.service.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private DAOSupplier supplier;

    @Autowired
    private CountryService country;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getSuppliers() {
        List<Supplier> suppliersDB = (List<Supplier>) supplier.findAll();

        if (suppliersDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(suppliersDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getSupplier(Long id) {
        Optional<Supplier> supplierDB = supplier.findById(id);

        if (supplierDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(supplierDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveSupplier(Supplier supplier) {
        return new ResponseEntity<>(this.supplier.save(supplier), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateSupplier(Supplier supplier, Long id) {
        Optional<Supplier> supplierDB = this.supplier.findById(id);

        if (supplierDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            supplierDB.get().setName(supplier.getName());
            supplierDB.get().setCountry((Country) country.getCountry(supplier.getCountry().getId()).getBody());
            // Update the item list?
            return new ResponseEntity<>(supplierDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteSupplier(Long id) {
        return this.supplier.findById(id)
                .map((supplierDB) -> {
                    this.supplier.delete(supplierDB);
                    return new ResponseEntity<>(HttpStatus.OK);
                }).orElseGet(() -> {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

}
