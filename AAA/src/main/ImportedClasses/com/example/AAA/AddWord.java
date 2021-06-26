package com.example.AAA;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "AddWord", value = "/AddWord")
public class AddWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello AssWord doGet...........");
        String newCn = request.getParameter("newCn");
        HttpSession session = request.getSession();
        String newEn = (String) session.getAttribute("newEn");
        String nowPa = (String) session.getAttribute("nowPa");
        String name = (String) session.getAttribute("name");
        Connection conn = null;
        Statement stmt = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://wizard71029.synology.me:3306/");
            stmt = conn.createStatement();

            int count = stmt.executeUpdate("insert into " + name + "(package,en,cn) values('" + nowPa + "','" + newEn + "','" + newCn + "')");
            System.out.println("insert into " + name + "(package,en,cn) values('" + nowPa + "','" + newEn + "','" + newCn + "')");
            stmt.executeUpdate("delete from " + name + " where en is null");
            System.out.println("delete from " + name + " where en is null");
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失敗");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        }
        response.sendRedirect("/AAA/Servlet01?pa=" + nowPa);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
