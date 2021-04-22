<%@page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="CSS/p2.css">
    <script src="./js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        var cn = [];
        var en = [];
    </script>
</head>
<body>


<!--新增單字-->

<div class="modal">
    <div class="container">
        <button class="bClose" onclick="bClose()">X</button>
        <form id="CreatePackage" action="/AAA/FindWord" method="get">
            <input class="mInput" type="text" placeholder="請輸入單字" name="newEn"><br>
            <input class="mInput" type="submit" value="劍橋翻譯"><br>
        </form>
    </div>
</div>

<!--輪播圖-->
<div class="PaModal">
    <div class="BBB">
        <button class="bClose" onclick="bClose()">X</button>
    <div class="banner">

        <div class="list">
            <%
                ArrayList<String> keylist2 = new ArrayList<String>();
                HashMap<String, String> Word2 = (HashMap<String, String>) session.getAttribute("Word");

                for (String s : Word2.keySet()) {
                    keylist2.add(s);

                }
                out.println("");
                out.println("<img src=\"./img/starrs01.jpg\" >");

                for (int i = 0; i < keylist2.size(); i++) {
                    out.println("<img src=\"./img/starrs01.jpg\" >");

                    out.println("<script>cn.push('" + Word2.get(keylist2.get(i)) + "')</script>");
                    out.println("<script>en.push('" + keylist2.get(i) + "')</script>");
                    /*audio*/
                    out.println("<audio class='audio' src='./mp3/" + keylist2.get(i) + ".mp3' controls ></audio>");
                }
                out.println("<img src=\"./img/starrs01.jpg\" >");

                // 				文字部分
                out.println("<div id='Word'>");
                out.println("<div class='wordName'>" + keylist2.get(keylist2.size() - 1) + "</div>");
                for (int i = 0; i < keylist2.size(); i++) {
                    /*文字*/
                    out.println("<div class='wordName'>" + keylist2.get(i));
                    /*撥放按鈕*/
                    out.println(" <button class='play'; onclick='play(" + i + ")''></button>");

                    /*刪除*/
                    out.println("<button class='del' onclick='location.href=\"/AAA/wordDel?del="+keylist2.get(i)+"\"'>DEL</button>");
                    out.println("</div>");
                }
                out.println("<div class='wordName'>" + keylist2.get(0) + "</div>");
                out.println("</div>");
            %>

        </div>
        <button class="arrow" id="prev"></button>
        <button class="arrow" id="next"></button>

    </div>
    </div>
</div>

<!-- 	//流星背景 -->
<canvas id="stars"></canvas>
<script src="js/star.js"></script>
<!-- 	上面 -->
<header>
    <!-- 測驗按鈕		 -->
    <form action="/AAA/testPage.jsp">
        <input id="goTest" type="submit" value="拼字練習">
    </form>
</header>
<!-- 	//左邊單字庫 -->
<div id="leftDiv">
    <ul id="leftUl">
        <!-- 			單字庫 -->
        <%
            System.out.println("hello P2 ");
            ArrayList pa = (ArrayList) session.getAttribute("pa");

            for (int i = 0; i < pa.size(); i++)
                out.print("<li><A href='/AAA/Servlet01?pa=" + pa.get(i) + "'>" + pa.get(i) + "</A></li><br>");
        %>
        <li class="CreatePackage" onclick="CreatePackage()">新增單字庫</li>

    </ul>
</div>
<div id="centerDiv">
    <!-- 		      單字部分小圖 -->
    <%
        ArrayList<String> keylist = new ArrayList<String>();
        HashMap<String, String> Word = (HashMap<String, String>) session.getAttribute("Word");

        for (String s : Word.keySet()) {
            keylist.add(s);
        }
        for (int i = 0; i < keylist.size(); i++) {
            out.println("");
            out.println("<div class='AAA' onclick=banner(" + i + ") onclick=a" + keylist.get(i) + "() ><p id='" + keylist.get(i)
                    + "'>" + keylist.get(i) + "</p></div>");
            out.println("<script>" + "function a" + keylist.get(i) + "() {\n" + "var temp = document.getElementById('"
                    + keylist.get(i) + "');\n" + "if (temp.innerHTML == '" + keylist.get(i) + "') {\n" + "temp.innerHTML ='"
                    + keylist.get(i) + "<br>" + Word.get(keylist.get(i)) + "'" + "} else {\n" + "temp.innerHTML = '"
                    + keylist.get(i) + "';\n" + "  } \n}</script>");
        }
        if (keylist.size() < 10) {
            out.println("<div class='AAA' onclick='newWord()' ><p id= 'newWord'>+</p></div>");
        }
    %>


</div>

<script src="js/p2.js"></script>

</body>
</html>
