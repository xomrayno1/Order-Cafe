package com.tampro.Dao;

import java.util.List;

import com.tampro.Model.User;

public interface UserRepository {
	public User getUserById(int id);

	public List<User> getAllUser();

	public User getUserByLogin(String username, String password);

	public void addUser(User user);

	public void updateUserById(User user);

	public void deleteUserById(int id);

}
