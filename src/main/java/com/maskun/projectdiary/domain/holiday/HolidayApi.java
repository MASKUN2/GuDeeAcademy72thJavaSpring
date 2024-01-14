package com.maskun.projectdiary.domain.holiday;


import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

public interface HolidayApi {

    List<HolidayApiVo> getHolidayList(YearMonth yearMonth) throws IOException;
}
