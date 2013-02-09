package org.zeropage.simplewiki.crud.simple;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.zeropage.simplewiki.crud.UserRepository;
import org.zeropage.simplewiki.model.User;

//@Repository
public class SimpleUserRepository implements UserRepository {
	@Autowired
	private UserDetailsManager manager;
	
	@Override
	public void delete(User user) {
		manager.deleteUser(user.getId());
	}

	@Override
	public void update(User user) {
		if(manager.userExists(user.getId())){
			manager.updateUser(manager.loadUserByUsername(user.getId()));
		}else{
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			manager.createUser(userDetails);
		}
	}

	@Override
	public User get(String id) {
		UserDetails userDetails = manager.loadUserByUsername(id);
		User user = new User(userDetails.getUsername(), userDetails.getPassword(), null);
		return user;
	}
}
