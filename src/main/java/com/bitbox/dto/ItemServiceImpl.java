package com.bitbox.dto;

import com.bitbox.dao.DAOItem;
import com.bitbox.enums.Item_State;
import com.bitbox.model.Item;
import com.bitbox.model.Supplier;
import com.bitbox.model.User;
import com.bitbox.service.ItemService;
import com.bitbox.service.Price_ReductionService;
import com.bitbox.service.SupplierService;
import com.bitbox.service.UserService;
import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    private DAOItem item;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private Price_ReductionService price_reduction;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getItems() {
        List<Item> itemsDB = (List<Item>) item.findAll();

        if (itemsDB == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemsDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getItem(Long id) {
        Optional<Item> itemDB = item.findById(id);

        if (itemDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemDB, HttpStatus.OK);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> saveItem(Item item) {

        // Set the current date of the item creation.
        LocalDate currentDate = LocalDate.now();

        item.setCreation_date(Date.valueOf(currentDate));
        return new ResponseEntity<>(item, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<?> updateItem(Item item, Long id) {

        Optional<Item> itemDB = this.item.findById(id);

        if (itemDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            itemDB.get().setDescription(item.getDescription());
            itemDB.get().setPrice(item.getPrice());
            itemDB.get().setCreator((User) userService.getUser(item.getCreator().getId()).getBody());
            
            if (isDiscontinued(item.getItem_state().toString())) {
                itemDB.get().setItem_state(item.getItem_state());
                itemDB.get().setReason(item.getReason());
            }
            // Check the price reduction list (just one price reduction) in the request. After, check if the price reduction exists on
            // the price reductions database list.
            if(!item.getPrice_reduction().isEmpty()) {
                
            }

            // Check the suppliers list in the request. After, check if the supplier exists on the suppliers database list
            // for avoid duplications insertions. In other case, add the supplier request to the suppliers databse list.
            if (!item.getSuppliers().isEmpty()) {
                for (int i = 0; i < item.getSuppliers().size(); i++) {
                    Supplier supplier = item.getSuppliers().get(i);
                    if (!existSupplier(itemDB.get().getSuppliers(), supplier.getId())) {
                        itemDB.get().addSupplier((Supplier) supplierService.getSupplier(item.getSuppliers().get(i).getId()).getBody());
                    }
                }
            }
            return new ResponseEntity<>(this.item.save(itemDB.get()), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> deleteItem(Long id) {
        return this.item.findById(id)
                .map((itemDB) -> {
                    this.item.delete(itemDB);
                    return new ResponseEntity<>(HttpStatus.OK);
                }).orElseGet(() -> {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    // Check if the supplier exist on the list for avoid duplications
    // insertions. In other case, add to the list.
    private boolean existSupplier(List<Supplier> supplier, Long id) {
        if (supplier.stream().anyMatch((s) -> (Objects.equals(s.getId(), id)))) {
            return true;
        }
        return false;
    }

    // Check if the item is active or not. Update the reason
    // of the item state if the value is equal to discontinued.
    private boolean isDiscontinued(String status) {
        if (status.equalsIgnoreCase(Item_State.DISCONTINUED.toString())) {
            return true;
        } else {
            return false;
        }
    }
        
}
