package main.java.myblog.po;

public class CultureAdminBean {
	private String cName;
	private String cInfo;
	private String cPic;
	private int userId;
	public String getCName() {
		return cName;
	}
	public void setCName(String cName) {
		this.cName = cName;
	}
	public String getCInfo() {
		return cInfo;
	}
	public void setCInfo(String cInfo) {
		this.cInfo = cInfo;
	}
	public String getCPic() {
		return cPic;
	}
	public void setCPic(String cPic) {
		this.cPic = cPic;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
}
