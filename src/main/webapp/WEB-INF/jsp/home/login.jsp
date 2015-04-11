<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	<style>
		#loginBox {
			position: relative;
			margin: 0 auto;
			margin-top: 20px;
			width: 400px;
			min-height: 300px;
			height: auto;
			padding: 20px;
			overflow: hidden;
		}
	</style>
</head>
<body>

	<div id="loginBox">
		<form role="form" action="authenticate" method="POST">
			<h1>Login Usu&aacute;rio</h1>
			<p>Entre com seus dados</p>
			<div class="form-group">
				<label for="user-email">Email:</label> 
				<input type="text" name="email" class="form-control" id="user-email" />
			</div>
			<div class="form-group">
				<label for="user-password">Senha:</label> 
				<input type="password"
					name="password" class="form-control" id="user-password" />
			</div>
			<!-- <div class="form-group">
				<a href="<c:url value="/user/forgotPassword"/>" >Esqueci minha senha</a>
			</div> -->
			<button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>


</body>
</html>