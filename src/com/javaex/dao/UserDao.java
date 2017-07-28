package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	public int insert(UserVo vo) {

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
			String query = "INSERT INTO USERS VALUES(SEQ_USERS_NUMBER.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPass());
			pstmt.setString(4, vo.getGender());

			count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 4.결과처리

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

	public UserVo getUser(String email, String pass) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		UserVo vo = null;
		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "select no, name from USERS where email =? and password = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
			int no = rs.getInt("no");
			String name = rs.getString("name");

			vo = new UserVo();

			vo.setNo(no);
			vo.setName(name);
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

}
