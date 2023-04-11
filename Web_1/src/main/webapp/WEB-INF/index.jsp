<%@page language="java" contentType="text/html"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
href="css/style.css">
<meta http-equiv="Content-Type" content="text/html"
charset="UTF-8">
<title>Главная страница</title>
</head>
<body>
<jsp:include page="../jspf/header.jsp" />
<div id="main">
<h2>Функции системы</h2>
<ul>
<li><a href="#">Сотрудники</a>
<li><a href="#">Должности</a>
</ul>
</div>
<jsp:include page="../jspf/footer.jsp" />
</body>
</html>