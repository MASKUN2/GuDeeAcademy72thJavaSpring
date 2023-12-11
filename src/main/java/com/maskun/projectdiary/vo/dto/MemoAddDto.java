package com.maskun.projectdiary.vo.dto;

import com.maskun.projectdiary.vo.domain.Memo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MemoAddDto implements Dto<Memo>{
    @NotNull
    private LocalDate date;
    @NotNull
    private String content;

    @Override
    public Memo toDomainEntity() {
        return Memo.builder().memoDate(date).memoContent(content).build();
    }
}
