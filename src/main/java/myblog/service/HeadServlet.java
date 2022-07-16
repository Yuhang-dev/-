package main.java.myblog.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.po.User;

/**
 * Servlet implementation class UploadPic
 */
@WebServlet("/head")
public class HeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String actionName=request.getParameter("actionName");
	
		if ("uploadHead".equals(actionName)) {
			uploadHead(request, response);
			
			//回到界面
			request.setAttribute("changePage", "userCenter.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if ("uploadFoodPic".equals(actionName)) {
			uploadFoodPic(request,response);
			//回到界面
			request.setAttribute("changePage", "foodmanage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("uploadResortsPic".equals(actionName)) {
			uploadResortsPic(request,response);
			//回到界面
			request.setAttribute("changePage", "resortsmanage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("uploadCulturePic".equals(actionName)) {
			uploadCulturePic(request,response);
			//回到界面
			request.setAttribute("changePage", "culturemanage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

	private void uploadCulturePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tempFileName=request.getSession().getId();
		
		String fileName=null;
		
		String dir="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img";
		
		File tmpFile=new File(dir, tempFileName);
		
		RandomAccessFile randomWrite=new RandomAccessFile(tmpFile, "rw");
		
		//上传
		
		InputStream in=request.getInputStream();
		byte b[] =new byte[10000];
		int n;
		while ((n=in.read(b))!=-1) {
			randomWrite.write(b, 0, n);
		}
		randomWrite.close();
		in.close();
		
		RandomAccessFile randomRead=new RandomAccessFile(tmpFile, "r");
		
		int second=1;
		String secondLine=null;
		while(second<=2) {
			secondLine=randomRead.readLine();
			second++;
		}
		//找到文件名前的等号位置
		int position=secondLine.lastIndexOf("=");
		//获得文件名
		fileName=secondLine.substring(position+2, secondLine.length()-1);
		//定位到临时文件的开头
		randomRead.seek(0);
		//获取第四行回车位置
		long forthEndPosition=0;
		int forth=1;
		
		while((n=randomRead.readByte())!=-1&&(forth<=4)) {
			if(n=='\n') {
				forthEndPosition=randomRead.getFilePointer();
				forth++;
			}
		}
		
		//根据上传的名字，将该文件存入磁盘
		
		byte cc[]=fileName.getBytes("ISO-8859-1");
		fileName=new String(cc, "UTF-8");
		
		File fileUser=new File(dir,fileName);
		
		randomWrite=new RandomAccessFile(fileUser, "rw");
		
		//确定出临时文件中 内容的最后位置，即倒数第二行
		randomRead.seek(randomRead.length());
		
		long endPosition=randomRead.getFilePointer();
		
		long mark=endPosition;
		
		int j=1;
		
		while((mark>=0)&&(j<=2)) {
			mark--;
			randomRead.seek(mark);
			n=randomRead.readByte();
			if(n=='\n') {
				endPosition=randomRead.getFilePointer();
				j++;
			}
		}
		
		//将randomRead指向文件fileTemp的第四行结束的位置
		
		randomRead.seek(forthEndPosition);
		
		long startPoint=randomRead.getFilePointer();
		
		//从临时文件读出客户上传的文件存入fileUser
		//读取第4行结束位置和倒数第2行之间的内容
		while(startPoint<endPosition-1) {
			n=randomRead.readByte();
			randomWrite.write(n);
			startPoint=randomRead.getFilePointer();
		}
		randomWrite.close();
		randomRead.close();
		tmpFile.delete();
		
		//跟新用户信息内保存的图片路径
		String cName=request.getParameter("cName");
		
		String sql="UPDATE tb_culture SET cPic=\""+fileName+"\" WHERE userId="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND cName=\""+cName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新记录表
		
		TableListServlet.getUserRecords(request, response);
		
		
	}

	private void uploadResortsPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tempFileName=request.getSession().getId();
		
		String fileName=null;
		
		String dir="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img";
		
		File tmpFile=new File(dir, tempFileName);
		
		RandomAccessFile randomWrite=new RandomAccessFile(tmpFile, "rw");
		
		//上传
		
		InputStream in=request.getInputStream();
		byte b[] =new byte[10000];
		int n;
		while ((n=in.read(b))!=-1) {
			randomWrite.write(b, 0, n);
		}
		randomWrite.close();
		in.close();
		
		RandomAccessFile randomRead=new RandomAccessFile(tmpFile, "r");
		
		int second=1;
		String secondLine=null;
		while(second<=2) {
			secondLine=randomRead.readLine();
			second++;
		}
		//找到文件名前的等号位置
		int position=secondLine.lastIndexOf("=");
		//获得文件名
		fileName=secondLine.substring(position+2, secondLine.length()-1);
		//定位到临时文件的开头
		randomRead.seek(0);
		//获取第四行回车位置
		long forthEndPosition=0;
		int forth=1;
		
		while((n=randomRead.readByte())!=-1&&(forth<=4)) {
			if(n=='\n') {
				forthEndPosition=randomRead.getFilePointer();
				forth++;
			}
		}
		
		//根据上传的名字，将该文件存入磁盘
		
		byte cc[]=fileName.getBytes("ISO-8859-1");
		fileName=new String(cc, "UTF-8");
		
		File fileUser=new File(dir,fileName);
		
		randomWrite=new RandomAccessFile(fileUser, "rw");
		
		//确定出临时文件中 内容的最后位置，即倒数第二行
		randomRead.seek(randomRead.length());
		
		long endPosition=randomRead.getFilePointer();
		
		long mark=endPosition;
		
		int j=1;
		
		while((mark>=0)&&(j<=2)) {
			mark--;
			randomRead.seek(mark);
			n=randomRead.readByte();
			if(n=='\n') {
				endPosition=randomRead.getFilePointer();
				j++;
			}
		}
		
		//将randomRead指向文件fileTemp的第四行结束的位置
		
		randomRead.seek(forthEndPosition);
		
		long startPoint=randomRead.getFilePointer();
		
		//从临时文件读出客户上传的文件存入fileUser
		//读取第4行结束位置和倒数第2行之间的内容
		while(startPoint<endPosition-1) {
			n=randomRead.readByte();
			randomWrite.write(n);
			startPoint=randomRead.getFilePointer();
		}
		randomWrite.close();
		randomRead.close();
		tmpFile.delete();
		
		//跟新用户信息内保存的图片路径
		String rName=request.getParameter("rName");
		
		String sql="UPDATE tb_resorts SET rPic=\""+fileName+"\" WHERE userId="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND rName=\""+rName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新记录表
		
		TableListServlet.getUserRecords(request, response);
		
	}

	private void uploadFoodPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tempFileName=request.getSession().getId();
		
		String fileName=null;
		
		String dir="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img";
		
		File tmpFile=new File(dir, tempFileName);
		
		RandomAccessFile randomWrite=new RandomAccessFile(tmpFile, "rw");
		
		//上传
		
		InputStream in=request.getInputStream();
		byte b[] =new byte[10000];
		int n;
		while ((n=in.read(b))!=-1) {
			randomWrite.write(b, 0, n);
		}
		randomWrite.close();
		in.close();
		
		RandomAccessFile randomRead=new RandomAccessFile(tmpFile, "r");
		
		int second=1;
		String secondLine=null;
		while(second<=2) {
			secondLine=randomRead.readLine();
			second++;
		}
		//找到文件名前的等号位置
		int position=secondLine.lastIndexOf("=");
		//获得文件名
		fileName=secondLine.substring(position+2, secondLine.length()-1);
		//定位到临时文件的开头
		randomRead.seek(0);
		//获取第四行回车位置
		long forthEndPosition=0;
		int forth=1;
		
		while((n=randomRead.readByte())!=-1&&(forth<=4)) {
			if(n=='\n') {
				forthEndPosition=randomRead.getFilePointer();
				forth++;
			}
		}
		
		//根据上传的名字，将该文件存入磁盘
		
		byte cc[]=fileName.getBytes("ISO-8859-1");
		fileName=new String(cc, "UTF-8");
		
		File fileUser=new File(dir,fileName);
		
		randomWrite=new RandomAccessFile(fileUser, "rw");
		
		//确定出临时文件中 内容的最后位置，即倒数第二行
		randomRead.seek(randomRead.length());
		
		long endPosition=randomRead.getFilePointer();
		
		long mark=endPosition;
		
		int j=1;
		
		while((mark>=0)&&(j<=2)) {
			mark--;
			randomRead.seek(mark);
			n=randomRead.readByte();
			if(n=='\n') {
				endPosition=randomRead.getFilePointer();
				j++;
			}
		}
		
		//将randomRead指向文件fileTemp的第四行结束的位置
		
		randomRead.seek(forthEndPosition);
		
		long startPoint=randomRead.getFilePointer();
		
		//从临时文件读出客户上传的文件存入fileUser
		//读取第4行结束位置和倒数第2行之间的内容
		while(startPoint<endPosition-1) {
			n=randomRead.readByte();
			randomWrite.write(n);
			startPoint=randomRead.getFilePointer();
		}
		randomWrite.close();
		randomRead.close();
		tmpFile.delete();
		
		//跟新用户信息内保存的图片路径
		String fName=request.getParameter("fName");
		
		String sql="UPDATE tb_food SET fPic=\""+fileName+"\" WHERE userId="
		+((User)request.getSession().getAttribute("user")).getUserId()+" AND fName=\""+fName+"\"";
		
		BaseDao.executeMyUpdate(sql, null);
		
		//更新记录表
		
		TableListServlet.getUserRecords(request, response);
		
	}

	private void loadHead(HttpServletRequest request,HttpServletResponse response,String name) throws IOException {
		
		String path="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img\\"+name;
		
		File file=new File(path);
		
		String type=name.substring(name.lastIndexOf(".")+1);
		
		
		if("PNG".equalsIgnoreCase(type)) {
			response.setContentType("image/png");
		}else if ("JPG".equalsIgnoreCase(type)||"JPEG".equalsIgnoreCase(type)) {
			response.setContentType("image/jpeg");
		}else if ("GIF".equalsIgnoreCase(type)) {
			response.setContentType("image/gif");
		}
		
		//设置文件流，传给浏览器
		FileUtils.copyFile(file, response.getOutputStream());
		
	}

	private void uploadHead(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String tempFileName=request.getSession().getId();
		
		String fileName=null;
		
		String dir="C:\\Users\\Yuhang\\eclipse-workspace\\Myblog\\WebContent\\static\\img";
		
		File tmpFile=new File(dir, tempFileName);
		
		RandomAccessFile randomWrite=new RandomAccessFile(tmpFile, "rw");
		
		//上传
		
		InputStream in=request.getInputStream();
		byte b[] =new byte[10000];
		int n;
		while ((n=in.read(b))!=-1) {
			randomWrite.write(b, 0, n);
		}
		randomWrite.close();
		in.close();
		
		RandomAccessFile randomRead=new RandomAccessFile(tmpFile, "r");
		
		int second=1;
		String secondLine=null;
		while(second<=2) {
			secondLine=randomRead.readLine();
			second++;
		}
		//找到文件名前的等号位置
		int position=secondLine.lastIndexOf("=");
		//获得文件名
		fileName=secondLine.substring(position+2, secondLine.length()-1);
		//定位到临时文件的开头
		randomRead.seek(0);
		//获取第四行回车位置
		long forthEndPosition=0;
		int forth=1;
		
		while((n=randomRead.readByte())!=-1&&(forth<=4)) {
			if(n=='\n') {
				forthEndPosition=randomRead.getFilePointer();
				forth++;
			}
		}
		
		//根据上传的名字，将该文件存入磁盘
		
		byte cc[]=fileName.getBytes("ISO-8859-1");
		fileName=new String(cc, "UTF-8");
		
		File fileUser=new File(dir,fileName);
		
		randomWrite=new RandomAccessFile(fileUser, "rw");
		
		//确定出临时文件中 内容的最后位置，即倒数第二行
		randomRead.seek(randomRead.length());
		
		long endPosition=randomRead.getFilePointer();
		
		long mark=endPosition;
		
		int j=1;
		
		while((mark>=0)&&(j<=2)) {
			mark--;
			randomRead.seek(mark);
			n=randomRead.readByte();
			if(n=='\n') {
				endPosition=randomRead.getFilePointer();
				j++;
			}
		}
		
		//将randomRead指向文件fileTemp的第四行结束的位置
		
		randomRead.seek(forthEndPosition);
		
		long startPoint=randomRead.getFilePointer();
		
		//从临时文件读出客户上传的文件存入fileUser
		//读取第4行结束位置和倒数第2行之间的内容
		while(startPoint<endPosition-1) {
			n=randomRead.readByte();
			randomWrite.write(n);
			startPoint=randomRead.getFilePointer();
		}
		randomWrite.close();
		randomRead.close();
		tmpFile.delete();
		
		//跟新用户信息内保存的头像路径
		String sql="UPDATE tb_user SET head=\""+fileName+"\" WHERE userId="+((User)request.getSession().getAttribute("user")).getUserId();
		
		BaseDao.executeMyUpdate(sql, null);
		
	}

}
