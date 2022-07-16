<%@page import="main.java.myblog.po.User"%>
<%@page import="main.java.myblog.po.FoodBean"%>
<%@page import="main.java.myblog.po.RecordsList"%>
<%@page import="main.java.myblog.vo.ResultInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <div class="col-md-7">
				<br>
				<form id="form_search" class="float-right" action="" method="post">
					<input id="search_input" type="text" placeholder="输入名称来查询">
					<button class="btn btn-pills btn-sm" onclick="search()" type="button"><img src="static/img/search.svg"></button>
				</form>
				<h3>家乡美食</h3>
				<br>
				<table class="table table-hover table-bordered table-responsive" style="text-align:justify;">
					<thead class="thead-light">
						<tr class="">
							<th>#</th>
							<th>美食名</th>
							<th>介绍</th>
							<th>图片</th>
						</tr>
					</thead>
					<tbody>
						
						
						<tr>
							<td>${foodpagebean.currentPage*2-1 }</td>
							<td>${foodpagebean.tableList[0].FName }</td>
							<td>${foodpagebean.tableList[0].FInfo }</td>
							<td><img src="static/img/${foodpagebean.tableList[0].FPic }" style="width: 200px;height: 135px;"></td>
						</tr>
						
						<c:if test="${!empty foodpagebean.tableList[1].FName}">
						<tr>
							<td>${foodpagebean.currentPage*2 }</td>
							<td>${foodpagebean.tableList[1].FName }</td>
							<td>${foodpagebean.tableList[1].FInfo }</td>
							<td><img src="static/img/${foodpagebean.tableList[1].FPic }" style="width: 200px;height: 135px;"></td>
						</tr>
							
						</c:if>
					</tbody>
				</table>
				<a href="showlist?recordname=food&page=${foodpagebean.currentPage-1 }"
					class="btn btn-pills btn-primary offset-1">上一页</a>

				<a href="showlist?recordname=food&page=${foodpagebean.currentPage+1 }"
					class="btn btn-pills btn-primary offset-7">下一页</a>

				<form id="form_goto" class="offset-4" action="" method="post">当前页码：
					<input id="page_input" class="col-md-2" value="${foodpagebean.currentPage }" type="number">
					<button id="btn_goto" class="btn btn-pills btn-primary btn-sm" onclick="goTo()"
						type="button">前往</button>
				</form>
			</div>

			<script>

				function goTo() {
					var str = $("#page_input").val();
					str = "showlist?recordname=food&page="+str;
					$("#form_goto").attr("action", str);
					$("#form_goto").submit();
				}
				
				$("#a1").removeClass("active");
				$("#a2").addClass("active");
				
				function search() {
					var str=$("#search_input").val();
					str="showlist?recordname=foodsearch&searchname="+str;
					$("#form_search").attr("action", str);
					$("#form_search").submit();
				}
				
			</script>