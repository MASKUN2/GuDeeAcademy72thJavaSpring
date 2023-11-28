package vo;

import java.util.List;

public class Month {
	private int monthNo;
	private List<Date> dateList;
	
	public int getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(int monthNo) {
		this.monthNo = monthNo;
	}
	public List<Date> getDateList() {
		return dateList;
	}
	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}
	@Override
	public String toString() {
		return "Month [monthNo=" + monthNo + ", dateList=" + dateList + "]";
	}
	
	
}
