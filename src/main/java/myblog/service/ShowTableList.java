package main.java.myblog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.java.myblog.dao.BaseDao;
import main.java.myblog.po.CultureBean;
import main.java.myblog.po.FoodBean;
import main.java.myblog.po.PageBean;
import main.java.myblog.po.RecordsList;
import main.java.myblog.po.ResortsBean;
import main.java.myblog.po.User;
import main.java.myblog.util.SessionUtil;

/**
 * Servlet implementation class ShowTableList
 */
@WebServlet("/showlist")
public class ShowTableList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTableList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所要展示的类型
		
		String recordsname=request.getParameter("recordname");
		
		
		if ("food".equals(recordsname)) {
			//获取所要展示的页码
			
			int pagenum=Integer.parseInt(request.getParameter("page"));
			showFoodTable(request, response, pagenum);
			
			//返回页面
			request.setAttribute("changePage", "foodList.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("culture".equals(recordsname)) {
			//获取所要展示的页码
			
			int pagenum=Integer.parseInt(request.getParameter("page"));
			showCultureTable(request, response, pagenum);
			
			//返回页面
			request.setAttribute("changePage", "cultureList.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("resorts".equals(recordsname)) {
			//获取所要展示的页码
			
			int pagenum=Integer.parseInt(request.getParameter("page"));
			showResortsTable(request, response, pagenum);
			
			//返回页面
			request.setAttribute("changePage", "resortsList.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("foodsearch".equals(recordsname)) {
			int pagenum=1;
			
			if (request.getParameter("page")==null) {
				
				//如果page为null，为初始化搜索，先产生结果集，再调用翻页函数到第一页
				
				
				String searchname=request.getParameter("searchname");
				if (searchname==null) {
					return;
				}
				generFoodSearchResult(request,response,searchname);
				showFoodSearch(request, response,1);
				//返回界面
				request.setAttribute("changePage", "searchFood.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				pagenum=Integer.parseInt(request.getParameter("page"));
			
			showFoodSearch(request, response,pagenum);
			
			//返回界面
			request.setAttribute("changePage", "searchFood.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		
			
		}else if ("culturesearch".equals(recordsname)) {
			int pagenum=1;
		
			if (request.getParameter("page")==null) {
				
				//如果page为null，为初始化搜索，先产生结果集，再调用翻页函数到第一页
				
				String searchname=request.getParameter("searchname").trim();
				if (searchname==null) {
					return;
				}
				
				generCultureSearchResult(request,response,searchname);
				showCultureSearch(request, response,1);
				//返回界面
				request.setAttribute("changePage", "searchCulture.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				pagenum=Integer.parseInt(request.getParameter("page"));
			
			showCultureSearch(request, response,pagenum);
			
			//返回界面
			request.setAttribute("changePage", "searchCulture.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		
			
		}else if ("resortssearch".equals(recordsname)) {
			int pagenum=1;
		
			if (request.getParameter("page")==null) {
				
				//如果page为null，为初始化搜索，先产生结果集，再调用翻页函数到第一页
				
				String searchname=request.getParameter("searchname").trim();
				if (searchname==null) {
					return;
				}
				
				generResortsSearchResult(request,response,searchname);
				showResortsSearch(request, response,1);
				
				//返回界面
				request.setAttribute("changePage", "searchResorts.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				pagenum=Integer.parseInt(request.getParameter("page"));
			
			showResortsSearch(request, response,pagenum);
			
			//返回界面
			request.setAttribute("changePage", "searchResorts.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		
		}else {
			
			return;
			
		}
		
		
	}
	
	
	/**
	 * 
	 * 第一次查询，生成查询结果集，保存到session中，方便下文翻页等操作
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */

	private void generResortsSearchResult(HttpServletRequest request, HttpServletResponse response, String name) {

		String sql="SELECT rName,rInfo,rPic FROM tb_resorts WHERE userId="+((User) (request.getSession().getAttribute("user"))).getUserId()
				+" AND rName LIKE \"%"+name+"%\"";
		List<ResortsBean> list=BaseDao.rowsQuery(sql, null, ResortsBean.class);
		
		request.getSession().setAttribute("resortssearchResultList", list);

	}

	
	/**
	 * 
	 * 第一次查询，生成查询结果集，保存到session中，方便下文翻页等操作
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	
	private void generCultureSearchResult(HttpServletRequest request, HttpServletResponse response, String name) {
		
		String sql="SELECT cName,cInfo,cPic FROM tb_culture WHERE userId="+((User) (request.getSession().getAttribute("user"))).getUserId()
				+" AND cName LIKE \"%"+name+"%\"";
		List<CultureBean> list=BaseDao.rowsQuery(sql, null, CultureBean.class);
		
		request.getSession().setAttribute("culturesearchResultList", list);

	}

	/**
	 * 
	 * 第一次查询，生成查询结果集，保存到session中，方便下文翻页等操作
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */

	private void generFoodSearchResult(HttpServletRequest request,HttpServletResponse response,String name) {
		
		String sql="SELECT fName,fInfo,fPic FROM tb_food WHERE userId="+((User) (request.getSession().getAttribute("user"))).getUserId()
				+" AND fName LIKE \"%"+name+"%\"";
		List<FoodBean> list=BaseDao.rowsQuery(sql, null, FoodBean.class);
		
		request.getSession().setAttribute("foodsearchResultList", list);

	}

	private void showResortsSearch(HttpServletRequest request,HttpServletResponse response,int page) {
		
		SessionUtil.DestoryUserSession(request, response, "resortssearchResultList");

		List<ResortsBean> list=((List<ResortsBean>)request.getSession().getAttribute("resortssearchResultList"));
		
		List<ResortsBean> res=new ArrayList<>();
		
		int listsize=list.size();
		
		PageBean<ResortsBean> resortssearchbean=new PageBean<>();
		
		if (list==null||listsize==0) {
			request.getSession().setAttribute("resortssearchbean", resortssearchbean);
			return;
		}
		
		if (page<1) {
			page=1;
		}else if (page>(listsize+1)/2) {
			page=(listsize+1)/2;
		}
		
		if (page==(listsize+1)/2&&(listsize%2)!=0) {
			res.add(list.get(page*2-2));
		}else {
			res.add(list.get(page*2-2));
			res.add(list.get(page*2-1));
		}
		
		
		resortssearchbean.setCurrentPage(page);
		resortssearchbean.setTableList(res);
		
		//把pagebean放到session域中
		
		request.getSession().setAttribute("resortssearchbean", resortssearchbean);
		
	}
	
	
	
	
	private void showCultureSearch(HttpServletRequest request,HttpServletResponse response,int page) {
		
		SessionUtil.DestoryUserSession(request, response, "culturesearchResultList");
		
		List<CultureBean> list=((List<CultureBean>)request.getSession().getAttribute("culturesearchResultList"));
		
		List<CultureBean> res=new ArrayList<>();
		
		int listsize=list.size();
		
		PageBean<CultureBean> culturesearchbean=new PageBean<>();
		
		if (list==null||listsize==0) {
			request.getSession().setAttribute("culturesearchbean", culturesearchbean);
			return;
		}
		
		if (page<1) {
			page=1;
		}else if (page>(listsize+1)/2) {
			page=(listsize+1)/2;
		}
		
		if (page==(listsize+1)/2&&(listsize%2)!=0) {
			res.add(list.get(page*2-2));
		}else {
			res.add(list.get(page*2-2));
			res.add(list.get(page*2-1));
		}
		
		
		culturesearchbean.setCurrentPage(page);
		culturesearchbean.setTableList(res);
		
		//把pagebean放到session域中
			
		request.getSession().setAttribute("culturesearchbean", culturesearchbean);
		
	}
	
	
	/**
	 * 模糊查询
	 * 
	 * 从模糊查询结果列表foodsearchResultList(session对象中)，将对应页数挑选出来放到session对象中去
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	
	private void showFoodSearch(HttpServletRequest request,HttpServletResponse response,int page) {
		
		SessionUtil.DestoryUserSession(request, response, "foodsearchResultList");
		
		List<FoodBean> list=((List<FoodBean>)request.getSession().getAttribute("foodsearchResultList"));
		
		List<FoodBean> res=new ArrayList<>();
		
		PageBean<FoodBean> foodsearchbean=new PageBean<>();
		
		int listsize=list.size();
		
		if (list==null||listsize==0) {
			request.getSession().setAttribute("foodsearchbean", foodsearchbean);
			return;
		}
		
		if (page<1) {
			page=1;
		}else if (page>(listsize+1)/2) {
			page=(listsize+1)/2;
		}
		
		if (page==(listsize+1)/2&&(listsize%2)!=0) {
			res.add(list.get(page*2-2));
		}else {
			res.add(list.get(page*2-2));
			res.add(list.get(page*2-1));
		}
		
		
		foodsearchbean.setCurrentPage(page);
		foodsearchbean.setTableList(res);
		
		//把pagebean放到session域中
		
		request.getSession().setAttribute("foodsearchbean", foodsearchbean);
		
	}

	/**
	 * 
	 * 
	 * 1.RecordsBean中查询所需列表记录
	 * 2.将对象bean封装成PageBean，将其放到session域中
	 * 
	 * @param request
	 * @param response
	 */
	
	public void showFoodTable(HttpServletRequest request,HttpServletResponse response,int pagenum) {
		
		SessionUtil.DestoryUserSession(request, response, "foodpagebean");
		
		List<FoodBean> list=((RecordsList)request.getSession().getAttribute("recordlistbean")).getFoodRecordsList();
		List<FoodBean> res=new ArrayList<>();
		
		int listsize=list.size();
		
		if (!(listsize<1)) {
			if (pagenum<1) {
				pagenum=1;
			}else if (pagenum>(listsize+1)/2) {
				pagenum=(listsize+1)/2;
			}
			
			if (pagenum==(listsize+1)/2&&(listsize%2)!=0) {
				res.add(list.get(pagenum*2-2));
			}else {
				res.add(list.get(pagenum*2-2));
				res.add(list.get(pagenum*2-1));
			}
		
		}
		
		
		//放到session对象中去
		
		PageBean<FoodBean> foodpagebean=new PageBean<>();
		foodpagebean.setCurrentPage(pagenum);
		foodpagebean.setTableList(res);
		
		request.getSession().setAttribute("foodpagebean", foodpagebean);
		
	}
	
	public void showResortsTable(HttpServletRequest request,HttpServletResponse response,int pagenum) {
		
		SessionUtil.DestoryUserSession(request, response, "resortspagebean");
		
		List<ResortsBean> list=((RecordsList)request.getSession().getAttribute("recordlistbean")).getResortsRecordsList();
		List<ResortsBean> res=new ArrayList<>();
		
		int listsize=list.size();
		
		if (!(listsize<1)) {
			if (pagenum<1) {
				pagenum=1;
			}else if (pagenum>(listsize+1)/2) {
				pagenum=(listsize+1)/2;
			}
			
			if (pagenum==(listsize+1)/2&&(listsize%2)!=0) {
				res.add(list.get(pagenum*2-2));
			}else {
				res.add(list.get(pagenum*2-2));
				res.add(list.get(pagenum*2-1));
			}
		}
		
		
		//放到session对象中去
		
		PageBean<ResortsBean> resortspagebean=new PageBean<>();
		resortspagebean.setCurrentPage(pagenum);
		resortspagebean.setTableList(res);
			
		
		request.getSession().setAttribute("resortspagebean", resortspagebean);
		
	}
	
	public void showCultureTable(HttpServletRequest request,HttpServletResponse response,int pagenum) {
		
		SessionUtil.DestoryUserSession(request, response, "culturepagebean");

		List<CultureBean> list=((RecordsList)request.getSession().getAttribute("recordlistbean")).getCultureRecordsList();
		List<CultureBean> res=new ArrayList<>();
		
		int listsize=list.size();
		
		if (!(listsize<1)) {
			if (pagenum<1) {
				pagenum=1;
			}else if (pagenum>(listsize+1)/2) {
				pagenum=(listsize+1)/2;
			}
			
			if (pagenum==(listsize+1)/2&&(listsize%2)!=0) {
				res.add(list.get(pagenum*2-2));
			}else {
				res.add(list.get(pagenum*2-2));
				res.add(list.get(pagenum*2-1));
			}
		}

		//放到session对象中去
		
		PageBean<CultureBean> culturepagebean=new PageBean<>();
		culturepagebean.setCurrentPage(pagenum);
		culturepagebean.setTableList(res);
			
		
		request.getSession().setAttribute("culturepagebean", culturepagebean);
		
	}
	
}
