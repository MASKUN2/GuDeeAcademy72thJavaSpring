package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.HolidayApiRequester;
import com.maskun.projectdiary.domain.HolidayApiVo;
import com.maskun.projectdiary.vo.DateInfo;
import com.maskun.projectdiary.vo.HomeCalendar;
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
    private final HolidayApiRequester holidayApiRequester;

    public HomeCalendar getCalendar(String yearMonth, HttpSession session) {
        HomeCalendar homeCalendar = new HomeCalendar(yearMonth);
        List<DateInfo> dateInfoList  = homeCalendar.getDateInfoList();
        List<HolidayApiVo> holidayList;
        try {
            holidayList = holidayApiRequester.getHoliday(yearMonth);
            holidayList.forEach(h ->{
                dateInfoList.get(h.getLocdate() - 1).setHoliday(true,h.getDateName());
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        log.debug("생성된 homeCalendar::toString = {}",homeCalendar.toString());
        return homeCalendar;
    }
}
