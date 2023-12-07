package com.maskun.projectdiary.service;

import com.maskun.projectdiary.externalApiRequest.HolidayApi;
import com.maskun.projectdiary.externalApiRequest.HolidayApiVo;
import com.maskun.projectdiary.mapper.HomeMapper;
import com.maskun.projectdiary.vo.DateInfo;
import com.maskun.projectdiary.vo.HomeCalendar;
import com.maskun.projectdiary.vo.Member;
import com.maskun.projectdiary.vo.Memo;
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

    /**
     * 원하는 달의 달력을 가져옵니다.
     * @param yearMonth yyyy-mm형식의 String
     * @param member session의 로그인된 member
     * @return Homcalendar 객체
     */
    public HomeCalendar getCalendar(String yearMonth, Member member) {
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
            log.debug("휴일을 처리하는 중 문제가 발생했습니다.");
        }

        if(member != null){
            List<Memo> monthMemoList = homeMapper.selectMonthMemoList(member.getMemberId(),yearMonth);
            monthMemoList.forEach(m -> dateInfoList.get(m.getDateNumber()-1).addDateMemo(m));
        }

        log.debug("생성된 homeCalendar.toString() = {}",homeCalendar.toString());
        return homeCalendar;
    }
}
