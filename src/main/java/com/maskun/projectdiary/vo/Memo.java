package com.maskun.projectdiary.vo;

import lombok.Data;

@Data
public class Memo {
    private int memoNo;
    private int dateNumber;
    private String memo;

    public void setDateNumber(String numberStr){
        this.dateNumber = Integer.parseInt(numberStr);
    }
}
