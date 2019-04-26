package com.bbu.service;

import java.util.ArrayList;

import com.bbu.model.User;

public interface UserService {
	public boolean addUser(User user);
	public boolean deleteUser(String id);
	public boolean modifyUser(User user);
	public User findUserById(Integer i);
	public ArrayList<User> findUserByName(String name);
	public ArrayList<User> getAllUsers();
	public ArrayList<User> getAllUserByPage(int currentPage,int pageSize);
	public Integer getUserCount();
	public boolean checkUserLogin(User user);
	public boolean deleteUserBySelected(String para);

}
