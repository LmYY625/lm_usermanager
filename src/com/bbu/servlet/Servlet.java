package com.bbu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbu.model.User;
import com.bbu.service.UserService;
import com.bbu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	private UserService userService=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");		
		if("delete".equals(type)) {
			String id=request.getParameter("id");
			User user=new User();
			user.setId(Integer.parseInt(id));
			System.out.println(user.getId());
			if(userService.deleteUser(id)) {
				System.out.println(user.getId());
				request.setAttribute("allUsers", userService.getAllUsers());
				System.out.println(id);
				request.getSession().setAttribute("user", user);
				System.out.println(id);
				request.getRequestDispatcher("list.jsp").forward(request, response);
			}
		}		
	}

}
