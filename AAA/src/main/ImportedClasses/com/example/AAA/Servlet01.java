package com.example.AAA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Servlet01", value = "/Servlet01")
public class Servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet01 doGet...............................................");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");//取得使用者
        String nowPa = request.getParameter("pa");

        User user = new User(name);
        ArrayList<String> pa = user.getPa();
        if(nowPa==null)nowPa=pa.get(0);


        Word word = new Word(name ,nowPa);//找包裡的單詞
        HashMap<String, String> hm = word.getWord();//取的單字庫裡單詞
        session.setAttribute("nowPa", nowPa);
        session.setAttribute("pa", pa);
        session.setAttribute("Word", hm);//發送單字庫裡單詞
        response.sendRedirect("/AAA/P2.jsp?name=" + name);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet01 Pos...............................................");
        boolean index = false;
        HttpSession session = request.getSession();
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html,charset=utf-8");
        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");




        User user = new User(name);//驗證使用者  取得使用者單字庫
        ArrayList<String> getUserName = user.getUserName();
        ArrayList<String> getPassword = user.getPassword();
        for (int i = 0; i < getUserName.size(); i++) {
            if (getUserName.get(i).equals(name)) {
                if (getPassword.get(i).equals(password)) {
                index = true;
                break;
                }
            }
        }
        if (index) {
            ArrayList<String> pa = user.getPa();
            Word word = new Word(name, pa.get(0));// 取的庫裡單詞
            HashMap<String, String> hm = word.getWord();
            session.setAttribute("Word", hm);
            session.setAttribute("pa", pa);
            session.setAttribute("name", name);// 記錄使用者
            session.setAttribute("nowPa", pa.get(0));
            response.sendRedirect("/AAA/P2.jsp?name=" + name);
        } else {
            response.sendRedirect("/AAA/index.jsp?model=error");
        }
    }
}
