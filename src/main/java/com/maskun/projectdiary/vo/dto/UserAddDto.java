package com.maskun.projectdiary.vo.dto;

import com.maskun.projectdiary.vo.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserAddDto implements Dto<User> {
    private String id;
    private String pw;
    private String pwCheck;

    @Override
    public User toDomainEntity() {
        return User.builder().userId(id).userPw(pw).build();
    }
}
