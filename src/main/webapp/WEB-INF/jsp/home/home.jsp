<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="" />

<title>Democracy - Home</title>
<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.dropotron.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.scrollgress.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/skel-layers.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/init.js"></script>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/sweet-alert.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skel.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-wide.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css" />

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">




<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Header -->
	<header id="header" class="skel-layers-fixed">
		<h1><a href="#"><img
			src="${pageContext.request.contextPath}/resources/images/democracy_min.png" style="padding-top: 10px" /></a></h1>
		<nav id="nav">
					
			<ul>
				
				<li>
					<a href="${pageContext.request.contextPath}/home" >
						<i class="fa fa-fw fa-dashboard"></i> Dashboard</a>						
				</li>
				
				<li>
					<a href="javascript:;" data-toggle="collapse"
						data-target="#question" class="icon fa-angle-down"><i class="fa fa-fw fa-comments"></i>
							Enquetes </a>
					<ul>
						<li><a href="#searchQuestion" class="routeLink">Buscar</a>
						</li>
					</ul>
				
				</li>
				<li>
					<a href="javascript:;" data-toggle="collapse"
							data-target="#statistics" class="icon fa-angle-down"><i class="fa fa-fw fa-pie-chart "></i>Estatísticas</a>
							<ul id="statistics" >
								<li><a href="#" class="routeLink">Perguntas</a></li>
							</ul>
				</li>
				
				<li>
					<a href="" class="icon fa-angle-down"><i class="fa fa-user"></i><sec:authentication
					property="principal.username" /></a>
					<ul>
						<li><a href="#userDetails" class="routeLink"><i class="fa fa-fw fa-user"></i> Dados</a>
						<li><a href="<c:url value="/doLogout"/>"><i
							class="fa fa-fw fa-power-off"></i> Log Out</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
	<section id="main" class="container">
		
		<div id="wrapper" style="padding-left:initial;">
	
			<div id="page-wrapper">
				<div style="display: none;" id="loader-wrapper">
					<div id="loader"></div>
				</div>
				<div class="container-fluid">
	
				</div>
				<!-- /.container-fluid -->
	
			</div>
			<!-- /#page-wrapper -->
	
		</div>
	</section>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/underscore-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/backbone-min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/sweet-alert.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/routerHome.js"></script>

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
