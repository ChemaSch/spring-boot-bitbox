package com.bitbox.dto;

import com.bitbox.dao.DAOItem;
import com.bitbox.dao.DAOPrice_Reduction;
import com.bitbox.dao.DAOSupplier;
import com.bitbox.enums.Item_State;
import com.bitbox.error.ItemNotFoundException;
import com.bitbox.model.Item;
import com.bitbox.model.Supplier;
import com.bitbox.service.ItemService;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
    private DAOItem daoItem;

    @Autowired
    private DAOSupplier supplier;
    
    @Autowired
    private DAOPrice_Reduction price_reduction;

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItems() {
        return (List<Item>) daoItem.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item getItem(Long id) {
        return daoItem.findById(id)
                .orElseThrow(() -> {
                    return new ItemNotFoundException(id);
                });
    }

    @Override
    @Transactional
    public Item saveItem(Item item) {
        return daoItem.save(item);
    }

    @Override
    public Item updateItem(Long id, Item itemRequest) {
        return daoItem.findById(id)
                .map((item) -> {
                    item.setDescription(itemRequest.getDescription());
                    item.setPrice(itemRequest.getPrice());                    
                    item.setItem_state(itemRequest.getItem_state());
                    
                    if (isDiscontinued(itemRequest.getItem_state().toString())) {
                        item.setReason(itemRequest.getReason());
                    }
                    
                    item.setCreator(itemRequest.getCreator());
                    return daoItem.save(item);
                }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        this.daoItem.deleteById(id);
    }

    // Check if the supplier exist on the list for avoid duplications
    // other case, add to the list.
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
