package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		if ("joinform".equals(actionName)) {
			System.out.println("join form들어왔습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp");
			rd.forward(request, response);

		} else if ("join".equals(actionName)) {

			System.out.println("join들어왔습니다.");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo(name, email, pass, gender);
			UserDao dao = new UserDao();
			dao.insert(vo);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp");
			rd.forward(request, response);

		} else if ("loginform".equals(actionName)) {

			System.out.println("로그인 폼에 들어왔습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			rd.forward(request, response);

		} else if ("login".equals(actionName)) {

			String email = request.getParameter("email");
			String pass = request.getParameter("password");

			UserDao dao = new UserDao();
			UserVo vo = dao.getUser(email, pass);

			if (vo == null) {
				
				System.out.println("실패");
				
				response.sendRedirect("/mysite/user?a=loginform&result=fail");
				
			} else {
				System.out.println("성공");
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo);

				response.sendRedirect("/mysite/main");
				return;
			}
		} else {

			response.sendRedirect("/mysite/main");
			// redirect로 다시 메인으로 보내준다.

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}