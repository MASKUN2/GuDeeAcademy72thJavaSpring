package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.holiday.HolidayApi;
import com.maskun.projectdiary.domain.holiday.HolidayApiVo;
import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.memo.MemoRepository;
import com.maskun.projectdiary.web.dto.CalendarVo;
import com.maskun.projectdiary.web.dto.DateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalendarService {
    private final HolidayApi holidayApi;
    private final MemoRepository memoRepository;

    /**
     * @param yearMonth
     * @return
     */
    public CalendarVo getCalendar(YearMonth yearMonth) {
        // 데이트 리스트 만들기
        //List<DateVo> dateVoList = getMonthLocalDateList(yearMonth).stream().map(DateVo::new).toList();
        LinkedHashMap<String, DateVo> dateVoMap = getMonthLocalDateList(yearMonth)
                .stream()
                .collect(Collectors.toMap(LocalDate::toString,
                        DateVo::new,
                        (exist, replacement)-> replacement,
                        LinkedHashMap::new)
                );

        // 해당월의 휴일찾고 매핑하기
        getHolidayVoListOpt(yearMonth)
                .orElse(new ArrayList<>())
                .forEach(holiday -> {
                    Optional.ofNullable(dateVoMap.get(holiday.getLocdate().toString()))
                            .ifPresent(dateVo -> dateVo.setHoliday(holiday.getDateName()));
                });

        //메모를 매핑하기
        getMonthMemoList(yearMonth).forEach(memo->{
            Optional.ofNullable(dateVoMap.get(memo.getMemoDate().toString()))
                    .ifPresent(dateVo -> dateVo.addMemoOne(memo));
        });

        return new CalendarVo(null, yearMonth, dateVoMap.values().stream().toList());
    }
    private List<Memo> getMonthMemoList(YearMonth yearMonth){

        return memoRepository.findByMemoDateBetween(yearMonth.atDay(1), yearMonth.atEndOfMonth());
    }
    private List<LocalDate> getMonthLocalDateList(YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endDate = yearMonth.atEndOfMonth().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return IntStream.iterate(0, n -> n + 1).mapToObj(startDate::plusDays).takeWhile(date -> date.isBefore(endDate)).toList();

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
