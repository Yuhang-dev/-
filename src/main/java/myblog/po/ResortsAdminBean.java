package main.java.myblog.po;

public class ResortsAdminBean {
	private String rName;
	private String rInfo;
	private String rPic;
	private int userId;
	public String getRName() {
		return rName;
	}
	public void setRName(String rName) {
		this.rName = rName;
	}
	public String getRInfo() {
		return rInfo;
	}
	public void setRInfo(String rInfo) {
		this.rInfo = rInfo;
	}
	public String getRPic() {
		return rPic;
	}
	public void setRPic(String rPic) {
		this.rPic = rPic;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
}
