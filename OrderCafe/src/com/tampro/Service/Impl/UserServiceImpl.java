package com.tampro.Service.Impl;

import java.util.List;

import com.tampro.Dao.UserRepository;
import com.tampro.Dao.Impl.UserRepositoryImpl;
import com.tampro.Model.User;
import com.tampro.Service.UserService;

public class UserServiceImpl  implements UserService{
	
	private static UserRepository  userRepository = new UserRepositoryImpl();

	@Override
	public User getUserById(int id) {
		
		// TODO Auto-generated method stub
		return userRepository.getUserById(id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.getAllUser();
	}

	@Override
	public User getUserByLogin(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.getUserByLogin(username, password);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userRepository.addUser(user);
	}

	@Override
	public void updateUserById(User user) {
		
		userRepository.updateUserById(user);
	}

	

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		if(user.getRole().equals("admin")) {
			return false;
		}else {
			userRepository.deleteUserById(user.getId());
			return true;
		}
		
	}

}
