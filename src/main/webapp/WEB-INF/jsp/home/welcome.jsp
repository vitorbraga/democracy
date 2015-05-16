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

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">


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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body class="landing">

		<!-- Header -->
			<header id="header" class="alt">
				<h1><a href="index.html"><img src="${pageContext.request.contextPath}/resources/images/democracy_min.png" style="padding-top:10px"/> </a> </h1>
				<nav id="nav">
					<ul>
						<li><a href="${pageContext.request.contextPath}/login" class="button">Login</a></li>
					</ul>
				</nav>
			</header>

		<!-- Banner -->
			<section id="banner">
				<img src="${pageContext.request.contextPath}/resources/images/democracy_med.png"/>
				<p>Crie, gerencie e comente sobre diversas enquetes </p>
				<ul class="actions">
					<li><a href="${pageContext.request.contextPath}/login" class="button special">Login</a></li>
					<li><a href="#signUp" class="button">Cadastre - se</a></li>
				</ul>
			</section>

		<!-- Main -->
			<section id="main" class="container">

				<section class="box special">
					<header class="major">
						<h2>Introduzindo o sistema para a criação e gerenciamento de enquetes
						seja no seu navegador ou no seu celular</h2>
						<p>Agora você pode criar, gerenciar e comentar sobre diversas enquetes diferentes
							podendo acompanha - las pelo seu celualar ou navegador.</p>
					</header>
					<span class="image featured"><img src="${pageContext.request.contextPath}/resources/images/pic01.jpg" alt="" /></span>
				</section>


			</section>

		<!-- CTA -->
			<section id="signUp" class="container 75%">
				<header>
					<h2>Faça seu Cadastro</h2>
					<p>Preencha os campos e clique em Cadastrar, iremos analisar seu pedido e logo lhe enviaremos um e-mail.</p>
				</header>
				<div class="box">
					<form method="post" action="#">
						<div class="row uniform 50%">
							<div class="6u 12u(mobilep)">
								<input type="text" name="name" id="name" value="" placeholder="Nome" />
							</div>
							<div class="6u 12u(mobilep)">
								<input type="text" name="academicRegister" id="academicRegister" value="" placeholder="Registro Acadêmico" />
							</div>
						</div>
						<div class="row uniform 50%">
							<div class="6u 12u(mobilep)">
								<input type="email" name="email" id="email" value="" placeholder="Email" />
							</div>
							<div class="6u 12u(mobilep)">
								<input type="email" name="email" id="email" value="" placeholder="Confirme seu Email" />
								
							</div>
						</div>
						<div class="row uniform 50%">
							
							<label> Sexo</label>
							<fieldset>
                        				<input type="radio" name="gender" id="male" value="1" />
                        				<label for = "male">Masculino</label>
                        				<input type="radio" name="gender" id="female" value="2" />
                        				<label for = "female">Feminino</label>
        						</fieldset>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions align-center">
									<li><input type="submit" value="Cadastrar" /></li>
								</ul>
							</div>
						</div>
					</form>
				</div>
			</section>
	</body>

</html>
