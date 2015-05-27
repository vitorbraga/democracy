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
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/sweet-alert.css">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet"
		type="text/css">

	<link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<section class="container 85%" style="padding-top: 30px;">
		<header>
			<h2>Dados do Admin</h2>
			<p>Preencha os campos e clique em Salvar, para alterar algum dado cadastral</p>
		</header>
		<div class="alert alert-danger" style="display:none;" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span><span class="error-text"></span>
		</div>
		<div class="box">
			<form role="form" method="post"  id="userForm"
				action="${pageContext.request.contextPath}/user/editAdmin">
				<div class="row uniform 50%">
					<div class="form-group">
						<label for="name-field">Nome <b>*</b>:</label> <input type="text"
							name="user.name" id="name-field" value="${user.name}" placeholder="Nome" />
					</div>
					<div class="form-group">
						<label for="academicRegister-field">Registro Acadêmico:</label> <input
							type="text" name="user.academicRegister"
							id="academicRegister-field" value="${user.academicRegister}"
							placeholder="Registro Acadêmico" />
					</div>
				</div>
				<div class="row uniform 50%">
					<div class="form-group">
						<label for="user-email">Email <b>*</b>:</label> <input type="email"
							name="user.email" id="email-field" value="${user.email}" placeholder="Email" />
					</div>
					<div class="form-group">
						<label for="email-conf-field">Confirme seu Email <b>*</b>:</label> <input
							type="email" name="user.emailConf" id="email-conf-field"
							value="${user.email}" placeholder="Confirme seu Email" />

					</div>
				</div>
				<div class="row uniform 50%">
					<div class="form-group">
						<label for="gender-field"> Sexo<b>*</b></label>
						<fieldset>
							<input type="radio" name="user.gender" ${user.genderId == 1 ? 'checked' : ''} id="male" value="1" /> <label
								for="male">Masculino</label> <input type="radio"
								name="user.gender" id="female" ${user.genderId == 2 ? 'checked' : ''} value="2" /> <label for="female">Feminino</label>
						</fieldset>
					</div>
				</div>
				
				<p>Digite sua senha e clique em salvar para editar seu perfil.</p>
				<div class="row uniform 50%">
					<div class="form-group">
						<label for="pass-field">Senha <b>*</b>:</label> <input type="password"
							name="user.password" id="pass-field" value=""
							placeholder="Senha" />
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

<script>

function afterAjaxSubmit(data) {
	$('#loader-wrapper').fadeOut(150);
	swal("Sucesso!", 'Admin editado com sucesso!', "success");
}

function errorResult(data) {
	$('#loader-wrapper').fadeOut(150);
	$('.alert-danger .error-text').html(data.responseJSON.message);
	$('.alert-danger').show(200);
}


$("#userForm").submit(function(e)	{
	$('#loader-wrapper').fadeIn(150);
    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");
    $.ajax(
    {
        url : formURL,
        type: "POST",
        data : postData,
        success: afterAjaxSubmit,
        error: errorResult
    });
    e.preventDefault(); //STOP default action
    e.unbind();
    
});


</script>