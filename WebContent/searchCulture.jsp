<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <div class="col-md-7">
				<form id="form_search_culture" class="float-right" action="" method="post">
					<input id="search_input_culture" type="text" placeholder="输入名称来查询">
					<button class="btn btn-pills btn-sm" onclick="search()_culture" type="button"><img src="static/img/search.svg"></button>
				</form>
				<h3>“风俗”</h3>
				<br>
				<h4>搜索结果</h4>
				<table class="table table-hover table-bordered table-responsive" style="text-align:justify;">
					<thead class="thead-light">
						<tr class="">
							<th>#</th>
							<th>风俗名</th>
							<th>介绍</th>
							<th>图片</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${culturesearchbean.currentPage*2-1 }</td>
							<td>${culturesearchbean.tableList[0].CName }</td>
							<td>${culturesearchbean.tableList[0].CInfo }</td>
							<td><img src="static/img/${culturesearchbean.tableList[0].CPic }" style="width: 200px;height: 135px;"></td>
						</tr>
						
						<c:if test="${!empty culturesearchbean.tableList[1].CName}">
						<tr>
							<td>${culturesearchbean.currentPage*2 }</td>
							<td>${culturesearchbean.tableList[1].CName }</td>
							<td>${culturesearchbean.tableList[1].CInfo }</td>
							<td><img src="static/img/${culturesearchbean.tableList[1].CPic }" style="width: 200px;height: 135px;"></td>
						</tr>
							
						</c:if>
					</tbody>
				</table>
				<a href="showlist?recordname=culturesearch&page=${culturesearchbean.currentPage-1 }"
					class="btn btn-pills btn-primary offset-1">上一页</a>

				<a href="showlist?recordname=culturesearch&page=${culturesearchbean.currentPage+1 }"
					class="btn btn-pills btn-primary offset-7">下一页</a>

				<form id="form_goto_culture" class="offset-4" action="" method="post">当前页码：
					<input id="page_input_culture" class="col-md-2" value="${culturesearchbean.currentPage }" type="number">
					<button id="btn_goto_culture" class="btn btn-pills btn-primary btn-sm" onclick="goTo_culture()"
						type="button">前往</button>
				</form>
			</div>
		<script>

				function goTo_culture() {
					var str = $("#page_input_culture").val();
					str = "showlist?recordname=culturesearch&page="+str;
					$("#form_goto_culture").attr("action", str);
					$("#form_goto_culture").submit();
				}
				
				
				$("#a1").removeClass("active");
				$("#a2").removeClass("active");
				$("#a3").removeClass("active");
				$("#a4").addClass("active");
				
				function search_culture() {
					var str=$("#search_input_culture").val();
					str="showlist?recordname=culturesearch&searchname="+str;
					$("#form_search_culture").attr("action", str);
					$("#form_search_culture").submit();
				}
				
		</script>