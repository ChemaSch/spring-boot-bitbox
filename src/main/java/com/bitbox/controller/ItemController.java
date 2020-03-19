package com.bitbox.controller;

import com.bitbox.model.Item;
import com.bitbox.service.ItemService;
import com.bitbox.service.Price_ReductionService;
import com.bitbox.service.SupplierService;
import java.awt.PageAttributes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@RestController
@CrossOrigin(origins = {"*"}, 
             methods = { RequestMethod.GET,
                         RequestMethod.POST,
                         RequestMethod.PUT,
                         RequestMethod.DELETE 
                       })
@RequestMapping("/items")
public class ItemController {
    
    @Autowired
    private ItemService itemService;    
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getItems() {
        return itemService.getItems();
    }
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }
   
}
