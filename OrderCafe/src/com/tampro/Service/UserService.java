package com.tampro.Service;

import java.util.List;

import com.tampro.Model.User;

public interface UserService {
	
	public User getUserById(int id);
	
	public List<User> getAllUser();
	
	public User getUserByLogin(String username,String password);
	
	public void addUser(User user);
	
	public void updateUserById(User user);
	
	public boolean deleteUser(User user);

}
