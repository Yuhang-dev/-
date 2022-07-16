package main.java.myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.myblog.po.User;
import main.java.myblog.util.DButil;

/**
 * 数据库基本访问
 * @author Yuhang
 *
 */
public class UserDao {
	
	/**
	 * 通过名字查询用户bean
	 * 
	 * @param uName
	 * @return
	 * @throws SQLException 
	 */
	
	public User queryByName(String uName) throws SQLException {
		
		User user=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try {
			/**
			 * 获取链接
			 * 定义sql语句
			 * 预编译
			 * 设置参数
			 * 执行查询
			 */
			connection=DButil.getConnection();
			String sql="SElECT * FROM tb_user WHERE uName = ?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, uName);
			resultSet=preparedStatement.executeQuery();
			
			//判断结果集
			if (resultSet.next()) {
				//查询到这样的记录
				user=new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setuName(uName);
				user.setuPassword(resultSet.getString("uPassword"));
				user.setuMood(resultSet.getString("mood"));
				user.setuPicture(resultSet.getString("head"));
				user.setuNick(resultSet.getString("uNick"));
				user.setuHome(resultSet.getString("uHome"));
				user.setuSchool(resultSet.getString("uSchool"));
				user.setFoodNum(queryRecordsNums(resultSet.getInt("userId"),"tb_food"));
				user.setCultureNum(queryRecordsNums(resultSet.getInt("userId"),"tb_culture"));
				user.setResortsNum(queryRecordsNums(resultSet.getInt("userId"),"tb_resorts"));
				
				return user;
			}
			
			//没查到记录:
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DButil.close(connection, preparedStatement, resultSet);
		}
		
		
		return null;
		
	}
	
	/**
	 * 
	 * 查询user对应各张表中的记录数量
	 * 
	 * @param user
	 * @param tbName
	 * @return
	 */
	
	public static int queryRecordsNums(int userid,String tbName) {
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		int num=0;
		
		
		try {
			
			if (userid==0) {
				return 0;
			}
			connection=DButil.getConnection();
			String sql="SElECT COUNT(*) FROM "+tbName+" WHERE userId = "+userid;
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				num=resultSet.getInt(1);
			}
			
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.close(connection, preparedStatement, resultSet);
		}
		
		return num;
	}
	
}
