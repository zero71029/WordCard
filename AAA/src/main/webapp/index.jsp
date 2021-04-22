<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
  <title>JSP - Hello World</title>
  <style>
    body {
      background-color: #0d1327;
    }
    #timeCenter {
      color: white;
      position: relative;
      top: 350px;
      margin: auto;
      width: 40px;
      font-size: 0.9rem;
    }
    form{
      position: absolute;
      left: 45%;
      top: 710px;

    }
  </style>
</head>
<body>

<div id="timeCenter">Error</div>
<script src="js/time.js"></script>
<form action="/AAA/Servlet01" method="post">
  <input type="text" placeholder="帳號" name="userName" value="AAA"><br>
  <input type="password" placeholder="密碼"name="userPassword" value="aaa"><br>
  <input type="submit" value="登入">
</form>
<br>
<script type="text/javascript">
  var search = window.location.search;
  if (search.indexOf("?") != -1) {
    alert("帳號密碼錯誤");
  }
</script>

</body>
</html>