<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
<title>后台数据管理</title>
	<link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/style.css" rel="stylesheet" type="text/css">
	<script src="static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body style="background-image: url('static/img/bgimg.jpg'); background-repeat: no-repeat; background-attachment: fixed; background-size: cover; background-position: center;">
		<nav class="container col-md-11" style="margin-top:10px">
		<ul class="nav nav-pills text-light">
			<li class="nav-item"><a id="a1" href="AdminServlet?actionName=User&page=1" class="navbar-brand nav-link active"
			>用户管理<span class="badge badge-dark">${Administrator.maxUser }</span></a></li>
			<li class="nav-item"><a id="a2" href="AdminServlet?actionName=Food&page=1" class="navbar-brand nav-link" 
			>食物记录<span class="badge badge-dark">${Administrator.maxFood }</span></a></li>
			<li class="nav-item"><a id="a3" href="AdminServlet?actionName=Resorts&page=1" class="navbar-brand nav-link" 
			>景点记录<span class="badge badge-dark">${Administrator.maxResorts }</span></a></li>
			<li class="nav-item"><a id="a4" href="AdminServlet?actionName=Culture&page=1" class="navbar-brand nav-link" 
			>风俗记录<span class="badge badge-dark">${Administrator.maxCulture }</span></a></li>
			<li class="nav-item dropdown ml-md-auto">
				<a class="navbar-brand nav-link dropdown-toggle" href="#" data-toggle="dropdown">
					管理员
				</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="AdminServlet?actionName=Manage">个人中心</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="AdminServlet?actionName=Logout">退出登录</a>
				</div>
			</li>
		</ul>
	</nav>
	
				<c:if test="${empty changePage}">
				<jsp:include page="adminUser.jsp"></jsp:include>
			</c:if>
			<c:if test="${!empty changePage}">
				<jsp:include page="${changePage}"></jsp:include>
			</c:if>
	
	<script>
	
	//切换效果
	$("#a1").click(function () {
		$("#a1").addClass("active");
		$("#a2").removeClass("active");
		$("#a3").removeClass("active");
		$("#a4").removeClass("active");
	});

	$("#a2").click(function () {
		$("#a2").addClass("active");
		$("#a1").removeClass("active");
		$("#a3").removeClass("active");
		$("#a4").removeClass("active");
	});

	$("#a3").click(function () {
		$("#a3").addClass("active");
		$("#a1").removeClass("active");
		$("#a2").removeClass("active");
		$("#a4").removeClass("active");
	});

	$("#a4").click(function () {
		$("#a4").addClass("active");
		$("#a2").removeClass("active");
		$("#a3").removeClass("active");
		$("#a1").removeClass("active");
	});

	</script>
	
	<script src="static/js/popper.min.js" type="text/javascript"></script>
	<script src="static/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="static/js/scripts.js" type="text/javascript"></script>
	
</body>
</html>