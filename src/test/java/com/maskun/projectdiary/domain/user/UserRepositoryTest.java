package com.maskun.projectdiary.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void findUserByUserIdAndUserPw() {
        Optional<User> user = userRepository.findUserByUserIdAndUserPw("goodee","1234");
        System.out.println(user.get().getUserId());
        System.out.println(user.get().getUserPw());
    }
}