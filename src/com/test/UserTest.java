package com.test;

import java.util.ArrayList;

import org.junit.Test;

import com.bbu.dao.UserDao;
import com.bbu.dao.impl.UserDaoImpl;
import com.bbu.model.User;
import com.bbu.service.UserService;
import com.bbu.service.impl.UserServiceImpl;
import com.md5.*;
import com.mysql.jdbc.StandardSocketFactory;

public class UserTest {
	private UserService userservice=new UserServiceImpl();
	private UserDao userDao=new UserDaoImpl();
	private User user=new User();
	private String inputStr;
	@Test
	public void checkUserLogin() {
		//UserDao userDao=new UserDaoImpl();
		//User user=new User();
		user.setUsername("陆美");
		user.setPassword("123");
		System.out.println(userDao.checkUserLogin(user));
	}
	@Test
	public void getAllUsers() {
		//UserDao userDao=new UserDaoImpl();
		ArrayList<User>allUsers=userDao.getAllUsers();
		for(int i=0;i<allUsers.size();i++) {
			User user=allUsers.get(i);
			System.out.println(user.getUsername());
	}
  }
	@Test
	public void delectUser() {
		//UserService userservice=new UserServiceImpl();
		userservice.deleteUser("4");
	}
	@Test
	public void modifyUser() {
		//User user=new User(2,"123","123",2,"1@qq.com");
		//UserService us=new UserServiceImpl();
		user.setId(2);
		user.setUsername("llmm");
		user.setPassword("111");
		user.setGrade(1);
		user.setEmail("123@qq.com");
		userservice.modifyUser(user);
	}
	@Test
	public void findUserById() {
		userservice.findUserById(1);
		System.out.println(user.getUsername()+"   "+user.getEmail());
	}
	@Test
	public void getUserCount() {
		System.out.println(userservice.getUserCount());
	}
	@Test
	public void getAllUserByPage() {
		System.out.println(userservice.getAllUserByPage(2, 2));
	}
	@Test
	public void MD5() {
		MD5 md5 = new MD5();
		System.out.println(md5.getResult("123456"+"tom"));
		System.out.println(md5.getResult("123456"));
		//md5.getResult(inputStr);
	}
}
