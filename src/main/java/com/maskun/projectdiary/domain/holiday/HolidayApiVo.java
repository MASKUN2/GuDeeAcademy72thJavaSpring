package com.maskun.projectdiary.domain.holiday;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HolidayApiVo {
	private String dateKind;
	private String dateName;
	private boolean isHoliday;
	private LocalDate locdate;
	private String seq;
	private LocalDate localDate;

	public void setIsHoliday(String YorN){
		if(YorN.equals("Y")){
			this.isHoliday = true;
		}
	}
	public void setLocdate(String yyyymmdd){
		this.locdate = LocalDate.parse(yyyymmdd, DateTimeFormatter.BASIC_ISO_DATE);
	}
}
