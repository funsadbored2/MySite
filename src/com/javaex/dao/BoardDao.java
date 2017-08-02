package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;

public class BoardDao {

	public List<BoardVo> getList() {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<BoardVo> list = new ArrayList<BoardVo>();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "select bo.no, bo.title, us.name, bo.hit, bo.rag_date, bo.user_no  " + 
						   "from board bo, users us " + 
						   "where bo.user_no = us.no " +
						   "order by no desc";	
			pstmt = conn.prepareStatement(query);
			System.out.println("다 넣었다");

			rs = pstmt.executeQuery();
			System.out.println("다 넣었다2");

			// 4.결과처리

			while (rs.next()) {

				BoardVo vo = new BoardVo();

				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setName(rs.getString("NAME"));
				vo.setHit_number(rs.getInt("HIT"));
				vo.setDate(rs.getString("RAG_DATE"));
				vo.setUser_no(rs.getInt("user_no"));

				list.add(vo);
			}

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}

		return list;
	}
	
	public int insert(String title, String content, int user_no) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO BOARD " + 
							"VALUES(SEQ_BAORD_ID.NEXTVAL, ?," + 
							"?,'0'" + 
							",SYSDATE,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, user_no);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이브 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			// 5. 자원정리

		}
		return count;
	}
	
	public BoardVo read(int no) {
		
		
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		BoardVo vo = new BoardVo();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "select title, content, user_no, no " + 
						   "from board " + 
						   "where no = ?";	
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {

				
				vo.setTitle(rs.getString("TITLE"));
				vo.setContent(rs.getString("content"));
				vo.setUser_no(rs.getInt("user_no"));
				vo.setNo(rs.getInt("NO"));

			}

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}

		return vo;
	}
	
	public void update(String title, String content, int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE BOARD " + 
						   "SET TITLE = ?," + 
						   "CONTENT = ? " + 
						   "WHERE NO = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 처리");
		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이브 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			// 5. 자원정리

		}
		
	}
	public void delete(int no) {
		Connection conn = null;

		PreparedStatement pstmt = null;
		
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "DELETE FROM BOARD WHERE NO = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);
			
			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error: " + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}
	}
	
	
	
}
