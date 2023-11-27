package api;

public class ApiVo {
	public String dateKind;
	public String dateName;
	public String isHoliday;
	public String locdate;
	public String seq;
	
	
	@Override
	public String toString() {
		return "ApiVo [dateKind=" + dateKind + ", dateName=" + dateName + ", isHoliday=" + isHoliday + ", locdate="
				+ locdate + ", seq=" + seq + "]";
	}
	public String getDateKind() {
		return dateKind;
	}
	public void setDateKind(String dateKind) {
		this.dateKind = dateKind;
	}
	public String getDateName() {
		return dateName;
	}
	public void setDateName(String dateName) {
		this.dateName = dateName;
	}
	public String getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}
	public String getLocdate() {
		return locdate;
	}
	public void setLocdate(String locdate) {
		this.locdate = locdate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
