package main.java.myblog.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;



/**
 * 
 * 创建数据库连接池,返回连接
 * 
 * @author Yuhang
 *
 */


public class DButil {
	
	private static Properties properties;
	private static DataSource dataSource;
	
	static {
		try {
			properties=new Properties();
			//通过文件流读入配置文件
			InputStream in=DButil.class.getClassLoader().getResourceAsStream("resources/db.properties");
			//写入配置对象
			properties.load(in);;
			//创建dbcp数据库连接实例对象
			dataSource=BasicDataSourceFactory.createDataSource(properties);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 链接数据库
	 *
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() {
		Connection connection =null;
		
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
	/**
	 * 关闭资源
	 * @throws SQLException 
	 */
	public static void close(Connection connection,
			PreparedStatement preparedStatement,
			ResultSet resultSet) {
		try {
			
			if (connection!=null) {
				connection.close();
			}
			else if (preparedStatement!=null) {
				preparedStatement.close();
			}else if (resultSet!=null) {
				resultSet.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

