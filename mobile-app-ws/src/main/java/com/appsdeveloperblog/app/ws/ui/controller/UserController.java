package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exception.UserServiceException;
import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserDetailsResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

	private Map<String , UserRest> users;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page" , defaultValue = "1" , required = false) int page , 
						   @RequestParam(value = "limit" , defaultValue = "50") int limit , 
						   @RequestParam(value = "sort" ,defaultValue = "desc", required = false)String sort)  {
		return "get users method was called with page = " + page + " and limit = " + limit +
				       " , sort = " + sort;
	}
	
	@GetMapping(path="/{userId}" , produces = {
		MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE
	}) 
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if(true) throw new UserServiceException("A user Service exception is thrown");
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId) , HttpStatus.OK);
		}
		else
		{
		   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
		}
	
	@PostMapping(produces = {
			MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE
		} , consumes = {
				MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsResponseModel userDetails) {

		UserRest userResponse = new UserRest();
		userResponse.setEmail(userDetails.getEmail());
		userResponse.setFirstName(userDetails.getFirstName());
		userResponse.setLastName(userDetails.getLastName());
		
		
		String userId = UUID.randomUUID().toString();
		userResponse.setUserId(userId);
		if(users == null) 
			users = new HashMap<>();
		users.put(userId, userResponse);
		
		return new ResponseEntity<UserRest>(userResponse , HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}" , consumes = {MediaType.APPLICATION_JSON_VALUE , 
											   MediaType.APPLICATION_XML_VALUE
											   })
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId , 
			                                   @Valid @RequestBody UpdateUserDetailsResponseModel userDetails)
	{
		if(users.get(userId)!= null) {
			UserRest userRest = users.get(userId);
			userRest.setFirstName(userDetails.getFirstName());
			userRest.setLastName(userDetails.getLastName());
			users.put(userId, userRest);
			return new ResponseEntity<UserRest>(userRest , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		
		if(users.get(userId)!=null) {
			users.remove(userId);
			//return new ResponseEntity<>("Record deleted successfully" , HttpStatus.OK);
			return ResponseEntity.noContent().build();
		}else {
			//return new ResponseEntity<>("Record not found" , HttpStatus.NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		
	}

}