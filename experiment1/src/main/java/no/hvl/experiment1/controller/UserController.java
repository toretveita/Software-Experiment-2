package no.hvl.experiment1.controller;

import no.hvl.experiment1.domain.User;
import no.hvl.experiment1.service.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DomainManager domainManager;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = domainManager.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // List all users
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = new ArrayList<>(domainManager.getAllUsers());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
