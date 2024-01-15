package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.holiday.HolidayApi;
import com.maskun.projectdiary.domain.holiday.HolidayApiVo;
import com.maskun.projectdiary.web.dto.CalendarVo;
import com.maskun.projectdiary.web.dto.DateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        // 해당월의 휴일찾고 매핑하기
        getHolidayVoListOpt(yearMonth).orElse(new ArrayList<>()).forEach(holidayVo ->{
            dateVoList.stream()
                    .filter(dateVo -> dateVo.getDate().equals(holidayVo.getLocdate()))
                    .findFirst()
                    .ifPresent(dateVo -> dateVo.setHoliday(holidayVo.getDateName(), true));
        });

        return new CalendarVo(null, yearMonth, dateVoList);
    }

    private List<LocalDate> getMonthLocalDateList(YearMonth yearMonth) {
        return IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                .mapToObj(yearMonth::atDay)
                .toList();
    }

    private Optional<List<HolidayApiVo>> getHolidayVoListOpt(YearMonth yearMonth){
        try {
            return Optional.ofNullable(holidayApi.getHolidayList(yearMonth));
        } catch (Exception e) {
            log.error("휴일정보를 가져오는데 실패했습니다. 무시하고 계속합니다.", e);
            return Optional.empty();
        }
    }
}
