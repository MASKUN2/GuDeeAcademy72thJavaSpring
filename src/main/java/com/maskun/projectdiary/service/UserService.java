package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.UserMapper;
import com.maskun.projectdiary.domain.entity.User;
import com.maskun.projectdiary.dto.UserAddRequestDto;
import com.maskun.projectdiary.dto.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;

    public User doLogin(String userId, String userPw) {
        User userFound = userMapper.findUserForLogin(userId, userPw);
        log.debug("찾은 USER = {}",userFound);
        return userFound;
    }
    @Transactional
    public boolean addUser(UserAddRequestDto userAddDto) {
        User user = null;
        int numberOfAffectedRows = userMapper.insertUser(user);
        if (numberOfAffectedRows == 1){
            log.debug("성공");
            return true;
        }else {
            log.debug("실패");
            return false;
        }
    }
}
