package main.java.myblog.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.po.AdminBean;

/**
 * Servlet implementation class AdminData
 */
@WebServlet("/AdminData")
public class AdminData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName=request.getParameter("actionName");
		
		if (actionName.equals("deleteUser")) {
			String name=request.getParameter("name");
			deleteUser(request,response,name);
		}else if (actionName.equals("deleteFood")) {
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			deleteFood(request,response,name,id);
		}if (actionName.equals("deleteResorts")) {
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			deleteResorts(request,response,name,id);
		}else if (actionName.equals("deleteCulture")) {
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			deleteCulture(request,response,name,id);
		}
	}

	private void deleteCulture(HttpServletRequest request, HttpServletResponse response, String name, String id) throws ServletException, IOException {
		
		String sql="DELETE FROM tb_culture WHERE cName='"+name+"'"+" AND userId="+id;
		
		BaseDao.executeMyUpdate(sql, null);
		
		AdminBean adminBean=((AdminBean)request.getSession().getAttribute("Administrator"));
		
		((AdminBean)request.getSession().getAttribute("Administrator")).setMaxCulture(adminBean.getMaxCulture()-1);
		
		request.getRequestDispatcher("AdminServlet?actionName=Culture&page=1").forward(request, response);
		
	}

	private void deleteResorts(HttpServletRequest request, HttpServletResponse response, String name, String id) throws ServletException, IOException {
		
		String sql="DELETE FROM tb_resorts WHERE rName='"+name+"'"+" AND userId="+id;
		
		BaseDao.executeMyUpdate(sql, null);
		
		AdminBean adminBean=((AdminBean)request.getSession().getAttribute("Administrator"));
		
		((AdminBean)request.getSession().getAttribute("Administrator")).setMaxResorts(adminBean.getMaxResorts()-1);
		
		request.getRequestDispatcher("AdminServlet?actionName=Resorts&page=1").forward(request, response);
		
	}

	private void deleteFood(HttpServletRequest request, HttpServletResponse response, String name, String id) throws ServletException, IOException {
		
		String sql="DELETE FROM tb_food WHERE fName='"+name+"'"+" AND userId="+id;
		
		BaseDao.executeMyUpdate(sql, null);
		
		AdminBean adminBean=((AdminBean)request.getSession().getAttribute("Administrator"));
		
		((AdminBean)request.getSession().getAttribute("Administrator")).setMaxFood(adminBean.getMaxFood()-1);
		
		request.getRequestDispatcher("AdminServlet?actionName=Food&page=1").forward(request, response);
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {

		String sql="DELETE FROM tb_user WHERE uName='"+name+"'";
		
		BaseDao.executeMyUpdate(sql, null);
		
		AdminBean adminBean=((AdminBean)request.getSession().getAttribute("Administrator"));
		
		((AdminBean)request.getSession().getAttribute("Administrator")).setMaxUser(adminBean.getMaxUser()-1);
		
		request.getRequestDispatcher("AdminServlet?actionName=User&page=1").forward(request, response);
		
	}

}
