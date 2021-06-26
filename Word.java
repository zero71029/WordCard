package com.example.AAA;

import java.sql.*;
import java.util.HashMap;
//讀取資料庫單辭
public class Word {
    HashMap<String, String> Word = new HashMap();

    public Word(String name,String pa) {
        System.out.println("Word...................");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://wizard71029.synology.me:3306/AAA");
            stmt = conn.createStatement();


            sql = "SELECT * FROM "+name+" where package ='" + pa + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String en = rs.getString("en");
                String cn = rs.getString("cn");
                Word.put(en, cn);

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

    public HashMap getWord() {
        return Word;
    }
}
