package com.maskun.projectdiary.web.validation;

import com.maskun.projectdiary.web.dto.UserRegisterReq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 유저 회원가입시 비밀번호 일치여부를 확인하는 밸리데이터 구현체
 */
public class UserRegisterPwCheckValidator implements ConstraintValidator<UserRegisterReqPwCheck, UserRegisterReq> {
    @Override
    public boolean isValid(UserRegisterReq value, ConstraintValidatorContext context) {
        if (value.userPw().equals(value.userPwCheck())) {
            return true;
        }
        return false;
    }
}
