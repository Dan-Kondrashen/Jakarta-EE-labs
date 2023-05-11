<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Territory"%>
<%@ page import="domain.Employee"%>
<%@ page import="domain.EmployeeTerritory"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>Назначение работников по определенным территориям</title>
<head>
<meta charset="UTF-8">
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
<h3>Список соотношений</h3>
<table>
	<thead>
	<tr>
	<th scope="col">ID связи</th>
	<th scope="col">Фио работника</th>
	<th scope="col">Название территории</th>
	<th scope="col">Редактировать</th> 
	<th scope="col">Удалить</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="emp" items="${empter}">
		<tr>
		
		<td>${emp.getId()}</td>
		<td>${emp.getEmploy()}</td>
		<td>${emp.getTerr()}</td>
		<td width="20">
			<a href='<c:url value="/editempterr?id=${emp.getId()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Редактировать" src="images/icon-edit.png"></a>
		</td> 
		<td width="20">
			<a href='<c:url value="/deleteempterr?id=${emp.getId()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Удалить" src="images/icon-delete.png"></a>
		</td> 
		
		</tr>
	</c:forEach>
	</tbody>
	</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Добавить связь</h3>
	<div class="text-article">
		<form method="POST" action="">
			
		<div class="mb-3 row">
			<label for="territory" class="col-sm-3 col-form-label">Название территории</label> 
			<div class="col-sm-7">
				<select name="territory" class="form-control">
						<option disabled>Выберите регион</option>
						<c:forEach var="terr" items="${territory}">
							<option value="${terr}">
							<c:out value="${terr.getTerDesc()}"></c:out>
							</option>
						</c:forEach>
				</select>
			</div>
		</div>
		<div class="mb-3 row">
			<label for="employee" class="col-sm-3 col-form-label">ФИО работника</label> 
			<div class="col-sm-7">
				<select name="employee" class="form-control">
						<option disabled>Выберите регион</option>
						<c:forEach var="emp" items="${employee}">
							<option value="${emp}">
							<c:out value="${emp.getEmail()}"></c:out>
							</option>
						</c:forEach>
				</select>
			</div>
		</div>
		<p>
			<button type="submit" class="btn btn-secondary">Добавить!</button>
		</p>
		</form>
		
	</div>
	</article>
	</section>
	</div>
	<jsp:include page="../jspf/footer.jsp" />

</body>
</html>