package main.java.myblog.vo;

/**
 * 
 * 一个封装结果的类
 * 
 * @author Yuhang
 *
 */

public class ResultInfo <T>{
	
	private Integer code;//状态码，成功为1，失败为0
	private String msg;//提示消息
	
	private T result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
