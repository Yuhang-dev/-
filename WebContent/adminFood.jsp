<%@page import="main.java.myblog.po.FoodAdminBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="col-md-11 offset-1" style="margin-top:20px">
    <table class="table table-sm bg-dark text-white" style="text-align:justify;opacity: 0.75;">
		<tbody>
		<thead class="thead-dark">
      		<tr>
      			<th>归属ID</th>
        		<th>名称</th>
        		<th>介绍</th>
        		<th>图片</th>
        		<th>修改</th>
        		<th>删除</th>
      		</tr>
    	</thead>
<%
	List<FoodAdminBean> list=(List<FoodAdminBean>)session.getAttribute("AdminFoodList");
	int currentPage=0;
	if(request.getParameter("foodadminPage")==null){
		currentPage=1;
	}

	else{currentPage=Integer.parseInt(request.getParameter("foodadminPage"));}
	
	int size=list.size();

	int pagesize=4;
	

	for(int i=0;i<list.size();i++){
		FoodAdminBean bean=list.get(i);
		out.print("<tr><td>");
		out.print(bean.getUserId());
		out.print("</td><td>");
		out.print(bean.getFName());
		out.print("</td><td>");
		out.print(bean.getFInfo());
		out.print("</td><td>");
		out.print(bean.getFPic());
		out.print("</td><td>");
		out.print("<a href=\"AdminData?actionName=changeFood&name="
		+bean.getFName()+"\""+"class=\"text-warning\" method=\"post\">");
		out.print("修改</a></td>");
		out.print("<td><a href=\"AdminData?actionName=deleteFood&name="+bean.getFName()+"&id="+bean.getUserId()+"\""
		+"class=\"text-danger\" onclick=\"return confirmAction();\" method=\"post\">");
		out.print("删除</a></td></tr>");
	}
	
	out.print("</tbody></table>");
	out.print("</tbody></table><a href=\"AdminServlet?actionName=Food&page="+(currentPage-1)
			+"\" class=\"btn btn-pills btn-primary offset-1\">上一页</a>&nbsp;当前第"+currentPage+"页");
	out.print("</tbody></table><a href=\"AdminServlet?actionName=Food&page="+(currentPage+1)
			+"\" class=\"btn btn-pills btn-primary offset-1\">下一页</a>");
	out.print("</div>");
%>

			<script>

				function goTo() {
					var str = $("#page_input").val();
					str = "showlist?recordname=culture&page="+str;
					$("#form_goto").attr("action", str);
					$("#form_goto").submit();
				}
				
				$("#a1").removeClass("active");
				$("#a2").addClass("active");
				
				function search() {
					var str=$("#search_input").val();
					str="showlist?recordname=culturesearch&searchname="+str;
					$("#form_search").attr("action", str);
					$("#form_search").submit();
				}
				
				function confirmAction() {
			    	if (confirm("是否删除?")) {
			        	return true;
			    	}

			    return false;
			};
			</script>