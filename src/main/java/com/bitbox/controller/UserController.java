package com.bitbox.controller;

import com.bitbox.model.User;
import com.bitbox.service.UserService;

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
        methods = {RequestMethod.GET,
                   RequestMethod.POST,
                   RequestMethod.PUT,
                   RequestMethod.DELETE
        })
@RestController
public class UserController {

    @Autowired
    private UserService userService;

   
    @GetMapping(path="/users")    
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/users/{id}")    
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
   
    @PostMapping(path = "/users")    
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(path = "/users/{id}")    
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/users/{id}")    
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    
    @PostMapping(path = "/login")    
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user);
    }

}
