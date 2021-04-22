package com.example.AAA;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AddPa", value = "/AddPa")
public class AddPa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello AddPa get..................");
        String paName = request.getParameter("paName");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://wizard71029.synology.me:3306/AAA?useSSL=false&serverTimezone=UTC&useFractionalSeconds=true", "EEIT24", "AAAbbb111222@");
            stmt = conn.createStatement();
            sql = "insert into "+name+"(package) values ('" + paName + "')";
            System.out.println(sql);
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失敗");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
