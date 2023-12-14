package com.maskun.projectdiary.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserAddServiceDto implements ServiceDto{
    private final String userId;
    private final String userPw;

}
