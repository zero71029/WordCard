package com.example.AAA;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

/**
 * Servlet implementation class Sound
 */
@WebServlet("/Sound")
public class Sound extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sound() {
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
		System.out.println("Sound doGet .............");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String newEn = (String) session.getAttribute("newEn");
		String sURL = new String();
		String UUU = "https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/"
				+ newEn;
		try {
			URL url = new URL(UUU);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.contains("mp3")) {
					String temp = line.substring(line.indexOf("%E7%B9%81%E9%AB%94/") + 19);
					String AAA = temp.substring(0, temp.indexOf(".mp3\"/>"));
					sURL = "https://dictionary.cambridge.org/zht/media/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/"
							+ AAA + ".mp3";
				}
			}
			reader.close();
			url = new URL(sURL);
			conn = (HttpURLConnection) url.openConnection();
			;
			conn.connect();

			FileOutputStream fout = new FileOutputStream( newEn + ".mp3");
			InputStream in = conn.getInputStream();
			byte[] buf = new byte[4 * 1024];
			int len;
			while ((len = in.read(buf)) != -1) {
				fout.write(buf, 0, len);
			}
			in.close();
			fout.flush();
			fout.close();
			System.out.println("download OK");

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("找不到翻譯");
			e.printStackTrace();
		}
	       response.sendRedirect("/AAA/findWord.jsp" );

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
