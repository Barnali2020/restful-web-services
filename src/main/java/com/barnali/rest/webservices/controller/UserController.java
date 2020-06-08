package com.barnali.rest.webservices.controller;

import java.net.URI;
import java.util.List;

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
import com.barnali.rest.webservices.success.SuccessResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userService;
	
	//retrieve all users
	@GetMapping("/all-users")
	public List<User> retrieveAllUsers(){
		return userService.findAll();
	}
	
	//retrieve single user
	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		
		if(user == null)
			throw new UserException("Id-"+id+" not found");
		
		return user;
	}
	
	@PostMapping("/create-user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userService.save(user);
		
		//return the created status and created resource uri
		// /user/{id}
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		
		User user = userService.deleteUser(id);
		
		if(user == null) {
			//that means deletion was not successful
			throw new UserException("Id-"+id+" not found");
		}
		
		SuccessResponse responseBody = new SuccessResponse("User Id-"+id+" deleted successfully");
		
		//else return OK status
		return new ResponseEntity<Object>(responseBody, HttpStatus.OK);
	}
	
}
