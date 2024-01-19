package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.holiday.HolidayApi;
import com.maskun.projectdiary.domain.holiday.HolidayApiVo;
import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.memo.MemoRepository;
import com.maskun.projectdiary.domain.user.User;
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
     * @param user
     * @return
     */
    public CalendarVo getCalendar(YearMonth yearMonth, User user) {
        // 해당월 캘린더 정렬된 날짜 맵 만들기
        LinkedHashMap<String, DateVo> dateVoMap = getSortedDateVoMap(yearMonth);

        // 해당월의 휴일찾고 매핑하기
        mapHolidayToDateVo(yearMonth, dateVoMap);

        //해당월의 유저 메모를 매핑하기
        mapUserMemoToDateVo(yearMonth, user, dateVoMap);

        //완성된 객체를 반환
        return new CalendarVo(user, yearMonth, dateVoMap.values().stream().toList());
    }

    private LinkedHashMap<String, DateVo> getSortedDateVoMap(YearMonth yearMonth) {
        return getMonthLocalDateList(yearMonth)
                .stream()
                .collect(Collectors.toMap(LocalDate::toString,
                        DateVo::new,
                        (exist, replacement)-> replacement,
                        LinkedHashMap::new)
                );
    }

    private void mapHolidayToDateVo(YearMonth yearMonth, LinkedHashMap<String, DateVo> dateVoMap) {
        getHolidayVoListOpt(yearMonth)
                .orElse(new ArrayList<>())
                .forEach(holiday -> {
                    Optional.ofNullable(dateVoMap.get(holiday.getLocdate().toString()))
                            .ifPresent(dateVo -> dateVo.setHoliday(holiday.getDateName()));
                });
    }

    private void mapUserMemoToDateVo(YearMonth yearMonth, User user, LinkedHashMap<String, DateVo> dateVoMap) {
        getMonthMemoListOpt(yearMonth, user).orElse(new ArrayList<>()).forEach(memo->{
            Optional.ofNullable(dateVoMap.get(memo.getMemoDate().toString()))
                    .ifPresent(dateVo -> dateVo.addMemoOne(memo));
        });
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

    private Optional<List<Memo>> getMonthMemoListOpt(YearMonth yearMonth, User user){
        if(user == null){
            return Optional.empty();
        }
        return Optional.ofNullable(memoRepository.findByMemoDateBetweenAndUserId(yearMonth.atDay(1), yearMonth.atEndOfMonth(), user.getUserId()));
    }
}
