package com.maskun.projectdiary.externalApiRequest;


import java.io.IOException;
import java.util.List;

public interface HolidayApi {

    List<HolidayApiVo> getHolidayList(String yearMonth) throws IOException;
}
