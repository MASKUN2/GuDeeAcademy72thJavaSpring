package com.maskun.projectdiary.externalApiRequest;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HolidayApiRestTemplateImplTest {
    @Autowired
    HolidayApiRestTemplateImpl apiRestTemplate;

    @Autowired
    HolidayApiJavaNetImpl apiJavaNet;
    @Test
    public void URL요청주소가일치한다() throws IOException, JAXBException {
        //apiJavaNet.getHolidayList("2023-12");
        apiRestTemplate.getHolidayList("2023-11");
    }

}