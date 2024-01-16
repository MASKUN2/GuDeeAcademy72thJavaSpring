package com.maskun.projectdiary.web.dto;

import com.maskun.projectdiary.domain.memo.Memo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@RequiredArgsConstructor
@Getter
public class DateVo{
    private final LocalDate date;
    private String dateName;
    private Boolean isHoliday;
    private List<Memo> memoList;

    public void setHoliday(final String dateName){
        this.dateName = dateName;
        this.isHoliday = true;
    }

    public void addMemoOne(Memo memo){
        Objects.requireNonNull(memo);
        if(this.memoList == null){
            this.memoList = new ArrayList<>();
        }
        this.memoList.add(memo);
    }
}
