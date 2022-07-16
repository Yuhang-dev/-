package main.java.myblog.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.po.AdminBean;
import main.java.myblog.po.RecordsList;
import main.java.myblog.po.User;
import main.java.myblog.util.SessionUtil;
import main.java.myblog.vo.ResultInfo;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet(
		urlPatterns = { "/user" }, 
		initParams = { 
				@WebInitParam(name = "user", value = "")
		})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService=new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    
    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    public void service(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	//判断用户行为
    	String actionName=request.getParameter("actionName");
    	
    	if ("login".equals(actionName)) {
    		try {
    			userLogin(request, response);
    		} catch (NoSuchAlgorithmException | SQLException e) {
    			e.printStackTrace();
    		}
    	}else if ("logout".equals(actionName)) {
			try {
				userLogout(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("usercenter".equals(actionName)) {
			try {
				
				userCenter(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("changeInfo".equals(actionName)) {
			try {
				
				doChange(request, response);
				
			} catch (NoSuchAlgorithmException | SQLException | IOException e) {
				
				e.printStackTrace();
			}
			//
			//转跳各个数据管理界面
			//
		}else if ("edit".equals(actionName)) {
			
			request.setAttribute("changePage", "edit.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else if ("fooddatamanage".equals(actionName)) {
			int page=Integer.parseInt(request.getParameter("foodmanagePage"));
			foodDataManage(request, response,page);
		}else if ("resortsdatamanage".equals(actionName)) {
			int page=Integer.parseInt(request.getParameter("resortsmanagePage"));
			resortsDataManage(request, response,page);
		}else if ("culturedatamanage".equals(actionName)) {
			int page=Integer.parseInt(request.getParameter("culturemanagePage"));
			cultureDataManage(request, response,page);
		}else{//如果没有参数则返回主页main.jsp
			request.setAttribute("changePage", "");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
    }

    private void cultureDataManage(HttpServletRequest request,HttpServletResponse response,int page) throws ServletException, IOException {
    	request.setAttribute("culturemanagePage", page);
    	request.setAttribute("changePage", "culturemanage.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    
    
    private void resortsDataManage(HttpServletRequest request,HttpServletResponse response,int page) throws ServletException, IOException {
    	request.setAttribute("resortsmanagePage", page);
    	request.setAttribute("changePage", "resortsmanage.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    
    private void foodDataManage(HttpServletRequest request,HttpServletResponse response,int page) throws ServletException, IOException {
    	request.setAttribute("foodmanagePage", page);
    	request.setAttribute("changePage", "foodmanage.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    
    
	private void culture(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("changePage", "cultureList.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    
    
    private void resotrs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("changePage", "resortsList.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}



	public void food(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("changePage", "foodList.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
    
    
    /**
     * 动态包含主页界面
     * 
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    
    public void userCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	SessionUtil.DestoryUserSession(request, response, "");
    	
		request.setAttribute("changePage", "userCenter.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}



	/**
     * 
     * 用户登录
     * 
     * @param request
     * @param response
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    
    public void userLogin(HttpServletRequest request,HttpServletResponse response) 
    		throws NoSuchAlgorithmException, SQLException, ServletException, IOException {
    	
    	//获取参数
    	String uName=request.getParameter("uName");
    	String uPassword=request.getParameter("uPassword");
    	
    	
    	//转跳管理员后台界面
    	if (uName.equalsIgnoreCase("admin")&&uPassword.equals("asdfghjkl123")) {
    		
    		AdminBean admin=AdminServlet.adminIn(request, response);
    		
    		request.getSession().setAttribute("Administrator", admin);
			request.getRequestDispatcher("AdminServlet?actionName=User&page=1").forward(request, response);
			
		}else {
			//传递给userService，判断是否存在记录，并获取返回信息
	    	
	    	ResultInfo<User> resultInfo=userService.userLogin(uName, uPassword);
	    	if (resultInfo.getCode()==1) {
	    		//成功查询，准备转跳主页
	    		
	    		//把request信息存放到session域中
	    		request.getSession().setAttribute("user", resultInfo.getResult());
	    		
	    		//登录时,查询对应食物表、景点表和文化表，将结果对象bean存入session
	    		TableListServlet.getUserRecords(request, response);
	    		
	    		//是否记住密码？
	    		String rem=request.getParameter("rem");
	    		
	    		if ("1".equals(rem)) {
	    			//把账号密码存到cookie中
	    			Cookie cookie=new Cookie("user", uName+"-"+uPassword);
	    			cookie.setMaxAge(7*24*60*60);
	    			response.addCookie(cookie);
	    			
	    		}else {
	    			//清除cookie
	    			Cookie cookie=new Cookie("user", null);
	    			
	    			cookie.setMaxAge(0);
	    			response.addCookie(cookie);
	    		}
	    		//转跳主页
	    		response.sendRedirect("index.jsp");
	    		
	    	}else {
	    		
	    		//查询失败，直接保存信息返回登录界面
	    		request.setAttribute("msg", "用户名或密码错误");
	    		request.getRequestDispatcher("login.jsp").forward(request, response);
	    	}
		}
    	
    	
    }

    
    /**
     * 
     * 用户退出登录:
     * 1.销毁session对象
     * 2.删除cookie对象
     * 3.返回到登陆界面
     * 
     * @param request
     * @param repsonse
     * @throws IOException 
     */
    
    public void userLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
    	//销毁session
    	request.getSession().invalidate();
    	
    	//删除cookie
    	Cookie cookie=new Cookie("user", null);
    	cookie.setMaxAge(0);
    	response.addCookie(cookie);
    	
    	//返回登录界面
    	response.sendRedirect("login.jsp");
	}
    
public void doChange(HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, SQLException, IOException  {
		
		SessionUtil.DestoryUserSession(request, response, "");
	
		//先取得对应的修改值
		String uNick=request.getParameter("nickname_input");
		String uPassword=request.getParameter("password_input");
		String uSchool=request.getParameter("school_input");
		String uHome=request.getParameter("home_input");
		String uMood=request.getParameter("mood_input");
		
		String uName=((User)(request.getSession().getAttribute("user"))).getuName();
				
		//执行更新操作
				
		String sql="UPDATE dbblog.tb_user SET uNick=?,uPassword=?,uSchool=?,uHome=?,mood=? WHERE userId="+((User) (request.getSession().getAttribute("user"))).getUserId();
		List<Object> params=new ArrayList<>();
		params.add(uNick);
		params.add(uPassword);
		params.add(uSchool);
		params.add(uHome);
		params.add(uMood);
				
		//调用BaseDao中的更新方法
					
		BaseDao.executeMyUpdate(sql, params);
		
		//登录，返回新session对象并刷新页面
		
		ResultInfo<User> resultInfo=userService.userLogin(uName, uPassword);
		
		request.getSession().setAttribute("user", resultInfo.getResult());//更新session对象中user对象
		
		response.sendRedirect("index.jsp");
		
		
	}
}
