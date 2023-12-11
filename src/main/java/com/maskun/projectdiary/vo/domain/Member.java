package com.maskun.projectdiary.vo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class Member {
    private int memberNo; //PK
    private int memberLevel;
    private String memberId;
    private String memberPw;
    private LocalDateTime createdate;
}
