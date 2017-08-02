package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestbookServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");
		
		if ("add".equals(actionName)) {

			System.out.println("add항목 들어왔음");;

			String name = request.getParameter("name");
			String pass = request.getParameter("password");
			String content = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo(name, pass, content);
			GuestbookDao dao = new GuestbookDao();
			dao.add(vo);
		
			WebUtil.redirect(response, "/mysite/guestbook");
			
		}	else if ("deleteform".equals(actionName)) {

			// delete전 비밀번호 확인하는 부분으로 사용자 입력 값 가져오기
			System.out.println("deleteform으로 들어옴");

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");

		} else if ("delete".equals(actionName)) {
			GuestbookDao dao = new GuestbookDao();

			String pw = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no"));

			dao.delete(pw, no);

			WebUtil.redirect(response, "/mysite/guestbook");
			
		} else {

			System.out.println("리스트로");

			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();

			request.setAttribute("list", list);

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
