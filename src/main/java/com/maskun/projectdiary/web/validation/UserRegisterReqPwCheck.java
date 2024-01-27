package com.maskun.projectdiary.web.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 유저 회원가입시 비밀번호 일치여부를 확인하는 밸리데이터 애노테이션
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRegisterPwCheckValidator.class)
public @interface UserRegisterReqPwCheck {
    String message() default "비밀번호가 일치하지 않습니다";
    Class<?>[] groups() default {};
    Class[] payload() default {};
}
