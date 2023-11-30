package com.maskun.projectdiary.vo;

import lombok.Data;

@Data
public class DateInfo {
    private int dateIndex;
    private String dateName;
    private boolean isHoliday;

    public DateInfo (int i){
        this.dateIndex = i;
    }

    public void setHoliday(boolean bool, String dateName){
        this.isHoliday = bool;
        this.dateName = dateName;
    }
    /*
    public int isHoliday(){
        return (this.isHoliday == true)? 1 : 0;
    }
     */
}
