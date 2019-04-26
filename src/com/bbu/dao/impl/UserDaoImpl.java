package com.bbu.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.bbu.dao.UserDao;
import com.bbu.model.User;
import com.bbu.service.UserService;
import com.bbu.service.impl.UserServiceImpl;
import com.bbu.util.SQLHelper;

public class UserDaoImpl implements UserDao {
	private User user=new User();

	@Override
	public boolean addUser(User user) {
		boolean b=false;
		String sql="insert into login(username,password,grade,email) values(?,?,?,?)";
		String []parameters= {user.getUsername(),user.getPassword(),String.valueOf(user.getGrade()),user.getEmail()};
		try {
			SQLHelper.executeUpdate(sql, parameters);
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean deleteUser(String id) {
		//UserService userservice=new UserServiceImpl();
		boolean b=false;
		String sql="delete from login where id=?";
		String []parameters={id};
		try {
			SQLHelper.executeUpdate(sql, parameters);
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean b=false;
		String sql="update login set username=?,password=?,grade=?,email=? where id=?";
		String []parameters= {user.getUsername(),user.getPassword(),String.valueOf(user.getGrade()),user.getEmail(),String.valueOf(user.getId())};
		try {
			SQLHelper.executeUpdate(sql, parameters);
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public User findUserById(Integer id) {
		String sql="select * from login where id=?";
		String []parameters= {String.valueOf(id)};
		User user=SQLHelper.executeQueryUser(sql,parameters).get(0);
		return user;
	}

	@Override
	public ArrayList<User> findUserByName(String para) {       /////////User
		String sql="select from login where id in ("+para+")";//("+para+")"
		String []parameters= {};
		ArrayList<User> allUsers=SQLHelper.executeQueryUser(sql, parameters);
		return allUsers;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		String sql="select * from login";
		String []parameters= {};
		ArrayList<User> allUsers=SQLHelper.executeQueryUser(sql, parameters);//executeQueryUser
		return allUsers;
	}

	@Override
	public ArrayList<User> getAllUsersByPage(int currentPage, int pageSize) {
		ArrayList<User> arrayUserByPage=new ArrayList<User>();
		String sql="select * from login limit "+(currentPage-1)*pageSize+","+pageSize;
		String []parameters=null;
		arrayUserByPage=SQLHelper.executeQueryUser(sql, parameters);
		return arrayUserByPage;
	}

	@Override
	public Integer getUserCount() {
		int count = 0;
		String sql="select count(*) from login ";
		String []parameters=null;
		ResultSet rs=SQLHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			SQLHelper.close(SQLHelper.getRs(), SQLHelper.getPs(), SQLHelper.getConn());
		}
		return count;
	}

	@Override
	public boolean checkUserLogin(User user) {
		// TODO Auto-generated method stub
		boolean b=false;
		String sql="select *from login where username=? and password=?";
		String []parameters= {user.getUsername(),user.getPassword()};
		ArrayList<User> allUsers=SQLHelper.executeQueryUser(sql,parameters);
		if(allUsers.size()>0)
		{
			b=true;
		}
		return b;
	}
	@Override
	public boolean deleteUserBySelected(String para) {
		boolean b=false;
		String sql="delete from login where id in ("+para+")";//("+para+")"
		try {
			SQLHelper.executeUpdate2(sql);
			b=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
