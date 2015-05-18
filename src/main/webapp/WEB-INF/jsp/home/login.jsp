<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<!-- HTML5UP References -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.dropotron.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.scrollgress.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/skel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/skel-layers.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/init.js"></script>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<!-- Custom CSS -->
	<link
		href="${pageContext.request.contextPath}/resources/css/sb-admin.css"
		rel="stylesheet">
	
	<!-- HTML5UP CSS -->
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/skel.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style-wide.css" />
	
	
	<!-- Custom Fonts -->
	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
		type="text/css">

	
</head>
<body>

	<!-- Header -->
	<header id="header" class="reveal">
		<h1>
			<a href="${pageContext.request.contextPath}/"><img
				src="${pageContext.request.contextPath}/resources/images/democracy_min.png"
				style="padding-top: 10px" /> </a>
		</h1>
		
		
	</header>
	
	<section id="loginBox" class="container 50%">
	
		<header>
			<h2 style="padding-top: 20px">Login Usu&aacute;rio</h2>
			<p>Entre com seus dados</p>
		</header>
		<div class="box" >
			<form role="form" action="authenticate" method="POST">
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
	</section>

</body>
</html>