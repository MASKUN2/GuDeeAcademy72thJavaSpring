package com.maskun.projectdiary.dto;

import com.maskun.projectdiary.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    private final String id;
    private final String pw;
}
