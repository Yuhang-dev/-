package main.java.myblog.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.myblog.util.DButil;

/**
 * 
 * 基础DAO，实现基本的
 * 更新(update,insert,delete)，查询操作(单个字段查询,单条,多条查询)
 * 
 * @author Yuhang
 *
 */

public class BaseDao {
	
	/**
	 * 更新操作(不包含)
	 * 
	 * @param sql
	 * @param params
	 * @return 受影响的行数
	 */
	
	public static int executeMyUpdate(String sql,List<Object> params) {
		int rows=0;//影响的行数
		try {
			
			PreparedStatement preparedStatement=null;
			preparedStatement=DButil.getConnection().prepareStatement(sql);
			//循环设置参数
			if (params!=null&&params.size()>0) {
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i+1, params.get(i));
				}
			}
			
			rows=preparedStatement.executeUpdate();
			DButil.close(null,preparedStatement,null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}
	
	/**
	 * 
	 * 单个值的查询操作
	 * 
	 * @param sql
	 * @return 对应值
	 */
	
	public static Object singleValueQuery(String sql,List<Object> params) {
		
		Object result=null;
		PreparedStatement preparedStatement=null;
		
		try {
			
			preparedStatement=DButil.getConnection().prepareStatement(sql);
			
			if (params!=null&&params.size()>0) {
				
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i+1, params.get(i));
				}
			}
			
			ResultSet set=preparedStatement.executeQuery();
			if(set.next()) {
				result=set.getObject(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.close(null, preparedStatement, null);
		}
		return result;
		
	}
	
	/**
	 * 
	 * 多条记录查询
	 * 
	 * @param sql sql语句
	 * @param params 待定参数列表
	 * @param cls Javabean的具体类Class
	 * @return 多个指定Javabean对象的列表
	 */
	
	public static <T> List<T> rowsQuery(String sql,List<T> params,Class<?> cls) {
		
		List<T> resList=new ArrayList<>();
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		
		try {
			
			preparedStatement=DButil.getConnection().prepareStatement(sql);
			
			if (params!=null&&params.size()>0) {
				
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i+1, params.get(i));
					
				}
				
			}
			
			resultSet=preparedStatement.executeQuery();
			
			//Retrieves the number, types and properties of this ResultSet object's columns.
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			
			//得到查询获得的字段数量
			int fieldNum=resultSetMetaData.getColumnCount();
			
			while (resultSet.next()) {
				
				
				T object=(T) cls.getDeclaredConstructor().newInstance();
				
				for (int i = 0; i < fieldNum; i++) {
					//得到查询的每一个列名
					String columnName=resultSetMetaData.getColumnName(i+1);
					
					//通过反射，利用setXxx方法设置实例对象中的每个实例域
					
					Field field=cls.getDeclaredField(columnName);
					
					//得到setXxx方法
					String setMethod="set"+columnName.substring(0,1).toUpperCase()
							+columnName.substring(1);
					Method method=cls.getDeclaredMethod(setMethod, field.getType());
					//获得列值
					Object value=resultSet.getObject(i+1);
					//把结果集中的每列,反射回实例对象
					method.invoke(object, value);
				}
				
				//把加载好的实例对象bean添加回结果列表
				resList.add(object);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DButil.close(null, preparedStatement, resultSet);
			
		}
		return resList;
	}
	
	/**
	 * 
	 * 单条记录查询
	 * 
	 * @param sql sql语句
	 * @param params 待定参数列表
	 * @param cls Javabean的具体类Class
	 * @return 单个指定Javabean对象的列表
	 */
	
	public static<T> T rowQuery(String sql,List<T> params,Class<Object> cls) {
		List<T> resultList=rowsQuery(sql, params, cls);
		T object=null;
		
		
		if (resultList!=null&&resultList.size()>0) {
			return resultList.get(0);
		}
		
		return object;
	}
	
}
