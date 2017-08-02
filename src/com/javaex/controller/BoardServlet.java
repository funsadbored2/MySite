package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		if ("insertform".equals(actionName)) {

			// user_no담긴 상태로 writeform으로 보낸다.
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeform.jsp");

		} else if ("insert".equals(actionName)) {

			System.out.println("insert들어옴");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			int no = authUser.getNo();

			BoardDao dao = new BoardDao();
			dao.insert(request.getParameter("title"), request.getParameter("content"), no);

			WebUtil.redirect(response, "/mysite/board");

		} else if ("read".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("READ들어옴");

			BoardDao dao = new BoardDao();

			BoardVo vo = dao.read(no);
			
			request.setAttribute("boardRead", vo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");

		} else if ("modifyform".equals(actionName)) {

			System.out.println("modifyform 들어옴");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			
			BoardVo vo = dao.read(no);
			
			request.setAttribute("modifyRead",vo);
					
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");

		} else if ("modify".equals(actionName)) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			dao.update(title, content, no);
			
			WebUtil.redirect(response, "/mysite/board");
			
			
		} else if ("delete".equals(actionName)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			dao.delete(no);
			
			WebUtil.redirect(response, "/mysite/board");

		} else {
			System.out.println("리스트");

			BoardDao dao = new BoardDao();

			List<BoardVo> list = dao.getList();

			request.setAttribute("boardList", list);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
