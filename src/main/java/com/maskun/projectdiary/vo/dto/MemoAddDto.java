package com.maskun.projectdiary.vo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MemoAddDto {
    @NotNull
    private LocalDate date;
    @NotNull
    private String content;
}
