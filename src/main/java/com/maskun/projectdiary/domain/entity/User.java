package com.maskun.projectdiary.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
@ToString
@Getter
@NoArgsConstructor
public class User {
    private String userId;
    private final String userPw = "PROTECTED";
    private Integer userLevel;
    private LocalDateTime createdate;

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void setUserLevel(final Integer userLevel) {
        this.userLevel = userLevel;
    }

    public void setCreatedate(final LocalDateTime createdate) {
        this.createdate = createdate;
    }
}
