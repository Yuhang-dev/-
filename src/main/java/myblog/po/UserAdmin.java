package main.java.myblog.po;

public class UserAdmin {
	private Integer userId;
	private String uName;
	private String uPassword;
	private String mood;
	private String head;
	private String uNick;
	private String uHome;
	private String uSchool;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getuName() {
		return uName;
	}
	public void setUName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setUPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuNick() {
		return uNick;
	}
	public void setUNick(String uNick) {
		this.uNick = uNick;
	}
	public String getuHome() {
		return uHome;
	}
	public void setUHome(String uHome) {
		this.uHome = uHome;
	}
	public String getuSchool() {
		return uSchool;
	}
	public void setUSchool(String uSchool) {
		this.uSchool = uSchool;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
}
