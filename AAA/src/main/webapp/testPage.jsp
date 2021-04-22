<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="zh-Hant-TW">
<head>
<meta charset="utf-8">
<title>測驗頁面</title>
<script src="js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="./CSS/p2.css">
<style>
html, body {
	background-image: url("./img/snow.jpg");
	background-size: cover;
}

::placeholder {
	color: red;
}

.modal {
	visibility: visible;
	background: rgba(11, 10, 17, .1);
}
.modal .container{
	background-image: url("./img/bbab4.jpg");
	background-size: cover;
}
</style>
</head>
<body>
	<canvas id="canvas"></canvas>
	<script src="./js/umbrella.js"></script>
	<div class="modal">
		<div class="container">
			<%--        關閉按鈕--%>
			<form action="/AAA/Servlet01" method="get">
				<input class="bClose" type="submit" value="x">
			</form>
			<%--    測試框架--%>
			<div id="scr"></div>
			<script>
            var AAA = "<%=session.getAttribute("Word").toString()%>";
				// console.log(bbb);
				// alert(AAA);
				var index = 0;
				//處理字串 轉成 en cn 陣列
				AAA = AAA.substr(1, AAA.length - 2);
				var bbb = AAA.split(", ");
				var en = [];
				var cn = [];
				for (var i = 0; i < bbb.length; i++) {
					en.push(bbb[i].substr(0, bbb[i].indexOf("=")));
					cn.push(bbb[i].substr(bbb[i].indexOf("=") + 1));
				}
				console.log(en);
				console.log(cn);

				var $scr = $("#scr");
				$scr.append("<H1 id = 'h'>" + cn[index] + "</h1>");//顯示中文
				var $h = $("#h");

				$scr.append(`<input id='intext' type="text">`);//創造輸入框
				var $intext = $("#intext");

				$scr.append(`<button id="but">確定</button>`);//創造按鈕
				var $but = $("#but");
				console.log(en[index]);
				//判斷對錯
				$but.on("click", function() {
					if ($intext.prop("value") == en[index]) {//答對進入下一題
						index++;
						if (index == en.length) {//結束測驗
							alert("完成測驗,恭喜過關");
							document.forms[0].action = "/AAA/Servlet01";
							document.forms[0].method = "get";
							document.forms[0].submit();
						}
						$h.text(cn[index]);
						$intext.prop("value", "");
						$intext.prop("placeholder", "");
						console.log(en[index]);
					} else {//答錯 顯示placeholder
						$intext.prop("value", "");
						$intext.prop("placeholder", en[index]);
					}
				});
			</script>
		</div>
	</div>
</body>
</html>
