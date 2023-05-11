<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Region"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Регионы</title>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>
<body>
<jsp:include page="../jspf/header.jsp" />
<div id="main">
<section>
<aside class="leftAside">
<h3>Список регионов</h3>
<table>
	<thead>
	<tr>
	<th scope="col">Код региона</th>
	<th scope="col">Описание</th>
	<th scope="col">Редактировать</th> 
	<th scope="col">Удалить</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="reg" items="${regions}">
		<tr>
		<td>${reg.getRegionID()}</td>
		<td>${reg.getRegionDesc()}</td>
		<td width="20">
			<a href='<c:url value="/editregion?id=${reg.getRegionID()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20">
			<a href='<c:url value="/deleteregion?id=${reg.getRegionID()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Удалить" src="images/icon-delete.png"></a>
		</td> 
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Добавление региона</h3>
	<div class="text-article">
		<form method="POST" action="">
			<div class="mb-3 row">
			<label for="id" class="col-sm-3 col-form-label">Код региона</label> 
			<div class="col-sm-7">
				<input type="text" name="id" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="regionName" class="col-sm-3 col-form-label">Название региона</label> 
			<div class="col-sm-7">
				<input type="text" name="regionName" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="regionDesc" class="col-sm-3 col-form-label">Описание региона</label> 
			<div class="col-sm-7">
				<input type="text" name="regionDesc" class="form-control"/>
			</div>
			</div>
			<button type="submit">Добавить!</button>
		</form>
		
	</div>
	</article>
	<jsp:include page="../jspf/footer.jsp" />
	</section>
	</div>
	

</body>
</html>