package com.maskun.projectdiary.domain.user;

import com.maskun.projectdiary.web.dto.UserRegisterReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void registerUser중복된아이디로실패한다() {
        UserRegisterReq registerReq = new UserRegisterReq("goodee", "1234", "1234");
        try {
            userService.registerUser(registerReq);
        }catch (Exception e){
            log.debug("오류", e);
            assertTrue(true);
            return;
        }
        fail();

    }
    @Test
    @Transactional
    void registerUser신규회원이등록된다() {
        UserRegisterReq registerReq = new UserRegisterReq("goodee1", "1234", "1234");
        try {
            userService.registerUser(registerReq);
        }catch (Exception e){
            log.debug("오류", e);
            fail();
            return;
        }
            User savedUser = userRepository.findById(registerReq.userId()).get();
            log.debug("등록완료"+savedUser.toString());
            assertTrue(true);

    }

}