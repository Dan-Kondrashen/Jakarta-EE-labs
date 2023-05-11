<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Territory"%>
<%@ page import="domain.Region"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Редактирование территории</title>
<head>
<!-- Bootstrap CSS -->

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
<h3>Список территорий</h3>
<table>
	<thead>
	<tr>
	<th scope="col">Код территории</th>
	<th scope="col">Описание территории</th>
	<th scope="col">Регион</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="ter" items="${territorys}">
		<tr>
		<td>${ter.getTerID()}</td>
		<td>${ter.getTerDesc()}</td>
		<td>${ter.getRegion()}</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Редактирование территории</h3>
	<div class="text-article">
		<form method="POST">	
			<div class="mb-3 row">
			<label for="id" class="col-sm-4 col-form-label">Код территории</label> 
			<div class="col-sm-6">
				<input type="text" name="id" class="form-control" readonly value="${territoryEdit[0].getTerID()}"/>
			</div>
			</div>	
			<div class="mb-3 row">
			<label for="terDesc" class="col-sm-4 col-form-label">Описание</label> 
			<div class="col-sm-6">
				<input type="text" name="terDesc" class="form-control" value="${territoryEdit[0].getTerDesc()}"/>
			</div>
			</div>
			<div class="mb-3 row">
				<label for="region" class="col-sm-4 col-form-label">Регион</label>
				<div class="col-sm-6">
					<select name="region" class="form-control">
						<option disabled>Выберите регион</option>
						<c:forEach var="region" items="${regions}">
							<option value="${region}">
							<c:out value="${region.getRegionName()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<p>
				<button type="submit">Отредактировать</button>
				<a href='<c:url value="/territory" />'role="button"class="btn btn-secondary">Отменить/Возврат</a>
			</p>
		</form>
		
	</div>
	</article>
	</section>
	</div>
	<jsp:include page="../jspf/footer.jsp" />

</body>
</html>