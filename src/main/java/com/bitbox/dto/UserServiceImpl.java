package com.bitbox.dto;

import com.bitbox.dao.DAOUser;
import com.bitbox.model.User;
import com.bitbox.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DAOUser user;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getUsers() {
        List<User> usersDB = (List<User>) user.findAll();

        if (usersDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usersDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getUser(Long id) {
        Optional<User> userDB = this.user.findById(id);

        if (userDB.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDB, HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveUser(User user) {
        // Encode the password of the user.
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new ResponseEntity<>(this.user.save(user), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateUser(User user, Long id) {
        Optional<User> userDB = this.user.findById(id);

        if (userDB.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userDB.get().setName(user.getName());
            userDB.get().setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userDB.get().setRole(user.getRole());
            return new ResponseEntity<>(this.user.save(userDB.get()), HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUser(Long id) {
        return this.user.findById(id)
                .map((userDB) -> {
                    this.user.delete(userDB);
                    return new ResponseEntity<>(HttpStatus.OK);
                }).orElseGet(() -> {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> login(User user) {
        User userDB = this.user.findByName(user.getName());

        if (userDB == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (comparePassword(user.getPassword(), userDB.getPassword())) {
            return new ResponseEntity<>(userDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean comparePassword(String password, String DBpassword) {
        BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder();
        if (!cryptPassword.matches(password, DBpassword)) {
            return false;
        } else {
            return true;
        }
    }

}
