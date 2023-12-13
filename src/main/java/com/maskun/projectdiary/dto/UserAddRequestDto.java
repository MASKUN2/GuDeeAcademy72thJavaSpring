package com.maskun.projectdiary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserAddRequestDto {
    @NotBlank(message = "빈값을 입력해선 안됩니다.")
    private final String id;
    @NotBlank(message = "빈값을 입력해선 안됩니다.")
    private final String pw;
    @NotBlank(message = "빈값을 입력해선 안됩니다.")
    private final String pwCheck;
}

