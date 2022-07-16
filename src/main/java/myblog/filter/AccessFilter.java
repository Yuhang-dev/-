package main.java.myblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.po.AdminBean;
import main.java.myblog.po.User;


/**
 * 
 * 实现非法访问拦截
 * 
 * @author Yuhang
 *
 */

@WebFilter("/*")
public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		
		//得到访问的路径
		String path=request.getRequestURI();
		
		if (path.contains("/login.jsp")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (path.contains("register")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if (path.contains("static")) {
			filterChain.doFilter(request, response);
			return;
		}
		if (path.contains("user")) {
			
			//得到用户行为
			String actionName=request.getParameter("actionName");
			
			if ("login".equals(actionName)) {
				filterChain.doFilter(request, response);
				return;
			}
		}if (path.contains("admin")||path.contains("AdminServlet")) {
			AdminBean admin=(AdminBean) request.getSession().getAttribute("Administrator");
			if (admin!=null) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		User user=(User) request.getSession().getAttribute("user");
		
		if (user!=null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		/**
		 * 
		 * 实现免登录直接进入主页
		 * 利用cookie和session
		 * 
		 */
		
		Cookie[] cookies=request.getCookies();
		
		if (cookies!=null&&cookies.length>0) {
			//遍历数组，拿到信息
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					//拿到存进去的用户名和密码,形如uName-uPassword
					String val=cookie.getValue();
					
					String[] vals=val.split("-");
					
					String uName=vals[0];
					String uPassword=vals[1];
					
					//直接转发登录请求
					
					String url="user?actionName=login&uName="+uName+"&uPassword="+uPassword+"&rem=1";
					
					request.getRequestDispatcher(url).forward(request, response);
					
					return;
					
				}
			}
			
		}
		
		//拦截请求，返回登录界面
		response.sendRedirect("login.jsp");
		
	}
}
