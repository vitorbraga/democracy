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

<style>
label {
	color: black;
}
</style>

</head>

<body>

	<div class="container">
		<h2>Cadastrar admin</h2>
		<div class="row">
			<form action="${pageContext.request.contextPath}/user/registerAdmin"
				method="post" role="form">
				<div class="col-lg-6">
					<div class="form-group">
						<label for="name-field">Nome</label>
						<div class="input-group">
							<input type="text" class="form-control" name="user.name"
								id="name-field" placeholder="nome" required> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="email-field">Email</label>
						<div class="input-group">
							<input type="email" class="form-control" id="email-field"
								name="user.email" placeholder="email" required> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="email-conf-field">Confirme o Email</label>
						<div class="input-group">
							<input type="email" class="form-control" id="email-conf-field"
								name="user.emailConf" placeholder="confirme o email" required>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="pass-field">Senha</label>
						<div class="input-group">
							<input type="password" class="form-control" id="pass-field"
								name="user.password" placeholder="senha" required> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="pass-conf-field">Confirme a senha</label>
						<div class="input-group">
							<input type="password" class="form-control" id="pass-conf-field"
								name="user.passwordConf" placeholder="confirme a senha" required>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="gender-field">Sexo:</label>
						<div class="radio">
							<label><input type="radio" value="1" name="user.gender">Masculino</label>
						</div>
						<div class="radio">
							<label><input type="radio" value="2" name="user.gender">Feminino</label>
						</div>

					</div>
					<input type="submit" name="submit" id="submit" value="Submit"
						class="btn btn-info pull-right">
				</div>
			</form>
		</div>
	</div>

</body>

</html>
