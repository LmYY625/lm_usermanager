package com.bbu.service.impl;

import java.util.ArrayList;

import com.bbu.dao.UserDao;
import com.bbu.dao.impl.UserDaoImpl;
import com.bbu.model.User;
import com.bbu.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao=new UserDaoImpl();

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.modifyUser(user);
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

	@Override
	public ArrayList<User> findUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(name);
	}

	@Override
	public ArrayList<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public ArrayList<User> getAllUserByPage(int currentPage, int pageSize) {
		return userDao.getAllUsersByPage(currentPage, pageSize);
	}

	@Override
	public Integer getUserCount() {
		return userDao.getUserCount();
	}

	@Override
	public boolean checkUserLogin(User user) {
		return userDao.checkUserLogin(user);
	}
	@Override
	public boolean deleteUserBySelected(String para) {
		return userDao.deleteUserBySelected(para);
	}

}
