package com.bbu.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharsetEncodingFilter
 */
@WebFilter("/CharsetEncodingFilter")
public class CharsetEncodingFilter implements Filter {
	protected String encoding=null;
	protected FilterConfig filterConfig=null;

	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(null!=encoding) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charser="+encoding);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");
	}
//	private static String encoding; // 定义变量接收初始化的值
//	 
//	public void destroy() {
//		
//	}
// 
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		// 设置字符编码链锁
//		request.setCharacterEncoding(encoding);
//		response.setCharacterEncoding(encoding);
//		chain.doFilter(request, response);
//		
//	}
//	// 初始化
//	public void init(FilterConfig config) throws ServletException {
//		// 接收web.xml配置文件中的初始参数
//		encoding = config.getInitParameter("CharsetEncoding");
//		
//	}
//


}
