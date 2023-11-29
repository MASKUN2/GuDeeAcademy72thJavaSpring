package com.maskun.projectdiary.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private int memberNo; //PK
    private int member_level;
    private String memberId;
    private String memberPw;
    private LocalDateTime createdate;
}
