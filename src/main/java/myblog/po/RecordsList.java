package main.java.myblog.po;

import java.util.List;

public class RecordsList {
	
	private List<FoodBean> foodRecordsList=null;
	private int foodNum=0;
	private int currentFoodPage=1;
	private List<ResortsBean> resortsRecordsList=null;
	private int resortsNum=0;
	private int currentResotrsPage=1;
	private List<CultureBean> cultureRecordsList=null;
	private int cultureNum=0;
	private int currentCulturePage=1;
	public List<FoodBean> getFoodRecordsList() {
		return foodRecordsList;
	}
	public void setFoodRecordsList(List<FoodBean> foodRecordsList) {
		this.foodRecordsList = foodRecordsList;
	}
	public List<ResortsBean> getResortsRecordsList() {
		return resortsRecordsList;
	}
	public void setResortsRecordsList(List<ResortsBean> resortsRecordsList) {
		this.resortsRecordsList = resortsRecordsList;
	}
	public List<CultureBean> getCultureRecordsList() {
		return cultureRecordsList;
	}
	public void setCultureRecordsList(List<CultureBean> cultureRecordsList) {
		this.cultureRecordsList = cultureRecordsList;
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public int getCurrentFoodPage() {
		return currentFoodPage;
	}
	public void setCurrentFoodPage(int currentFoodPage) {
		this.currentFoodPage = currentFoodPage;
	}
	public int getResortsNum() {
		return resortsNum;
	}
	public void setResortsNum(int resortsNum) {
		this.resortsNum = resortsNum;
	}
	public int getCurrentResotrsPage() {
		return currentResotrsPage;
	}
	public void setCurrentResotrsPage(int currentResotrsPage) {
		this.currentResotrsPage = currentResotrsPage;
	}
	public int getCultureNum() {
		return cultureNum;
	}
	public void setCultureNum(int cultureNum) {
		this.cultureNum = cultureNum;
	}
	public int getCurrentCulturePage() {
		return currentCulturePage;
	}
	public void setCurrentCulturePage(int currentCulturePage) {
		this.currentCulturePage = currentCulturePage;
	}
	
}
