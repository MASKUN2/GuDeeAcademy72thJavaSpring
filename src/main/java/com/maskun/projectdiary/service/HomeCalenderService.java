package com.maskun.projectdiary.service;

import com.maskun.projectdiary.externalApiRequest.HolidayApi;
import com.maskun.projectdiary.externalApiRequest.HolidayApiVo;
import com.maskun.projectdiary.mapper.HomeCalendarMapper;
import com.maskun.projectdiary.vo.domain.User;
import com.maskun.projectdiary.vo.dto.DateInfo;
import com.maskun.projectdiary.vo.dto.HomeCalendar;
import com.maskun.projectdiary.vo.domain.Memo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**휴일정보와 로그인 사용자의 해당월 메모리스트가 담긴 캘린더를 다루는 서비스클래스입니다.
 *
 */
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class HomeCalenderService {

    private final HolidayApi holidayApi;
    private final HomeCalendarMapper homeCalendarMapper;

    /**
     * 원하는 달의 달력을 가져옵니다.
     * @param yearMonth yyyy-mm형식의 String
     * @param user session의 로그인된 member
     * @return Homcalendar 객체
     */
    public HomeCalendar getHomeCalendar(YearMonth yearMonth, User user) {
        HomeCalendar homeCalendar = new HomeCalendar(yearMonth);
        List<DateInfo> dateInfoList  = homeCalendar.getDateInfoList();
        try {
            List<HolidayApiVo> holidayList = holidayApi.getHolidayList(yearMonth);
            holidayList.forEach(h ->{
                int dateIndex = h.getLocdate();
                String dateName = h.getDateName();
                dateInfoList.stream().filter(d -> d.getDayOfMonth() == dateIndex).findFirst()
                                .ifPresent(d -> d.setHoliday(dateName));
            });
        }catch (Exception e){
            e.printStackTrace();
            log.debug("휴일 프로세싱 문제가 발생했습니다. 휴일 정보를 제외하고 계속해서 캘린더를 생성합니다.");
        }

        if(user != null){
            try {
                List<Memo> monthMemoList = homeCalendarMapper.selectMonthMemoList(user,yearMonth);
                monthMemoList.forEach(memo -> {
                    LocalDate memoDate = memo.getMemoDate();
                    dateInfoList.stream().filter(d -> d.getLocalDate() == memoDate).findFirst()
                                .ifPresent(d -> d.addMemo(memo));
                });
            }catch (Exception e){
                e.printStackTrace();
                log.debug("사용자 메모 프로세싱 중 문제가 발생했습니다. 제외하고 캘린더를 생성합니다.");
            }
        }
        log.debug("생성된 homeCalendar.toString() = {}",homeCalendar.toString());
        return homeCalendar;
    }
}
