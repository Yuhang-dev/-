package main.java.myblog.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.dao.UserDao;
import main.java.myblog.po.CultureBean;
import main.java.myblog.po.FoodBean;
import main.java.myblog.po.RecordsList;
import main.java.myblog.po.ResortsBean;
import main.java.myblog.po.User;


/**
 * Servlet implementation class TableListServlet
 */
@WebServlet("/TableListServlet")
public class TableListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	/**
	 * 
	 * 刷新RecordsList中的属性,更新或加载用户所拥有的表记录(包括FoodBean，ResortsBean，CultureBean)
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	
	public static void getUserRecords(HttpServletRequest request,HttpServletResponse response) {
		
		int userid=((User) (request.getSession().getAttribute("user"))).getUserId();
		
		String sqlQueryFood="SELECT fName,fInfo,fPic FROM tb_food WHERE userId="+userid;
		
		String sqlQueryResort="SELECT rName,rInfo,rPic FROM tb_resorts WHERE userId="+userid;
		
		String sqlQueryCulture="SELECT cName,cInfo,cPic FROM tb_culture WHERE userId="+userid;
		
		
		RecordsList recordslist=new RecordsList();
		
		//将结果放入用户记录列表
		
		recordslist.setFoodRecordsList(BaseDao.rowsQuery(sqlQueryFood, null, FoodBean.class));
		recordslist.setResortsRecordsList(BaseDao.rowsQuery(sqlQueryResort, null, ResortsBean.class));
		recordslist.setCultureRecordsList(BaseDao.rowsQuery(sqlQueryCulture, null, CultureBean.class));
		recordslist.setFoodNum(UserDao.queryRecordsNums(userid, "tb_food"));
		recordslist.setResortsNum(UserDao.queryRecordsNums(userid, "tb_resorts"));
		recordslist.setCultureNum(UserDao.queryRecordsNums(userid, "tb_culture"));
		
		//将三种记录封装对象recordsList bean存入session
		
		request.getSession().setAttribute("recordlistbean", recordslist);
		
	}
	
}
