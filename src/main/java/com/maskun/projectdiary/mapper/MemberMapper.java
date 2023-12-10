package com.maskun.projectdiary.mapper;

import com.maskun.projectdiary.vo.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member getMemberForLogin(Member loginRequest);

    int insertMember(Member member);
}
