package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserForLogin(User user);

    int insertUser(User user);
}
