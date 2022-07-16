package main.java.myblog.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionUtil {
	
	public static void DestroyAdminSession(HttpServletRequest request,HttpServletResponse response,String attributeName) {
		
		//获取session对象中所有键值
		Enumeration<String> attr=request.getSession().getAttributeNames();
		
		//遍历所有键值，匹配到所保存的session，其余全部删除
		while (attr.hasMoreElements()) {
			String aName=attr.nextElement().toString();
			//不匹配则删除,除了Administrator 
			if (aName.equals("Administrator")) {
				continue;
			}else if (!aName.equals(attributeName)) {
				request.getSession().removeAttribute(aName);
			}
		}
	}
	
	public static void DestoryUserSession(HttpServletRequest request,HttpServletResponse response,String attributeName) {
		//获取session对象中所有键值
		Enumeration<String> attr=request.getSession().getAttributeNames();
		
		//遍历所有键值，匹配到所保存的session，其余全部删除
		while (attr.hasMoreElements()) {
			String aName=attr.nextElement().toString();
			//不匹配则删除,除了user recordlistbean
			if (aName.equals("user")||aName.equals("recordlistbean")) {
				continue;
			}else if (!aName.equals(attributeName)) {
				request.getSession().removeAttribute(aName);
			}
		}
	}
	
}
