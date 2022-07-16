<%@page import="main.java.myblog.po.User"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">

	<title>我的博客</title>


	<link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="static/css/style.css" rel="stylesheet" type="text/css">
	<script src="static/js/jquery.min.js" type="text/javascript"></script>
	

</head>

<body>

	<nav class="container col-md-11" style="margin-top:10px">
		<ul class="nav nav-pills">
			<li class="nav-item"><a id="a1" href="user?actionName=main" class="navbar-brand nav-link active"
			>主页</a></li>
			<li class="nav-item"><a id="a2" href="showlist?recordname=food&page=1" class="nav-link" 
			>家乡美食&nbsp;&nbsp;<span class="badge badge-dark">${user.foodNum }</span></a></li>
			<li class="nav-item"><a id="a3" href="showlist?recordname=resorts&page=1" class="nav-link" 
			>旅游景点&nbsp;&nbsp;<span class="badge badge-dark">${user.resortsNum }</span></a></li>
			<li class="nav-item"><a id="a4" href="showlist?recordname=culture&page=1" class="nav-link" 
			>家乡文化&nbsp;&nbsp;<span class="badge badge-dark">${user.cultureNum }</span></a></li>
			<li class="nav-item dropdown ml-md-auto">
				<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
					个人
				</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="user?actionName=usercenter">个人中心</a>
					<a class="dropdown-item" href="user?actionName=fooddatamanage&foodmanagePage=1">数据管理</a>
					<a class="dropdown-item" href="user?actionName=edit">新增博客</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="user?actionName=logout">退出登录</a>
				</div>
			</li>
		</ul>
	</nav>
	
	<div class="container" style="margin-top:30px">
		<div class="row">
			<div class="col-md-4">
				<h2>关于我</h2>
				<h5>我的照片:</h5>
				<img src="static/img/${user.uPicture }" class="rounded" alt="头像" width="294">
				<p class="text-danger font-italic m-3">- ${user.uMood }</p>
				<h4 class="text-dark offset-3">个人信息</h4>
				<dl>
					<dt>学校</dt>
					<dd class="text-primary">- ${user.uSchool }</dd>
					<dt>家乡</dt>
					<dd class="text-primary">- ${user.uHome }</dd>
					<dt>-----------------------------------------------</dt>
					<p class="font-italic">An unexamined life is not worth living, and<br>an examined life is not
						necessarily better.</p>
					<footer class="blockquote-footer">Socrates 苏格拉底</footer>
				</dl>
				<hr class="d-md-none">
			</div>
			
			<c:if test="${empty changePage}">
				<jsp:include page="main.jsp"></jsp:include>
			</c:if>
			<c:if test="${!empty changePage}">
				<jsp:include page="${changePage}"></jsp:include>
			</c:if>
			
			
			
		</div>
	</div>

	<div class="jumbotron text-center" style="margin-bottom:0">
		<p>Copyright © 2022 Yuhang Cheng. All rights reserved.</p>
	</div>


	
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