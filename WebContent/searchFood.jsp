<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <div class="col-md-7">
				<form id="form_search_food" class="float-right" action="" method="post">
					<input id="search_input_food" type="text" placeholder="输入名称来查询">
					<button class="btn btn-pills btn-sm" onclick="search_food()" type="button"><img src="static/img/search.svg"></button>
				</form>
				<h3>“美食”</h3>
				<br>
				<h4>搜索结果</h4>
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
							<td>${foodsearchbean.currentPage*2-1 }</td>
							<td>${foodsearchbean.tableList[0].FName }</td>
							<td>${foodsearchbean.tableList[0].FInfo }</td>
							<td><img src="static/img/${foodsearchbean.tableList[0].FPic }" style="width: 200px;height: 135px;"></td>
						</tr>
						
						<c:if test="${!empty foodsearchbean.tableList[1].FName}">
						<tr>
							<td>${foodsearchbean.currentPage*2 }</td>
							<td>${foodsearchbean.tableList[1].FName }</td>
							<td>${foodsearchbean.tableList[1].FInfo }</td>
							<td><img src="static/img/${foodsearchbean.tableList[1].FPic }" style="width: 200px;height: 135px;"></td>
						</tr>
							
						</c:if>
					</tbody>
				</table>
				<a href="showlist?recordname=foodsearch&page=${foodsearchbean.currentPage-1 }"
					class="btn btn-pills btn-primary offset-1">上一页</a>

				<a href="showlist?recordname=foodsearch&page=${foodsearchbean.currentPage+1 }"
					class="btn btn-pills btn-primary offset-7">下一页</a>

				<form id="form_goto_food" class="offset-4" action="" method="post">当前页码：
					<input id="page_input_food" class="col-md-2" value="${foodsearchbean.currentPage }" type="number">
					<button id="btn_goto_food" class="btn btn-pills btn-primary btn-sm" onclick="goTo_food()"
						type="button">前往</button>
				</form>
			</div>
		<script>

				function goTo_food() {
					var str = $("#page_input_food").val();
					str = "showlist?recordname=foodsearch&page="+str;
					$("#form_goto_food").attr("action", str);
					$("#form_goto_food").submit();
				}
				
				$("#a1").removeClass("active");
				$("#a2").addClass("active");
				$("#a3").removeClass("active");
				$("#a4").removeClass("active");
				
				function search_food() {
					var str=$("#search_input_food").val();
					str="showlist?recordname=foodsearch&searchname="+str;
					$("#form_search_food").attr("action", str);
					$("#form_search_food").submit();
				}
				
		</script>