package com.practice.SpringBoot.user;

import java.net.URI;
import java.util.List;
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
public class userController {
	
	@Autowired
	private userDaoService service;
	
	@GetMapping("user")
	public List<User> findAll() {
		return service.findAll();
	}
	
	@GetMapping("user/{id}")
	public User findOneUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+id);
		return user;
	}
	
	@DeleteMapping("user/{id}")
	public void deleteOneUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+id);

	}
	
	@PostMapping("user")
	public ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
