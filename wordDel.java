package com.example.AAA;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class wordDel
 */
@WebServlet("/wordDel")
public class wordDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public wordDel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 取得資料
		System.out.println("wordDel..............");
		String wordDel = request.getParameter("del");
		HttpSession session = request.getSession();
		String nowPa = (String) session.getAttribute("nowPa");
		String name = (String) session.getAttribute("name");
		// JDBC
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://wizard71029.synology.me:3306t");
			stmt = conn.createStatement();
			sql = "DELETE FROM " + name + " WHERE package='" + nowPa + "' and en='" + wordDel+"'";
			
			System.out.println(sql);
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				System.out.println("刪除成功");
			} else {
				System.out.println("刪除失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				System.out.println("conn.close()");
				conn.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		response.sendRedirect("/AAA/Servlet01");
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
