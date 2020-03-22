package com.bitbox.controller;

import com.bitbox.model.Item;
import com.bitbox.service.ItemService;
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
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @GetMapping(path= "/items")
    public ResponseEntity<?> getItems() {
        return itemService.getItems();
    }
    
    @GetMapping(path = "/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }
    
    @PostMapping(path = "/items")
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }
    
    @PutMapping(path = "/items/{id}")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable Long id) {
        return itemService.updateItem(item, id);
    }
    
    @DeleteMapping(path = "/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
   
}
