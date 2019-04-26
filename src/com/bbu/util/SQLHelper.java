package com.bbu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.bbu.model.User;
import com.mysql.jdbc.PreparedStatement;

public class SQLHelper {
	private static Connection conn=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static String url="";
	private static String user="";
	private static String password="";
	private static String driver="";
	private static Properties pp=null;
	private static InputStream fis=null;
	static {
		try {
			pp=new Properties();
			fis=SQLHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
			pp.load(fis);
			url=pp.getProperty("url");
			user=pp.getProperty("user");
			password=pp.getProperty("password");
			driver=pp.getProperty("driver");
			Class.forName("com.mysql.jdbc.Driver");///driver
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static Connection getConnection() throws SQLException{
		try {
			conn=DriverManager.getConnection(url,user,password);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	public static ArrayList<User> executeQueryUser(String sql, String[] parameters) {
		ArrayList<User> arraylist=new ArrayList<User>();
		try {
			conn=getConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1,parameters[i]);
				}
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setGrade(rs.getInt(4));
				user.setEmail(rs.getString(5));
				arraylist.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			SQLHelper.close(SQLHelper.getRs(),SQLHelper.getPs(),SQLHelper.getConn());
		}
		return arraylist;
	}
	public static void close(Object rs2, Object ps2, Object conn2) {
		
	}
	public static Connection getConn() {
		// TODO Auto-generated method stub
		return conn;
	}
	public static PreparedStatement getPs() {
		// TODO Auto-generated method stub
		return ps;
	}
	public static Object getRs() {
		// TODO Auto-generated method stub
		return rs;
	}
	public static void executeUpdate(String sql,String []parameters) {
		try {
			conn=getConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1, parameters[i]);
				}
				ps.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
	}
	public static void executeUpdate2(String sql){
		  try {
			  	conn=getConnection();
			  	ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			//	throw new RuntimeException();				
			}finally{
				close(rs,ps,conn);
			}			  
	  }

	public static void close(ResultSet rs,PreparedStatement ps,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(ps!=null) {
			try {
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			ps=null;
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	public static ResultSet executeQuery(String sql,String []parameters) {
		try {
			conn=getConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);	
			//ps.executeUpdate();
			if(parameters!=null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
