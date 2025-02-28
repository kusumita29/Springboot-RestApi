package com.kusumita.rest_api.microservice.social.media;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kusumita.rest_api.microservice.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResourceController {
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userDaoService.findAll();
	}
	
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }

        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
    	User savedUser = userDaoService.addUser(user);

        // Generate location URI: /users/{id}
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found"); // Handling in Controller
        }
        userDaoService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
