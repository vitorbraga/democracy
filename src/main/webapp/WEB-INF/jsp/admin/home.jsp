<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Democracy - Admin</title>

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

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Admin - Democracy</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/doLogout" />"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/admin"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#question"><i class="fa fa-fw fa-arrows-v"></i> Perguntas <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="question" class="collapse">
                            <li>
                                <a href="#newQuestion" class="routeLink" callback="newQuestion">Nova Pergunta</a>
                            </li>
                            <li>
                                <a href="#searchQuestion" class="routeLink" callback="searchQuestion">Buscar</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#statistics"><i class="fa fa-fw fa-arrows-v"></i> Estatísticas <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="statistics" class="collapse">
                            <li>
                                <a href="#" class="routeLink">Perguntas</a>
                            </li>
                            <li>
                                <a href="#" class="routeLink">Usuários</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#users"><i class="fa fa-fw fa-arrows-v"></i> Usuários <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="users" class="collapse">
                            <li>
                                <a href="#searchUser" class="routeLink">Buscar</a>
                            </li>
                            <li>
                                <a href="#awaitingUsers" class="routeLink" callback="awaitingUsers">Usuários pendentes</a>
                            </li>
                            <li>
                                <a href="#registerAdmin" class="routeLink" callback="registerAdmin">Cadastrar Admin</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">
			<div style="display:none;" id="loader-wrapper">
	    		<div id="loader"></div>
			</div>
            <div class="container-fluid">

				<h2>Dashboard</h2>
                <marquee class="admin-marquee">Neste momento 8 perguntas ativas</marquee>

				<div id="available-questions">
				</div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/underscore-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/backbone-min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/sweet-alert.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/router.js"></script>
	
	<script type="text/javascript">
	
		var callback = '', param = '';
		var basePath = "${pageContext.request.contextPath}" + '/';
		$(document).ready(function() {
		
			var appRouter = new AppRouter(); 
			Backbone.history.start();
			
			$('.routeLink').click(function () {
				callback = $(this).attr('callback');
				param = $(this).attr('callback');
			});
		});
		
		
	
	</script>
</body>

</html>
