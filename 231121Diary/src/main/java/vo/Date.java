package vo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Date {
	private int date;
	private DayOfWeek dayOfWeek;
	private boolean isHoliday;
	private String dateName;
	private String[] memoList;
	
	@Override
	public String toString() {
		return "Date [date=" + date + ", dayOfWeek=" + dayOfWeek + ", isHoliday=" + isHoliday + ", dateName=" + dateName
				+ ", memoList=" + memoList + "]";
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public boolean isHoliday() {
		return isHoliday;
	}
	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	public String[] getMemoList() {
		return memoList;
	}
	public void setMemoList(String[] strings) {
		this.memoList = strings;
	}
	
	
}
