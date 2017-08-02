package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.vo.UserVo;

public class WebUtil {
	
		
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
	
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}
	
	public static void redirect(HttpServletResponse response, String url) throws IOException {
		
		response.sendRedirect(url);
		
	}
	
	public static UserVo http(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		return authUser;
	}

}
