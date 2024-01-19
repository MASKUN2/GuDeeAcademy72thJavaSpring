package com.maskun.projectdiary.domain.user;

import com.maskun.projectdiary.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User login(LoginDto loginReq){
        Optional<User> userOpt = userRepository.findUserByUserIdAndUserPw(loginReq.userId(), loginReq.userPw());
        return userOpt.orElse(null);
    }
}
