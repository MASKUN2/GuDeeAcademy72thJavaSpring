package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    User findUserForLogin(@Param("userId") String userId, @Param("userPw") String userPw);

    int insertUser(Map paramMap);
}
