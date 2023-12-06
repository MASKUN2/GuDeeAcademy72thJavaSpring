package com.maskun.projectdiary.externalApiRequest;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

public interface HolidayApi {
    List<HolidayApiVo> getHolidayList(String yearMonth) throws IOException, JAXBException;
}
