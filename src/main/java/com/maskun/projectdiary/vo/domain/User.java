package com.maskun.projectdiary.vo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class User {
    private String userId;
    private String userPw;
    private int userLevel;
    private LocalDateTime createdate;
}
