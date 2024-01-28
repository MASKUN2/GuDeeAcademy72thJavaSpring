package com.maskun.projectdiary.domain.user;

import com.maskun.projectdiary.web.dto.LoginDto;
import com.maskun.projectdiary.web.dto.UserRegisterReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User login(LoginDto loginReq) {
        Optional<User> userOpt = userRepository.findUserByUserIdAndUserPw(loginReq.userId(), loginReq.userPw());
        return userOpt.orElse(null);
    }

    @Transactional
    public void registerUser(UserRegisterReq req) {
        //아이디 중복검사
        checkIsDuplicateId(req.userId());

        //엔티티로 변경
        String encodedUserPw = userRepository.getEncodedUserPw(req.userPw());
        User registerUser = User.builder()
                .userId(req.userId())
                .userPw(encodedUserPw)
                .userLevel(0)
                .createdate(LocalDateTime.now())
                .build();
        //등록
            userRepository.save(registerUser);
        //검증
            Optional<User> savedUser = userRepository.findById(registerUser.getUserId());
            if(savedUser.isEmpty()){
                throw new RuntimeException("등록한 유저를 찾는데 실패했습니다. 찾은 유저정보:"+registerUser.getUserId());
            }
    }

    private void checkIsDuplicateId(String userId) throws DuplicateUserIdException{
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            throw new DuplicateUserIdException();
        }
    }
}
