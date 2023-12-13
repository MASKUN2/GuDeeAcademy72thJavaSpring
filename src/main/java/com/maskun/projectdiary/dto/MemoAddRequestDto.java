package com.maskun.projectdiary.dto;

import com.maskun.projectdiary.domain.entity.Memo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MemoAddRequestDto implements RequestDto<Memo> {
    private final LocalDate memoDate;
    private final String memoContent;

    @Override
    public Memo toServiceDto() {
        return Memo.builder().memoDate(memoDate)
                .memoContent(memoContent)
                .build();
    }
}
