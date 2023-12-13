package com.maskun.projectdiary.ApiRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HolidayApiVo {
	private String dateKind;
	private String dateName;
	private boolean isHoliday;
	private int locdate;
	private String seq;

	public void setIsHoliday(String YorN){
		if(YorN.equals("Y")){
			this.isHoliday = true;
		}
	}
	public void setLocdate(String yyyymmdd){
		this.locdate = Integer.parseInt(yyyymmdd.substring(6));
	}
}
