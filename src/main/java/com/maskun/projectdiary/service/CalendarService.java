package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.holiday.HolidayApi;
import com.maskun.projectdiary.web.dto.CalendarVo;
import com.maskun.projectdiary.web.dto.DateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;
@Slf4j
@RequiredArgsConstructor
@Service
public class CalendarService {
    private final HolidayApi holidayApi;

    /**
     * @param yearMonth
     * @return
     */
    public CalendarVo getCalendar(YearMonth yearMonth) {
        // 데이트 리스트 만들기
        List<DateVo> dateVoList = getMonthLocalDateList(yearMonth).stream().map(DateVo::new).toList();
        // 해당월의 휴일찾기
        try {
            holidayApi.getHolidayList(yearMonth).forEach(holidayVo ->{
                dateVoList.stream()
                        .filter(dateVo -> dateVo.getDate() == holidayVo.getLocalDate())
                        .findFirst()
                        .ifPresent(dateVo -> dateVo.setHoliday(holidayVo.getDateName(), true));
            });
        }catch (Exception e){
            log.error("휴일정보를 가져와 매핑하는데 실패했습니다. 무시하고 계속합니다.", e);
        }

        return null;
    }

    private List<LocalDate> getMonthLocalDateList(YearMonth yearMonth) {
        return IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                .mapToObj(yearMonth::atDay)
                .toList();
    }
}
