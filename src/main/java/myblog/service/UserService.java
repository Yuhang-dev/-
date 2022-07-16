package main.java.myblog.service;

import java.sql.SQLException;



import main.java.myblog.dao.UserDao;
import main.java.myblog.po.User;
import main.java.myblog.vo.ResultInfo;


import java.security.NoSuchAlgorithmException;


/**
 * 
 * 
 * 
 * @author Yuhang
 *
 */

public class UserService {
	
	private UserDao userDao=new UserDao();
	
	public ResultInfo<User> userLogin(String uName,String uPassword) throws NoSuchAlgorithmException, SQLException {
		
		ResultInfo<User> resultInfo=new ResultInfo<>();
		
		//判断参数是否为空
		if (uName==null||uName.trim()==""||uPassword==null||uPassword.trim()=="") {
			resultInfo.setCode(0);
			resultInfo.setResult(null);
			resultInfo.setMsg("用户名或密码为空！");
			return resultInfo;
		}
		User user=userDao.queryByName(uName);
		//不为空则进行查询，判断密码是否匹配并返回
	
			//若用户不存在:
			if (user==null) {
				resultInfo.setCode(0);
				resultInfo.setMsg("用户不存在!");
				resultInfo.setResult(user);
				return resultInfo;
			}
			
			String uPassword_query=user.getuPassword();
			
			
			//若密码不匹配:
			if (!uPassword_query.equals(uPassword)) {
				resultInfo.setCode(0);
				resultInfo.setMsg("密码错误!");
				resultInfo.setResult(user);
				return resultInfo;
			}
			
			
			//查到了匹配记录
			resultInfo.setCode(1);
			resultInfo.setMsg("登录中...");
			resultInfo.setResult(user);
			return resultInfo;
	}
}
