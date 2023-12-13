package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findUserForLogin(@Param("userId") String userId, @Param("userPw") String userPw);

    int insertUser(User user);
}
