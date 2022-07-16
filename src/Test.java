import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.myblog.dao.BaseDao;
import main.java.myblog.dao.UserDao;
import main.java.myblog.po.FoodBean;
import main.java.myblog.po.User;
import main.java.myblog.util.DButil;
/**
 * 测试程序
 * @author Yuhang
 *
 */
public class Test {
	
	public static void testDB() throws SQLException {
		
		
		String sql="SELECT COUNT(*) FROM dbblog.tb_user";
		System.out.println(BaseDao.singleValueQuery(sql, null));
		System.out.println(sql);
		
		}
	
	public static void main(String args[]) throws SQLException {
		testDB();
	}
	
}