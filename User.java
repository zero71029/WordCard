package com.example.AAA;


import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class User {
    ArrayList<String> pa = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();


    User(String name) {
        System.out.println("User...................");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from USER ";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://wizard71029.synology.me:3306/AAA?useSSL=false&serverTimezone=UTC&useFractionalSeconds=true", "EEIT24", "AAAbbb111222@");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                username.add(rs.getString("username"));
                password.add(rs.getString("password"));
                //取得使用者
            }

            sql = "SELECT distinct package FROM "+name;
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //取得單字庫
                pa.add(rs.getString("package"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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


    }

    public ArrayList<String> getPa() {
        return pa;
    }
    public ArrayList<String> getUserName() {
        return username;
    }
    public ArrayList<String> getPassword() {
        return password;
    }
}
