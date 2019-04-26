package com.bbu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService=new UserServiceImpl();
	//private int currentPage;
	//private int pageSize;
	//private int recordCount;
	int currentPage = 1; //从index.jsp跳转而来，是guest第一次访问，所以开始页面为1
	int pageSize = 5;
	private Object pageBean; 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		if("delete".equals(type)) {
			String id=request.getParameter("id");
			User user=new User();
			user.setId(Integer.parseInt(id));
			if(userService.deleteUser(id)) {
				request.setAttribute("allUsers", userService.getAllUsers());
				request.getSession().setAttribute("myuser",user);
				ArrayList<User> recordList = userService.getAllUserByPage(currentPage, pageSize);
				int recordCount = userService.getUserCount();
				PageBean pageBean = new PageBean(currentPage,pageSize,recordCount,recordList);
				request.setAttribute("pageBean", pageBean);
				request.getRequestDispatcher("list.jsp").forward(request, response);
				//PageBean pageBean = new PageBean(currentPage,pageSize,recordCount,recordList);
				/*ArrayList<User> recordList=userService.getAllUserByPage(currentPage, pageSize);
				request.setAttribute("pageBean", pageBean);
				
				request.setAttribute("allUsers", userService.getAllUsers());///
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/list.jsp").forward(request, response);*/
			}
	}else if("modifyUi".equals(type)){
		String id=request.getParameter("id");
		User user=userService.findUserById(Integer.parseInt(id));
		request.setAttribute("user", user);
		request.getRequestDispatcher("modifyUi.jsp").forward(request, response);
	}else if("modify".equals(type)) {
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String grade=request.getParameter("grade");
		String email=request.getParameter("email");
		User user=new User();
		user.setId(Integer.parseInt(id));
		user.setUsername(username);
		user.setPassword(password);
		user.setGrade(Integer.parseInt(grade));
		user.setEmail(email);
		userService.modifyUser(user);
		if(userService.modifyUser(user)) {
			request.setAttribute("allUsers", userService.getAllUsers());
			request.getSession().setAttribute("user1",user);
			ArrayList<User> recordList = userService.getAllUserByPage(currentPage, pageSize);
			int recordCount = userService.getUserCount();
			PageBean pageBean = new PageBean(currentPage,pageSize,recordCount,recordList);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}else if("delBySelected".equals(type)) {
		String []para=request.getParameterValues("myselect");
		StringBuffer inParams=new StringBuffer();
		for(int i=0;i<para.length;i++) {
			inParams.append(para[i]);
			inParams.append(",");
		}
		String para2=inParams.substring(0,inParams.length()-1);
		userService.deleteUserBySelected(para2);
		ArrayList<User> recordList = userService.getAllUserByPage(currentPage, pageSize);
		int recordCount = userService.getUserCount();
		PageBean pageBean = new PageBean(currentPage,pageSize,recordCount,recordList);
		request.setAttribute("pageBean", pageBean);
    	request.setAttribute("allUsers", userService.getAllUsers());
    	//request.getSession().setAttribute("user",user);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}else if("add".equals(type)) {
		User user=new User();
		MD5 md5 = new MD5();                            // 
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String passwordMD5 =md5.getResult(password);    //
		String grade=request.getParameter("grade");
		String email=request.getParameter("email");
		user.setUsername(username);
		user.setPassword(password);
		user.setPassword(passwordMD5);                 //
		user.setGrade(Integer.parseInt(grade));
		user.setEmail(email);   //userService.addUser(user);
		ArrayList<User> recordList = userService.getAllUserByPage(currentPage, pageSize);
		int recordCount=userService.getUserCount();
		PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
		request.setAttribute("pageBean", pageBean);
		if(userService.addUser(user)) {
			request.setAttribute("allUsers", userService.getAllUsers());
			request.getSession().setAttribute("myuser1",user);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
 }else if("destory".equals(type)) {
	 if(null!=request.getSession()) {
		 request.getSession().invalidate();
		 request.getRequestDispatcher("login.jsp").forward(request, response);
	 }
 }else if("quit".equals(type)) {
	 ArrayList<User> recordList = userService.getAllUserByPage(currentPage, pageSize);
	 int recordCount=userService.getUserCount();
	 PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
	 request.setAttribute("pageBean", pageBean);////
	 if(true) {
	 User user=new User();
	 request.setAttribute("allUsers", userService.getAllUsers());
	 request.getSession().setAttribute("user",user);
	 request.getRequestDispatcher("list.jsp").forward(request, response);
	 }
 }
}
}

