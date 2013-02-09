package org.zeropage.simplewiki.crud;

import org.zeropage.simplewiki.model.User;

public interface UserRepository {
	public void delete(User user);
	public void update(User user);
	public User get(String id);
}
