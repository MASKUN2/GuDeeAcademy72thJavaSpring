package com.maskun.projectdiary.web.dto;

import com.maskun.projectdiary.web.validation.UserRegisterReqPwCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@UserRegisterReqPwCheck
public record UserRegisterReq (
        @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]*$",message = "아이디는 영문자로 시작하고 영문자 혹은 숫자만 가능합니다")
        @Size(min = 4,max = 30, message = "아이디는 4자 이상 30자 이하만 가능합니다")
        String userId,
        @Size(min = 4,max = 30, message = "비밀번호는 4자 이상 30자 이하만 가능합니다")
        String userPw,
        String userPwCheck
){
    public static UserRegisterReq empty() {
        return new UserRegisterReq(null, null, null);
    }
}
