package com.bitbox.service;

import com.bitbox.model.User;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface UserService {
        
    public ResponseEntity<?> getUsers();
   
    public ResponseEntity<?> getUser(Long id);
    
    public ResponseEntity<?> saveUser(User user);
   
    public ResponseEntity<?> updateUser(User user, Long id);
    
    public ResponseEntity<?> deleteUser(Long id);
   
    public ResponseEntity<?> login(User user);
    
}
