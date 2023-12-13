package com.maskun.projectdiary.dto;

import com.maskun.projectdiary.domain.entity.Memo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class MemoSetRequestDto implements RequestDto<Memo> {
    private int memoNo;
    private LocalDate memoDate;
    private String memoContent;

    @Override
    public Memo toServiceDto() {
        return Memo.builder().memoNo(memoNo).memoDate(memoDate).memoContent(memoContent).build();
    }
}
