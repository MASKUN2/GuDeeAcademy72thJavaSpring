package com.maskun.projectdiary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class LocDateTest {

    @Test
    public void locDateTest(){
        LocalDate localDate = LocalDate.of(2024, 1, 1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println(localDate);
        YearMonth yearMonth = YearMonth.parse("2024-01");
        LocalDate startDate = yearMonth.atDay(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endDate = yearMonth.atEndOfMonth().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        log.info("startDate ="+startDate + "endDate = "+endDate);
        List<LocalDate> localDates = IntStream.iterate(0, n -> n + 1).mapToObj(startDate::plusDays).takeWhile(date -> date.isBefore(endDate)).toList();
        localDates.forEach(System.out::println);
        //int add = 0;
        //List<LocalDate> localDates = new ArrayList<>();


        //Stream.iterate(0, n -> n+1).filter(n -> n < startDate.until(endDate).getDays()).forEach(System.out::println);
        //IntStream.rangeClosed(0, )
    }
}
