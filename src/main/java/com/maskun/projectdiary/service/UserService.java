package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.UserMapper;
import com.maskun.projectdiary.vo.domain.User;
import com.maskun.projectdiary.vo.dto.UserAddDto;
import com.maskun.projectdiary.vo.dto.UserLoginDto;
import jakarta.validation.Valid;
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

    public User doLogin(UserLoginDto userLoginDto) {
        User user = userLoginDto.toDomainEntity();
        User userFound = userMapper.findUserForLogin(user);
        log.debug("찾은 USER = {}",userFound);
        return userFound;
    }
    @Transactional
    public boolean addUser(UserAddDto userAddDto) {
        User user = userAddDto.toDomainEntity();
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
