<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
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
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	  <div class="container">
	    <div class="row">
	        <form role="form">
	            <div class="col-lg-6">
	                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
	                <div class="form-group">
	                    <label for="InputName">Nome</label>
	                    <div class="input-group">
	                        <input type="text" class="form-control" name="InputName" id="InputName" placeholder="Enter Name" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="InputEmail">Email</label>
	                    <div class="input-group">
	                        <input type="email" class="form-control" id="InputEmailFirst" name="InputEmail" placeholder="Enter Email" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="InputEmail">Confirme o Email</label>
	                    <div class="input-group">
	                        <input type="email" class="form-control" id="InputEmailSecond" name="InputEmail" placeholder="Confirm Email" required>
	                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
	                    </div>
	                </div>	
	                <div class="form-group">
	                	<label for="UserSex">Sexo:</label>
		                <form role="form">
			                <div class="radio">
							  <label><input type="radio" name="optMasc">Masculino</label>
							</div>
							<div class="radio">
							  <label><input type="radio" name="optFem">Feminino</label>
							</div>             
		                </form>
	                
	                </div>
	                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
	            </div>
	        </form>
	        <div class="col-lg-5 col-md-push-1">
	            <div class="col-md-12">
	                <div class="alert alert-success">
	                    <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong>
	                </div>
	                <div class="alert alert-danger">
	                    <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check all page inputs.</strong>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
</body>

</html>
