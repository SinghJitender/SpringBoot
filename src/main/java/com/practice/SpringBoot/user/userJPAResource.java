package com.practice.SpringBoot.user;

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

@RestController
public class userJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/user")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/user/{id}")
	public User findOneUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		return user.get();
	}
	
	@DeleteMapping("/jpa/user/{id}")
	public void deleteOneUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Post> retriveUserPosts(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		return user.get().getPosts();
	}

}
