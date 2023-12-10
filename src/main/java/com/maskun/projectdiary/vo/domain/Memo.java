package com.maskun.projectdiary.vo.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Memo {
    private int memoNo;
    private String memberId;
    private LocalDate memoDate;
    private String memoContent;
}
