package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.MemberMapper;
import com.maskun.projectdiary.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberMapper mapper;

    public boolean doLogin(Member loginRequest, HttpSession session) {
        Member memberFound = mapper.getMemberForLogin(loginRequest);
        if (memberFound != null){
            session.setAttribute("memberLoggedIn", memberFound);
            return true;
        }else {
            return false;
        }
    }
}
