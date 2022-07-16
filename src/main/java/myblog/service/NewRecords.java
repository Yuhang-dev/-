package main.java.myblog.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.dao.UserDao;
import main.java.myblog.po.User;
import main.java.myblog.util.SessionUtil;

/**
 * Servlet implementation class NewRecords
 */
@WebServlet("/new")
public class NewRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRecords() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionUtil.DestoryUserSession(request, response, "");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		String type=null;
		String name=null;
		String info=null;
		String pic=null;
		
		try {
			List<FileItem> fileItems=upload.parseRequest(request);
			for(int i=0;i<fileItems.size();i++) {
				FileItem tmp=fileItems.get(i);
				if (tmp.isFormField()) {
					
					//获取每个表单名字
					String filedName=tmp.getFieldName();
					
					if ("type".equals(filedName)) {
						type=tmp.getString("UTF-8");
					}else if ("name_input".equals(filedName)) {
						name=tmp.getString("UTF-8");
					}else if ("info_input".equals(filedName)) {
						info=tmp.getString("UTF-8");
					}
				}else {//不是普通表单,即文件
					String path="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img";
					//获取上传的文件
					
					File uploadFile=new File(tmp.getName());
					
					String fileName=uploadFile.getName();
					
					File destFile=new File(path, fileName);
					
					tmp.write(destFile);//将目标文件写入目标文件夹
					
					pic=fileName;
					
					System.out.println(pic);
				}
				
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if ("food".equals(type)) {
			newFoodRecord(request,response,name,info,pic);
		}else if ("resorts".equals(type)) {
			newResortsRecord(request,response,name,info,pic);
		}else if ("culture".equals(type)) {
			newCultureRecord(request,response,name,info,pic);
		}
		
		//返回主页
		request.setAttribute("changePage", "");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	private void newCultureRecord(HttpServletRequest request,HttpServletResponse response,String name,String info,String pic) {
		
		String sql="INSERT INTO tb_culture (userId,cName,cInfo,cPic) VALUES("+((User)request.getSession().getAttribute("user")).getUserId()+",\""+name+"\",\""+info+"\",\""+pic+"\")";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session中用户bean的记录，和用户纪录列表session的内容
		
		UserDao userDao=new UserDao();
		

			try {
				request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
			
		TableListServlet.getUserRecords(request, response);
		
	}

	private void newResortsRecord(HttpServletRequest request,HttpServletResponse response,String name,String info,String pic) {
		
		String sql="INSERT INTO tb_resorts (userId,rName,rInfo,rPic) VALUES("+((User)request.getSession().getAttribute("user")).getUserId()+",\""+name+"\",\""+info+"\",\""+pic+"\")";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session中用户bean的记录，和用户纪录列表session的内容
		
		UserDao userDao=new UserDao();
		

			try {
				request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
			
		TableListServlet.getUserRecords(request, response);
		
	}

	private void newFoodRecord(HttpServletRequest request,HttpServletResponse response,String name,String info,String pic) {
		
		String sql="INSERT INTO tb_food (userId,fName,fInfo,fPic) VALUES("+((User)request.getSession().getAttribute("user")).getUserId()+",\""+name+"\",\""+info+"\",\""+pic+"\")";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新session中用户bean的记录，和用户纪录列表session的内容
		
		UserDao userDao=new UserDao();
		

			try {
				request.getSession().setAttribute("user", userDao.queryByName(((User)request.getSession().getAttribute("user")).getuName()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		//更新session对象中的纪录列表，调用TableListServlet中的getUserRecords()方法
			
		TableListServlet.getUserRecords(request, response);
		
	}

}
