package main.java.myblog.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.dao.UserDao;
import main.java.myblog.po.RecordsList;
import main.java.myblog.po.User;

/**
 * Servlet implementation class DataManage
 */
@WebServlet("/data")
public class DataManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName=request.getParameter("actionName");
		
		if ("deleteFood".equals(actionName)) {
			//删除记录
			deleteFood(request,response);
			//返回页面
			request.setAttribute("changePage", "foodmanage.jsp");
			//向user servlet发生请求让它去处理转跳返回操作
			request.getRequestDispatcher("user?actionName=fooddatamanage&foodmanagePage=1").forward(request, response);
		}else if ("deleteResorts".equals(actionName)) {
			//删除记录
			deleteResorts(request,response);
			//返回页面
			request.setAttribute("changePage", "resortsmanage.jsp");
			//向user servlet发生请求让它去处理转跳返回操作
			request.getRequestDispatcher("user?actionName=resortsdatamanage&resortsmanagePage=1").forward(request, response);
		}else if ("deleteCulture".equals(actionName)) {
			//删除记录
			deleteCulture(request,response);
			//返回页面
			request.setAttribute("changePage", "culturemanage.jsp");
			//向user servlet发生请求让它去处理转跳返回操作
			request.getRequestDispatcher("user?actionName=culturedatamanage&culturemanagePage=1").forward(request, response);
		}else if ("changeFood".equals(actionName)) {
			String name=request.getParameter("name");
			
			request.setAttribute("name", name);
			request.setAttribute("changePage", "foodchange.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("changeResorts".equals(actionName)) {
			String name=request.getParameter("name");
			
			request.setAttribute("name", name);
			request.setAttribute("changePage", "resortschange.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("changeCulture".equals(actionName)) {
			String name=request.getParameter("name");
			
			request.setAttribute("name", name);
			request.setAttribute("changePage", "culturechange.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("change".equals(actionName)) {
			String type=request.getParameter("type");
			
			if ("food".equals(type)) {
				changeFood(request,response);
				//返回主页
				request.setAttribute("changePage", "");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if ("resorts".equals(type)) {
				changeResorts(request,response);
				//返回主页
				request.setAttribute("changePage", "");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else if ("culture".equals(type)) {
				changeCulture(request,response);
				//返回主页
				request.setAttribute("changePage", "");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
		
	}
	
	private void changeCulture(HttpServletRequest request,HttpServletResponse response) {
		
		String cName=request.getParameter("culturename_input");
		
		String sql="UPDATE tb_culture SET cInfo=\""+request.getParameter("foodinfo_input")+"\" WHERE userId ="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND cName=\""+cName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
		
		TableListServlet.getUserRecords(request, response);
	}
	
	private void changeResorts(HttpServletRequest request,HttpServletResponse response) {
		
		String rName=request.getParameter("resortsname_input");
		
		String sql="UPDATE tb_resorts SET rInfo=\""+request.getParameter("foodinfo_input")+"\" WHERE userId ="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND rName=\""+rName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
		
		TableListServlet.getUserRecords(request, response);
	}

	private void changeFood(HttpServletRequest request,HttpServletResponse response) {
		
		String fName=request.getParameter("foodname_input");
		
		String sql="UPDATE tb_food SET fInfo=\""+request.getParameter("foodinfo_input")+"\" WHERE userId ="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND fName=\""+fName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
		
		TableListServlet.getUserRecords(request, response);
	}

	private void deleteCulture(HttpServletRequest request, HttpServletResponse response) {
		//获取删除记录的检索(名称)
		
		String cName=request.getParameter("name");
			
		String sql="DELETE FROM tb_culture WHERE cName='"+cName+"'";
			
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session对象中user中保存的各条记录条数
		
		UserDao userDao=new UserDao();
		
		try {
			request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		//删除完毕需要更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
			
		TableListServlet.getUserRecords(request, response);
	}

	private void deleteResorts(HttpServletRequest request, HttpServletResponse response) {
			//获取删除记录的检索(名称)
		
			String rName=request.getParameter("name");
				
			String sql="DELETE FROM tb_resorts WHERE rName='"+rName+"'";
				
			BaseDao.executeMyUpdate(sql, null);
				
			
			//更新session对象中user中保存的各条记录条数
			
			UserDao userDao=new UserDao();
			
			try {
				request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			//删除完毕需要更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
				
			TableListServlet.getUserRecords(request, response);
	}

	private void deleteFood(HttpServletRequest request,HttpServletResponse response) {
		//获取删除记录的检索(名称)
		
		String fName=request.getParameter("name");
		
		
		String sql="DELETE FROM tb_food WHERE fName='"+fName+"'";
		
		BaseDao.executeMyUpdate(sql, null);
		

		//更新session对象中user中保存的各条记录条数
		
			UserDao userDao=new UserDao();
				
			try {
				request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
			} catch (SQLException e) {
					
				e.printStackTrace();
			}
		
		//删除完毕需要更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
		
		TableListServlet.getUserRecords(request, response);
		
	}
	
	
}
