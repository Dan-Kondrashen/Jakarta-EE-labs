<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Territory"%>
<%@ page import="domain.Region"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Регионы</title>
<head>
<meta charset="UTF-8">

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
	<th scope="col">Редактировать</th> 
	<th scope="col">Удалить</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="ter" items="${territorys}">
		<tr>
		<td>${ter.getTerID()}</td>
		<td>${ter.getTerDesc()}</td>
		<td>${ter.getRegion()}</td>
		<td width="20">
			<a href='<c:url value="/editterritory?id=${ter.getTerID()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20">
			<a href='<c:url value="/deleteterritory?id=${ter.getTerID()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Удалить" src="images/icon-delete.png"></a>
		</td> 
		
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Добавление территории</h3>
	<div class="text-article">
		<form method="POST">		
			<div class="mb-3 row">
			<label for="terDesc" class="col-sm-3 col-form-label">Описание</label> 
			<div class="col-sm-7">
				<input type="text" name="terDesc" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
				<label for="region" class="col-sm-3 col-form-label">Регион</label>
				<div class="col-sm-7">
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
				<button type="submit">Добавить!</button>
			</p>
		</form>
		
	</div>
	</article>
	</section>
	</div>
	<jsp:include page="../jspf/footer.jsp" />

</body>
</html>