<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <div class="col-md-7">
				<form id="form_search_resorts" class="float-right" action="" method="post">
					<input id="search_input_resorts" type="text" placeholder="输入名称来查询">
					<button class="btn btn-pills btn-sm" onclick="search_resorts()" type="button"><img src="static/img/search.svg"></button>
				</form>
				<h3>“景点”</h3>
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
							<td>${resortssearchbean.currentPage*2-1 }</td>
							<td>${resortssearchbean.tableList[0].RName }</td>
							<td>${resortssearchbean.tableList[0].RInfo }</td>
							<td><img src="static/img/${resortssearchbean.tableList[0].RPic }" style="width: 200px;height: 135px;"></td>
						</tr>
						
						<c:if test="${!empty resortssearchbean.tableList[1].RName}">
						<tr>
							<td>${resortssearchbean.currentPage*2 }</td>
							<td>${resortssearchbean.tableList[1].RName }</td>
							<td>${resortssearchbean.tableList[1].RInfo }</td>
							<td><img src="static/img/${resortssearchbean.tableList[1].RPic }" style="width: 200px;height: 135px;"></td>
						</tr>
							
						</c:if>
					</tbody>
				</table>
				<a href="showlist?recordname=resortssearch&page=${resortssearchbean.currentPage-1 }"
					class="btn btn-pills btn-primary offset-1">上一页</a>

				<a href="showlist?recordname=resortssearch&page=${resortssearchbean.currentPage+1 }"
					class="btn btn-pills btn-primary offset-7">下一页</a>

				<form id="form_goto_resorts" class="offset-4" action="" method="post">当前页码：
					<input id="page_input_resorts" class="col-md-2" value="${resortssearchbean.currentPage }" type="number">
					<button id="btn_goto_resorts" class="btn btn-pills btn-primary btn-sm" onclick="goTo_resorts()"
						type="button">前往</button>
				</form>
			</div>
		<script>

				function goTo_resorts() {
					var str = $("#page_input_resorts").val();
					str = "showlist?recordname=resortssearch&page="+str;
					$("#form_goto_resorts").attr("action", str);
					$("#form_goto_resorts").submit();
				}
				
				$("#a1").removeClass("active");
				$("#a2").removeClass("active");
				$("#a3").addClass("active");
				$("#a4").removeClass("active");
				
				function search_resorts() {
					var str=$("#search_input_resorts").val();
					str="showlist?recordname=resortssearch&searchname="+str;
					$("#form_search_resorts").attr("action", str);
					$("#form_search_resorts").submit();
				}
				
		</script>