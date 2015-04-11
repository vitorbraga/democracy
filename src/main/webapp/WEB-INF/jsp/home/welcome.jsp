<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Democracy - Cadastro</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<form class="form-inline" role="form" method="POST" action="<c:url value="authenticate" />">
		<div class="form-group">
			<label for="user-email">Email address:</label> 
			<input type="text"
				name="email" class="form-control" id="user-email" />
		</div>
		<div class="form-group">
			<label for="user-password">Password:</label> 
			<input type="password" name="password" class="form-control" id="user-password" />
		</div>
		<button type="submit" class="btn btn-default">Login</button>
	</form>
	<a href="${pageContext.request.contextPath}/register" class="routeLink">Cadastre-se</a>

</body>

</html>
