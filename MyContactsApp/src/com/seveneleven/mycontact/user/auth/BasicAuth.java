package com.seveneleven.mycontact.user.auth;
import com.seveneleven.mycontact.user.model.User;
import java.util.List;

public class BasicAuth {
	
	public User login(String username, String password, List<User>users) {
		for(User user : users) {
			if(user.getUsername().equals(username)&& user.getPassword().equals(password)) {
				return user;
				//success
			}
		}
		return null;
		//failure
	}

}
