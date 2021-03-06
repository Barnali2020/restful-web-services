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

import com.barnali.rest.webservices.beans.Post;
import com.barnali.rest.webservices.beans.User;
import com.barnali.rest.webservices.error.UserException;
import com.barnali.rest.webservices.services.PostRepository;
import com.barnali.rest.webservices.services.UserDaoService;
import com.barnali.rest.webservices.services.UserRepository;
import com.barnali.rest.webservices.success.SuccessResponse;

@RestController
public class UserJpaController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//retrieve all users
	@GetMapping("/jpa/all-users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	//retrieve single user
	@GetMapping("/jpa/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())													//check for null object
			throw new UserException("Id-"+id+" not found");
		
		return userOptional.get();														//get actual object
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
	
	
	//retrieve all posts
	@GetMapping("/jpa/user/{id}/all-posts")
	public List<Post> retrieveAllPostsOfUser(@PathVariable int id){
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())													//check for null object
			throw new UserException("Id-"+id+" not found");
		
		return userOptional.get().getPost();
	}
	
	@PostMapping("/jpa/user/{id}/create-post")
	public ResponseEntity<User> createPost (@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())													//check for null object
			throw new UserException("Id-"+id+" not found");
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		Post savedPost = postRepository.save(post);
		
		
		//return the created status and created resource uri
		// /user/{id}
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedPost.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	

}
