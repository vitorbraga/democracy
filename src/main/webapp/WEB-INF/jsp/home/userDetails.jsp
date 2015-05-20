<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Democracy - Dados</title>

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

<body>
<section class="container 75%" >
	<header>
			<h2>Dados do usuário</h2>
			<p>Preencha os campos e clique em Salvar, para alterar algum dado cadastral</p>
	</header>
	<div class="box">
		<form role="form" method="post"
			action="${pageContext.request.contextPath}/user/editUser">
			<div class="row uniform 50%">
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="name-field">Nome:</label> <input type="text"
							name="user.name" id="name-field" value="user.name" placeholder="Nome" />
					</div>
				</div>
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="academicRegister-field">Registro Acadêmico:</label> <input
							type="text" name="user.academicRegister"
							id="academicRegister-field" value="academicRegister"
							placeholder="Registro Acadêmico" />
					</div>
				</div>
			</div>
			<div class="row uniform 50%">
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="user-email">Email:</label> <input type="email"
							name="user.email" id="email-field" value="user.email" placeholder="Email" />
					</div>
				</div>
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="email-conf-field">Confirme seu Email:</label> <input
							type="email" name="user.emailConf" id="email-conf-field"
							value="user.email" placeholder="Confirme seu Email" />

					</div>
				</div>
			</div>
			<div class="row uniform 50%">
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="pass-field">Senha:</label> <input type="password"
							name="user.password" id="pass-field" value=""
							placeholder="Senha" />
					</div>
				</div>
				<div class="6u 12u(mobilep)">
					<div class="form-group">
						<label for="pass-conf-field">Confirme sua Senha:</label> <input
							type="password" name="user.passwordConf" id="pass-conf-field"
							value="" placeholder="Confirme sua Senha" />

					</div>
				</div>
			</div>
			<div class="row uniform 50%">
				<div class="form-group">
					<label for="gender-field"> Sexo</label>
					<fieldset>
						<input type="radio" name="user.gender" id="male" value="1" /> <label
							for="male">Masculino</label> <input type="radio"
							name="user.gender" id="female" value="2" /> <label for="female">Feminino</label>
					</fieldset>
				</div>
			</div>

			<div class="row uniform">
				<div class="12u">
					<ul class="actions align-center">
						<li><input type="submit" value="Salvar" /></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
</section>
</body>

</html>
