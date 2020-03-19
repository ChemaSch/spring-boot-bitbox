package com.bitbox.service;

import com.bitbox.model.Item;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface ItemService {
    
    public ResponseEntity<?> getItems();
    
    public ResponseEntity<?> getItem(Long id);
    
    public ResponseEntity<?> saveItem(Item item);
    
    public ResponseEntity<?> updateItem(Item item, Long id);
    
    public ResponseEntity<?> deleteItem(Long id);
    
}
