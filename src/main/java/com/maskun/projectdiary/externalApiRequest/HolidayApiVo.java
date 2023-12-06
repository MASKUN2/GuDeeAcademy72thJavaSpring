package com.maskun.projectdiary.externalApiRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlRootElement;
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
