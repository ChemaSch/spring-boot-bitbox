package com.bitbox.dto;

import com.bitbox.dao.DAOItem;
import com.bitbox.enums.Item_State;
import com.bitbox.model.Item;
import com.bitbox.model.Price_Reduction;
import com.bitbox.model.Supplier;
import com.bitbox.service.ItemService;
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getItems() {
        List<Item> itemsDB = (List<Item>) item.findAll();

        if (itemsDB.isEmpty()) {
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
        return new ResponseEntity<>(this.item.save(item), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<?> updateItem(Item item, Long id) {

        Optional<Item> itemDB = this.item.findById(id);

        if (itemDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            itemDB.get().setDescription(item.getDescription());
            itemDB.get().setPrice(item.getPrice());

            if (isDiscontinued(item.getItem_state().toString())) {
                itemDB.get().setItem_state(item.getItem_state());
                itemDB.get().setReason(item.getReason());
                itemDB.get().setUser_deactivation(item.getUser_deactivation());
            } else {
                itemDB.get().setItem_state(item.getItem_state());
            }

            if (!item.getPrice_reductions().isEmpty()) {                
                for (int i = 0; i < item.getPrice_reductions().size(); i++) {
                    Price_Reduction price_reduction = item.getPrice_reductions().get(i);
                    if (!existPriceReduction(itemDB.get().getPrice_reductions(), price_reduction.getId())) {                        
                        System.out.println(item.getPrice_reductions().get(i));
                        itemDB.get().addPriceReduction(item.getPrice_reductions().get(i));
                    }
                }
            }

            if (!item.getSuppliers().isEmpty()) {
                for (int i = 0; i < item.getSuppliers().size(); i++) {
                    Supplier supplier = item.getSuppliers().get(i);
                    if (!existSupplier(itemDB.get().getSuppliers(), supplier.getId())) {
                        itemDB.get().addSupplier(item.getSuppliers().get(i));
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
    private boolean existSupplier(List<Supplier> suppliers, Long id) {
        if (suppliers.stream().anyMatch((s) -> (Objects.equals(s.getId(), id)))) {
            return true;
        }
        return false;
    }

    // Check if the price reduction exist on the list for avoid duplications
    // insertions. In other case, add to the list.
    private boolean existPriceReduction(List<Price_Reduction> price_reductions, Long id) {
        if (price_reductions.stream().anyMatch((p) -> (Objects.equals(p.getId(), id)))) {
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
