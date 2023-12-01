package com.maskun.projectdiary.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DateInfo {
    private int dateIndex;
    private String dateName;
    private boolean isHoliday;
    private List<Memo> dateMemoList;

    public String getDateIndexStr(){
        return String.format("%02d", this.dateIndex);
    }

    public DateInfo (int i){
        this.dateIndex = i;
        this.dateMemoList = new ArrayList<>();
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
    public void addDateMemo (Memo memo){
        this.dateMemoList.add(memo);
    }
    public List<String> getMemoHead(){
        if(this.dateMemoList.size() == 0) {
            return null;
        }else {
            return this.dateMemoList.stream()
                    .map(m -> {
                       if(m.getMemo().length() > 10) {
                          return m.getMemo().substring(0, 8);
                       }else{
                           return m.getMemo();
                       }}).limit(3).collect(Collectors.toList());
        }

    }
}
