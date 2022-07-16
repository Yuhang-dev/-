package main.java.myblog.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.dao.UserDao;
import main.java.myblog.po.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uName=request.getParameter("uName");
		String uPassword=request.getParameter("uPassword");
		
		UserDao userDao=new UserDao();
		
		try {
			
			User user=userDao.queryByName(uName);
			
			if (user!=null) {
				
				request.setAttribute("message", "用户已存在");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				
			}else {
				
				String sql="INSERT INTO tb_user (uName,uPassword) VALUE('"+uName+"','"+uPassword+"')";
				
				BaseDao.executeMyUpdate(sql, null);
				
				request.setAttribute("Registed", uName);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

}
