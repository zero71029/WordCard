package com.example.AAA;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//上網找翻譯
@WebServlet(name = "FindWord", value = "/FindWord")
public class FindWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello FindWord doget................");
        String newEn = request.getParameter("newEn");
        String UUU = "https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/" + newEn;
        ArrayList<String> cn = new ArrayList();
        try {
            URL url = new URL( UUU);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line ;

            while ((line = reader.readLine()) != null ){
                if (line.contains("<span class=\"trans dtrans dtrans-se  break-cj\" lang=\"zh-Hant\">")) {

                    cn.add(line.substring(70, line.indexOf("<", 70)));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("找不到翻譯");
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("newEn",newEn);
        session.setAttribute("findWord",cn);
        response.sendRedirect("/AAA/Sound" );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
