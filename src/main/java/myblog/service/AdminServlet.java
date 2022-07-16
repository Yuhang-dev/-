package main.java.myblog.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.po.AdminBean;
import main.java.myblog.po.CultureAdminBean;
import main.java.myblog.po.FoodAdminBean;
import main.java.myblog.po.ResortsAdminBean;
import main.java.myblog.po.UserAdmin;
import main.java.myblog.util.SessionUtil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName=(String) request.getParameter("actionName");
		
		if (actionName.equals("User")) {
			int page=Integer.parseInt(request.getParameter("page"));
			adminGetUserList(page,request,response);
			
		}else if (actionName.equals("Food")) {
			int page=Integer.parseInt(request.getParameter("page"));
			adminGetFoodList(page,request,response);
			
			
		}else if (actionName.equals("Resorts")) {
			int page=Integer.parseInt(request.getParameter("page"));
			adminGetResortsList(page,request,response);
			
			
		}else if (actionName.equals("Culture")) {
			int page=Integer.parseInt(request.getParameter("page"));
			adminGetCultureList(page,request,response);
			
			
		}else if (actionName.equals("Manage")) {
			
			request.setAttribute("changePage", "adminManage.jsp");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else if (actionName.equals("Logout")) {
			logout(request,response);
		}
		
	}

	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	//销毁session
    	request.getSession().invalidate();
    	
    	//返回登录界面
    	response.sendRedirect("login.jsp");
	}

	private void adminGetCultureList(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//先清除之前的 Session
		SessionUtil.DestroyAdminSession(request, response,"");
		
		AdminBean adminBean=(AdminBean) request.getSession().getAttribute("Administrator");
	
		//限制页码范围
		if (page<1) {
			page=1;
		}else if (page>(adminBean.getMaxCulture()+3)/4.0) {
			page=(adminBean.getMaxCulture()+3)/4;
		}
		
		int num;
		//判断改页有多少项
		if (adminBean.getMaxCulture()/4>page) {
			num=4;
		}else {
			num=adminBean.getMaxCulture()-4*(page-1);
		}
		
		String sql="SELECT * FROM tb_culture LIMIT "+(page-1)*4+","+num;
		
		//将查询结果封装到session中去
		List<CultureAdminBean> list=BaseDao.rowsQuery(sql, null, CultureAdminBean.class);
		request.getSession().setAttribute("AdminCultureList", list);
		request.setAttribute("changePage", "adminCulture.jsp");
		request.getRequestDispatcher("admin.jsp?cultureadminPage="+page).forward(request, response);
		
	}

	private void adminGetResortsList(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//先清除之前的 Session
		SessionUtil.DestroyAdminSession(request, response,"");
		
		AdminBean adminBean=(AdminBean) request.getSession().getAttribute("Administrator");
	
		//限制页码范围
		if (page<1) {
			page=1;
		}else if (page>(adminBean.getMaxResorts()+3)/4.0) {
			page=(adminBean.getMaxResorts()+3)/4;
		}
		
		int num;
		//判断改页有多少项
		if (adminBean.getMaxCulture()/4>page) {
			num=4;
		}else {
			num=adminBean.getMaxCulture()-4*(page-1);
		}
		
		String sql="SELECT * FROM tb_resorts LIMIT "+(page-1)*4+","+num;
		
		//将查询结果封装到session中去
		List<ResortsAdminBean> list=BaseDao.rowsQuery(sql, null, ResortsAdminBean.class);
		request.getSession().setAttribute("AdminResortsList", list);
		request.setAttribute("changePage", "adminResorts.jsp");
		request.getRequestDispatcher("admin.jsp?resortsadminPage="+page).forward(request, response);
		
	}

	private void adminGetFoodList(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//先清除之前的 Session
		SessionUtil.DestroyAdminSession(request, response,"");
		
		AdminBean adminBean=(AdminBean) request.getSession().getAttribute("Administrator");
	
		//限制页码范围
		if (page<1) {
			page=1;
		}else if (page>(adminBean.getMaxFood()+3)/4.0) {
			page=(adminBean.getMaxFood()+3)/4;
		}
		
		int num;
		//判断改页有多少项
		if (adminBean.getMaxCulture()/4>page) {
			num=4;
		}else {
			num=adminBean.getMaxCulture()-4*(page-1);
		}
		
		String sql="SELECT * FROM tb_food LIMIT "+(page-1)*4+","+num;
		
		//将查询结果封装到session中去
		List<FoodAdminBean> list=BaseDao.rowsQuery(sql, null, FoodAdminBean.class);
		request.getSession().setAttribute("AdminFoodList", list);
		request.setAttribute("changePage", "adminFood.jsp");
		request.getRequestDispatcher("admin.jsp?foodadminPage="+page).forward(request, response);
		
	}

	private void adminGetUserList(int page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//先清除之前的Session
		SessionUtil.DestroyAdminSession(request, response,"");
		
		AdminBean adminBean=(AdminBean) request.getSession().getAttribute("Administrator");
	
		//限制页码范围
		if (page<1) {
			page=1;
		}else if (page>(adminBean.getMaxUser()+3)/4.0) {
			page=(adminBean.getMaxUser()+3)/4;
		}
		
		int num;
		//判断改页有多少项
		if (adminBean.getMaxCulture()/4>page) {
			num=4;
		}else {
			num=adminBean.getMaxCulture()-4*(page-1);
		}
		
		String sql="SELECT * FROM tb_user LIMIT "+(page-1)*4+","+num;
		
		//将查询结果封装到session中去
		List<UserAdmin> list=BaseDao.rowsQuery(sql, null, UserAdmin.class);
		request.getSession().setAttribute("AdminUserList", list);
		request.setAttribute("changePage", "adminUser.jsp");
		request.getRequestDispatcher("admin.jsp?useradminPage="+page).forward(request, response);
		
	}

	/**
	 * 
	 * 更新，保存管理员的信息
	 * 
	 * @param request
	 * @param response
	 * @return AdminBean 对象
	 */
	
	public static AdminBean adminIn(HttpServletRequest request,HttpServletResponse response) {
		
		AdminBean adminBean=new AdminBean();
		
		String sql1="SELECT COUNT(*) FROM dbblog.tb_user";
		String sql2="SELECT COUNT(*) FROM dbblog.tb_food";
		String sql3="SELECT COUNT(*) FROM dbblog.tb_resorts";
		String sql4="SELECT COUNT(*) FROM dbblog.tb_culture";
		
		adminBean.setMaxUser(((Number)BaseDao.singleValueQuery(sql1, null)).intValue());
		adminBean.setMaxFood(((Number)BaseDao.singleValueQuery(sql2, null)).intValue());
		adminBean.setMaxResorts(((Number)BaseDao.singleValueQuery(sql3, null)).intValue());
		adminBean.setMaxCulture(((Number)BaseDao.singleValueQuery(sql4, null)).intValue());
		
		return adminBean;
	}
	
	public static void deleteUser(HttpServletRequest request,HttpServletResponse response) {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}
	
}
