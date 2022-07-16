package main.java.myblog.po;

/**
 * 一个用户bean
 * @author Yuhang
 *
 */
public class User {
	private Integer userId;
	private String uName;
	private String uPassword;
	private String uMood;
	private String uPicture;
	private String uNick;
	private String uHome;
	private String uSchool;
	private int foodNum;
	private int resortsNum;
	private int cultureNum;
	
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	
	public String getuPicture() {
		return uPicture;
	}
	public void setuPicture(String uPicture) {
		this.uPicture = uPicture;
	}
	public String getuHome() {
		return uHome;
	}
	public void setuHome(String uHome) {
		this.uHome = uHome;
	}
	public String getuSchool() {
		return uSchool;
	}
	public void setuSchool(String uSchool) {
		this.uSchool = uSchool;
	}
	public String getuNick() {
		return uNick;
	}
	public void setuNick(String uNick) {
		this.uNick = uNick;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public int getResortsNum() {
		return resortsNum;
	}
	public void setResortsNum(int resortsNum) {
		this.resortsNum = resortsNum;
	}
	public int getCultureNum() {
		return cultureNum;
	}
	public void setCultureNum(int cultureNum) {
		this.cultureNum = cultureNum;
	}
	public String getuMood() {
		return uMood;
	}
	public void setuMood(String uMood) {
		this.uMood = uMood;
	}
	
	
	
}
