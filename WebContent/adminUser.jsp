<%@page import="main.java.myblog.po.UserAdmin"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="col-md-11 offset-1" style="margin-top:20px">
    <table class="table table-hover table-sm bg-dark text-white" style="text-align:justify;opacity: 0.75;">
		<tbody>
		<thead class="thead-dark">
      		<tr>
      			<th>ID</th>
        		<th>用户名</th>
        		<th>密码</th>
        		<th>昵称</th>
        		<th>自我介绍</th>
        		<th>家乡</th>
        		<th>学校</th>
        		<th>头像</th>
        		<th>修改</th>
        		<th>删除</th>
      		</tr>
    	</thead>
<%
	List<UserAdmin> list=(List<UserAdmin>)session.getAttribute("AdminUserList");
	int currentPage=0;
	if(request.getParameter("useradminPage")==null){
		currentPage=1;
	}

	else{currentPage=Integer.parseInt(request.getParameter("useradminPage"));}
	
	int size=list.size();

	int pagesize=4;

	for(int i=0;i<list.size();i++){
		UserAdmin bean=list.get(i);
		out.print("<tr><td>");
		out.print(bean.getUserId());
		out.print("</td><td>");
		out.print(bean.getuName());
		out.print("</td><td>");
		out.print(bean.getuPassword());
		out.print("</td><td>");
		out.print(bean.getuNick());
		out.print("</td><td>");
		out.print(bean.getMood());
		out.print("</td><td>");
		out.print(bean.getuHome());
		out.print("</td><td>");
		out.print(bean.getuSchool());
		out.print("</td><td>");
		out.print("<img src=\"static/img/"+bean.getHead()+"\" width=\"100\">");
		out.print("</td><td>");
		out.print("<a href=\"AdminData?actionName=changeUser&name="
		+bean.getuName()+"\""+"class=\"text-warning\" method=\"post\">");
		out.print("修改</a></td>");
		out.print("<td><a href=\"AdminData?actionName=deleteUser&name="+bean.getuName()+"\""
		+"class=\"text-danger\" onclick=\"return confirmAction();\" method=\"post\">");
		out.print("删除</a></td></tr></br>");
	}
	
	out.print("</tbody></table>");
	out.print("</tbody></table><a href=\"AdminServlet?actionName=User&page="+(currentPage-1)
			+"\" class=\"btn btn-pills btn-primary offset-1\">上一页</a>&nbsp;当前第"+currentPage+"页");
	out.print("</tbody></table><a href=\"AdminServlet?actionName=User&page="+(currentPage+1)
			+"\" class=\"btn btn-pills btn-primary offset-4\">下一页</a>");
	out.print("</div>");
%>

			<script>

				function goTo() {
					var str = $("#page_input").val();
					str = "showlist?recordname=culture&page="+str;
					$("#form_goto").attr("action", str);
					$("#form_goto").submit();
				}
				
				$("#a1").addClass("active");
				
				
				function confirmAction() {
			    	if (confirm("是否删除?")) {
			        	return true;
			    	}

			    return false;
			};
			</script>