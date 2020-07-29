package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	
	@GetMapping
	public String getUsers(@RequestParam(value="page" , defaultValue = "1" , required = false) int page , 
						   @RequestParam(value = "limit" , defaultValue = "50") int limit , 
						   @RequestParam(value = "sort" ,defaultValue = "desc", required = false)String sort)  {
		return "get users method was called with page = " + page + " and limit = " + limit +
				       " , sort = " + sort;
	}
	
	@GetMapping(path="/{userId}")
	public UserRest getUser(@PathVariable String userId) {
		UserRest userResponse = new UserRest();
		userResponse.setEmail("ac@mail.com");
		userResponse.setFirstName("Alex");
		userResponse.setLastName("Ciub");
		userResponse.setUserId(userId);
		return userResponse;
	}
	
	@PostMapping
	public String createUser() {
		return "post method , create user";
	}
	
	@PutMapping
	public String updateUser() {
		return "put method , update user";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete method , delete user";
	}

}