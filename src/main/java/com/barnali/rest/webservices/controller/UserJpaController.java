package com.barnali.rest.webservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.barnali.rest.webservices.beans.User;
import com.barnali.rest.webservices.error.UserException;
import com.barnali.rest.webservices.services.UserDaoService;
import com.barnali.rest.webservices.services.UserRepository;
import com.barnali.rest.webservices.success.SuccessResponse;

@RestController
public class UserJpaController {
	
	@Autowired
	private UserRepository userRepository;
	
	//retrieve all users
	@GetMapping("/jpa/all-users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	//retrieve single user
	@GetMapping("/jpa/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())													//check for null object
			throw new UserException("Id-"+id+" not found");
		
		return user.get();														//get actual object
	}
	
	@PostMapping("/jpa/create-user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		
		//return the created status and created resource uri
		// /user/{id}
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/delete-user/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

}
