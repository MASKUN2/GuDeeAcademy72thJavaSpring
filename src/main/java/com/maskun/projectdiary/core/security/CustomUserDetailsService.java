package com.maskun.projectdiary.core.security;

import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUserOpt = userRepository.findById(username);

        if(foundUserOpt.isPresent()){
            return new SecurityUser(foundUserOpt.get());
        }else {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
    }
}
