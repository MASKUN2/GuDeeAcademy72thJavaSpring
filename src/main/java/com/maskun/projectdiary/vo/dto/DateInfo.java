package com.maskun.projectdiary.vo.dto;

import com.maskun.projectdiary.vo.domain.Memo;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DateInfo{
    private final LocalDate localDate;
    private final int dayOfMonth;
    private final DayOfWeek dayOfWeek;
    private String dateName;
    private boolean isHoliday;
    private boolean isToday;
    private final List<Memo> memoList;

    public String getDayOfMonth2Digit(){
        return String.format("%02d", this.dayOfMonth);
    }

    public DateInfo (LocalDate localDate){
        this.localDate = localDate;
        this.dayOfMonth = this.localDate.getDayOfMonth();
        this.dayOfWeek = localDate.getDayOfWeek();
        if (this.dayOfWeek == DayOfWeek.SUNDAY){
            this.isHoliday = true;
        }
        if(localDate.equals(LocalDate.now())){
            this.isToday = true;
        }
        this.memoList = new ArrayList<>();
    }

    public void setHoliday(String dateName){
        this.isHoliday = true;
        this.dateName = dateName;
    }

    public void addMemo(Memo memo){
        this.memoList.add(memo);
    }

    /**
     * 달력 칸에 표시될 memo의 일부만 가져오는 메소드입니다.
     */
    public List<String> getMemoHead(){
        final int stringLengthLimit = 10;
        final int count = 3;
        if(this.memoList.size() == 0) {
            return null;
        }else {
            return this.memoList.stream()
                    .map(m -> {
                        String memo = m.getMemoContent();
                        return (memo.length() > stringLengthLimit)?
                               memo.substring(0, stringLengthLimit)
                               :memo;}
                    ).limit(count).collect(Collectors.toList());
        }
    }
}
