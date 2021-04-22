<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head >
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./CSS/p2.css">
    <style>
        .modal {
            visibility: visible;
        }

    </style>
</head>

<body>
<!-- //流星 -->
<canvas id="stars"></canvas>
<script src="js/star.js"></script>

<div class="modal">
    <div class="container">
       <!-- //關閉按鈕 -->
        <form action="/AAA/Servlet01" method="get">
            <input class="bClose" type="submit" value="X"><br><br>
        </form>


        <form action="/AAA/AddWord" method="get">
            <input class="mInput" type="text" placeholder="請輸入翻譯" name="newCn" ><br>
            <input class="mInput" type="submit" value="確定"><br><br>
        </form>
            <%
//                取得翻譯 生出按鈕
                ArrayList<String> findWord = (ArrayList<String>) session.getAttribute("findWord");
                for (int i = 0; i < findWord.size(); i++) {
                    System.out.println(findWord.get(i));
                    out.println("<form action=\"/AAA/AddWord\" method=\"get\">");
                    out.print("<input class=\"mInput\" type=\"text\" value="+findWord.get(i)+" name=\"newCn\"\n" +
                            "style=\"display: none\">");
                    out.println(" <input class='mInput' value='" + findWord.get(i) + "' type='button'   onclick=\"this.form.action='/AAA/AddWord?newCn="+findWord.get(i)+"';this.form.method='get';this.form.submit();\"  ><br>");
                    out.println("</form>");
                }
            %>

    </div>
</div>


</body>
</html>
