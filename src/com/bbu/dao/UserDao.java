package com.bbu.dao;

import java.util.ArrayList;

import com.bbu.model.User;

public interface UserDao {
	public boolean addUser(User user);
	public boolean deleteUser(String id);
	public boolean modifyUser(User user);
	public User findUserById(Integer id);
	public ArrayList<User> findUserByName(String name);
	public ArrayList<User> getAllUsers();
	public ArrayList<User> getAllUsersByPage(int currentPage,int pageSize);
	public Integer getUserCount();
	public boolean checkUserLogin(User user);
	public boolean deleteUserBySelected(String para);
}
