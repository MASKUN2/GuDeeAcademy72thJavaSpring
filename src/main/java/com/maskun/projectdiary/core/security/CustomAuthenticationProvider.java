package com.maskun.projectdiary.core.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails foundUserDetails = customUserDetailsService.loadUserByUsername(username);

        String foundUserDetailsPassword = foundUserDetails.getPassword();
        boolean matches = passwordEncoder.matches(password, foundUserDetailsPassword);
        log.debug("encoded pw requested ="+passwordEncoder.encode(password));
        if(matches){
            return new UsernamePasswordAuthenticationToken(username, foundUserDetailsPassword, foundUserDetails.getAuthorities());
        }else {
            throw new AuthenticationCredentialsNotFoundException("인증 정보가 일치하지 않음");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {

        return authenticationType.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
