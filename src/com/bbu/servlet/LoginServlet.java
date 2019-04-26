package com.bbu.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbu.model.PageBean;
import com.bbu.model.User;
import com.bbu.service.UserService;
import com.bbu.service.impl.UserServiceImpl;
import com.md5.MD5;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
//		MD5 md5 = new MD5();                        //
//		String passwordMD5 =md5.getResult(password);//
//		user.setPassword(passwordMD5);              //
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(username+"  "+password);
		if(userService.checkUserLogin(user)) {
			int currentPage=1;
			int pageSize=5;////5
			ArrayList<User> recordList=userService.getAllUserByPage(currentPage, pageSize);
			int recordCount=userService.getUserCount();
			PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
			request.setAttribute("pageBean", pageBean);
			//request.setAttribute("allUsers", userService.getAllUsers());
			request.getSession().setAttribute("user",user);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
