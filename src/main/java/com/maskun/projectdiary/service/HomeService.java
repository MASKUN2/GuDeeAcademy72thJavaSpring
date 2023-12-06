package com.maskun.projectdiary.service;

import com.maskun.projectdiary.externalApiRequest.HolidayApi;
import com.maskun.projectdiary.externalApiRequest.HolidayApiVo;
import com.maskun.projectdiary.mapper.HomeMapper;
import com.maskun.projectdiary.vo.DateInfo;
import com.maskun.projectdiary.vo.HomeCalendar;
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
public class HomeService {
    private final HolidayApi holidayApi;
    private final HomeMapper homeMapper;
    public HomeCalendar getCalendar(String yearMonth, HttpSession session) {
        HomeCalendar homeCalendar = new HomeCalendar(yearMonth);
        List<DateInfo> dateInfoList  = homeCalendar.getDateInfoList();
        List<HolidayApiVo> holidayList;
        try {
            holidayList = holidayApi.getHolidayList(yearMonth);
            holidayList.forEach(h ->{
                dateInfoList.get(h.getLocdate() - 1).setHoliday(true,h.getDateName());
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        if(session.getAttribute("memberLoggedIn") != null){
            Member member = (Member) session.getAttribute("memberLoggedIn");
            List<Memo> monthMemoList = homeMapper.selectMonthMemoList(member.getMemberId(),yearMonth);
            monthMemoList.forEach(m -> dateInfoList.get(m.getDateNumber()-1).addDateMemo(m));
        }

        log.debug("생성된 homeCalendar::toString = {}",homeCalendar.toString());
        return homeCalendar;
    }
}
