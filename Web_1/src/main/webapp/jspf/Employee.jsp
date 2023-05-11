<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Employee"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Работники</title>
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
	<h3>Список сотрудников</h3>
	<table>
		<thead>
		<tr>
		<th scope="col">ID Работника</th>
		<th scope="col">Фамилия</th>
		<th scope="col">Имя</th>
		<th scope="col">Отчество</th>
		<th scope="col">Должность</th>
		<th scope="col">Телефон</th>
		<th scope="col">Адресс</th>
		<th scope="col">Email</th>
		<th scope="col">Дата рождения</th>
		<th scope="col">Редактировать</th> 
		<th scope="col">Удалить</th>
		
		</tr>
		</thead>
		<tbody>
		<c:forEach var="emp" items="${employees}">
			<tr>
				<td>${emp.getId()}</td>
				<td>${emp.getSecondName()}</td>
				<td>${emp.getFirstName()}</td>
				<td>${emp.getLastName()}</td>
				<td>${emp.getTitle()}</td>
				<td>${emp.getPhone()}</td>
				<td>${emp.getAddres()}</td>
				<td>${emp.getEmail()}</td>
				<td>${emp.getBirchDay()}</td>
				<td width="20">
					<a href='<c:url value="/editemployee?id=${emp.getId()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Редактировать" src="images/icon-edit.png"></a>
				</td> 
				<td width="20">
					<a href='<c:url value="/deleteemployee?id=${emp.getId()}" />'role="button" class="btn btn-outline-primary"><img width = 20 alt="Удалить" src="images/icon-delete.png"></a>
				</td> 
			</tr>
			
		</c:forEach>
		</tbody>
		</table>
	</aside>
	</section>
	<section>
	<article>
	<h3>Создание нового сотрудника</h3>
	<div class="text-article">
		<form method="POST" action="">
			<div class="mb-3 row">
			<label for="id" class="col-sm-3 col-form-label">Код сотрудника:</label> 
			<div class="col-sm-7">
				<input type="text" name="id" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="secondname" class="col-sm-3 col-form-label">Фамилия:</label> 
			<div class="col-sm-7">
				<input type="text" name="secondname" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="firstname" class="col-sm-3 col-form-label">Имя:</label> 
			<div class="col-sm-7">
				<input type="text" name="firstname" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="lastname" class="col-sm-3 col-form-label">Отчество:</label> 
			<div class="col-sm-7">
				<input type="text" name="lastname" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="title" class="col-sm-3 col-form-label">Должность:</label> 
			<div class="col-sm-7">
				<input type="text" name="title" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="phone" class="col-sm-3 col-form-label">Телефон:</label> 
			<div class="col-sm-7">
				<input type="text" name="phone" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="addres" class="col-sm-3 col-form-label">Адресс:</label> 
			<div class="col-sm-7">
				<input type="text" name="addres" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="email" class="col-sm-3 col-form-label">Почта:</label> 
			<div class="col-sm-7">
				<input type="text" name="email" class="form-control"/>
			</div>
			</div>
			<div class="mb-3 row">
			<label for="birchdate" class="col-sm-3 col-form-label">День рождения:</label> 
			<div class="col-sm-7">
				<input type="date" name="birchdate" value ="1990-12-12" class="form-control"/>
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