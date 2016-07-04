
package com.cristian.tareask.service;

import java.util.List;
import com.cristian.tareask.model.User;

public interface UserService {
	public void add(User user);
	public void edit(User user);
	public void delete(int UserId);
	public User getUser(int UserId);
	public List getAllUsers();
        public User getUserByName(String UserName);
        public User getUserByEmail(String email);    
}
