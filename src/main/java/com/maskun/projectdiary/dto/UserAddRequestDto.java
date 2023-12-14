package com.maskun.projectdiary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAddRequestDto implements RequestDto<UserAddServiceDto>{
    @NotBlank(message = "아이디에 빈값을 입력해선 안됩니다.")
    private final String id;
    @NotBlank(message = "패스워드에 빈값을 입력해선 안됩니다.")
    private final String pw;
    @NotBlank(message = "패스워드 체크에 빈값을 입력해선 안됩니다.")
    private final String pwCheck;

    @Override
    public UserAddServiceDto toServiceDto() {
        return new UserAddServiceDto(id,pw);
    }
}

