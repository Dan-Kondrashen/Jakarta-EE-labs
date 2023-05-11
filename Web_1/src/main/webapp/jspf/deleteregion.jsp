<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Region"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Удаление региона</title>
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
	</tr>
	</thead>
	<tbody>
	<c:forEach var="reg" items="${regions}">
		<tr>
		<td>${reg.getRegionID()}</td>
		<td>${reg.getRegionDesc()}</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Редактирование региона</h3>
	<div class="text-article">
		<form method="POST" action="">
			<div class="mb-3 row">
			<label for="id" class="col-sm-4 col-form-label">Код региона</label> 
			<div class="col-sm-6">
				<input type="text" name="id" class="form-control" readonly value="${regionDelete[0].getRegionID()}"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="regionName" class="col-sm-4 col-form-label">Название региона</label> 
			<div class="col-sm-6">
				<input type="text" name="regionName" class="form-control" readonly value="${regionDelete[0].getRegionName()}"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="regionDesc" class="col-sm-4 col-form-label">Описание региона</label> 
			<div class="col-sm-6">
				<input type="text" name="regionDesc" class="form-control" readonly value="${regionDelete[0].getRegionDesc()}"/>
			</div>
			</div>
			<p>
				<button type="submit" class="btn btn-secondary">Удалить</button>
				<a href='<c:url value="/region" />'role="button"class="btn btn-secondary">Отменить/Возврат</a>
			</p>
		</form>
		
	</div>
	</article>
	<jsp:include page="../jspf/footer.jsp" />
	</section>
	</div>
</body>
</html>