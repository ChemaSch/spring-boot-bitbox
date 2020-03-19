package com.bitbox.service;

import com.bitbox.model.Item;
import java.util.List;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface ItemService {
    
    public List<Item> getItems();
    
    public Item getItem(Long id);
    
    public Item saveItem(Item item);
    
    public Item updateItem(Long id, Item item);
    
    public void deleteItem(Long id);
    
}
