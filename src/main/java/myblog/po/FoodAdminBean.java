package main.java.myblog.po;

public class FoodAdminBean {
	private String fName;
	private String fInfo;
	private String fPic;
	private int userId;
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getFInfo() {
		return fInfo;
	}
	public void setFInfo(String fInfo) {
		this.fInfo = fInfo;
	}
	public String getFPic() {
		return fPic;
	}
	public void setFPic(String fPic) {
		this.fPic = fPic;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
}
