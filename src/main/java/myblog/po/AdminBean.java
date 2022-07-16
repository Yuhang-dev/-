package main.java.myblog.po;

public class AdminBean {
	private int UserPage=1;
	private int foodPage=1;
	private int ResortsPage=1;
	private int CulturePage=1;
	private int MaxUser=0;
	private int MaxFood=0;
	private int MaxResorts=0;
	private int MaxCulture=0;
	public int getUserPage() {
		return UserPage;
	}
	public void setUserPage(int userPage) {
		UserPage = userPage;
	}
	public int getFoodPage() {
		return foodPage;
	}
	public void setFoodPage(int foodPage) {
		this.foodPage = foodPage;
	}
	public int getResortsPage() {
		return ResortsPage;
	}
	public void setResortsPage(int resortsPage) {
		ResortsPage = resortsPage;
	}
	public int getCulturePage() {
		return CulturePage;
	}
	public void setCulturePage(int culturePage) {
		CulturePage = culturePage;
	}
	public int getMaxUser() {
		return MaxUser;
	}
	public void setMaxUser(int maxUser) {
		MaxUser = maxUser;
	}
	public int getMaxFood() {
		return MaxFood;
	}
	public void setMaxFood(int maxFood) {
		MaxFood = maxFood;
	}
	public int getMaxResorts() {
		return MaxResorts;
	}
	public void setMaxResorts(int maxResorts) {
		MaxResorts = maxResorts;
	}
	public int getMaxCulture() {
		return MaxCulture;
	}
	public void setMaxCulture(int maxCulture) {
		MaxCulture = maxCulture;
	}

}
