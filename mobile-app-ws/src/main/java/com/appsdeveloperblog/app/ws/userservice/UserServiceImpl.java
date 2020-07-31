package com.appsdeveloperblog.app.ws.userservice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

@Service
public class UserServiceImpl implements UserService {
	
	Map<String , UserRest> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailsResponseModel userDetails) {
		
		UserRest userResponse = new UserRest();
		userResponse.setEmail(userDetails.getEmail());
		userResponse.setFirstName(userDetails.getFirstName());
		userResponse.setLastName(userDetails.getLastName());
		
		
		String userId = utils.generateUserId();
		userResponse.setUserId(userId);
		if(users == null) 
			users = new HashMap<>();
		users.put(userId, userResponse);
		return userResponse;
	}

}
