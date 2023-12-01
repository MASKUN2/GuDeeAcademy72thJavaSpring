package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.ScheduleMapper;
import com.maskun.projectdiary.vo.Member;
import com.maskun.projectdiary.vo.Memo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;
    public List<Memo> getDateSchedule(String yearMonthDate, HttpSession session) {
        Member member = (Member) session.getAttribute("memberLoggedIn");
        List<Memo> dateScheduleList = scheduleMapper.selectDateScheduleList(member.getMemberId(), yearMonthDate);
        return dateScheduleList;
    }
    @Transactional
    public boolean setDateSchedule(int memoNo, String memo) {
        int result = scheduleMapper.updateScheduleMemo(memoNo,memo);
        return (result == 1)? true:false;
    }
    @Transactional
    public boolean delteDateSchedule(int memoNo) {
        int result = scheduleMapper.deleteScheduleMemo(memoNo);
        return (result == 1)? true:false;
    }
    @Transactional
    public boolean insertDateSchedule(String memberId, String yearMonthDate, String memo) {
        int result = scheduleMapper.insertScheduleMemo(memberId,yearMonthDate,memo);
        return (result == 1)? true:false;
    }
}
