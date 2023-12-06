package com.maskun.projectdiary.externalApiRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Primary
@Component
public class HolidayApiJavaNetImpl implements HolidayApi {
    @Override
    public List<HolidayApiVo> getHolidayList(String yearMonth) throws IOException {
        //공휴일정보를 불러오는 공공API

        String yearStr = yearMonth.substring(0, 4);
        String monthStr = yearMonth.substring(5, 7);
        log.debug("year : {}",yearStr);
        log.debug("month : {}",monthStr);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=ZbmPZ9Fmf4lkuzc5QHJOW5eGFvcOxN52X%2FBMmG6A5SBVoVwogSlJ035lgjUPXAZFwZiqmXRhMAqBGAryiHatIQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(yearStr, "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(monthStr, "UTF-8")); /*월*/
        URL url = new URL(urlBuilder.toString());
        log.debug(url.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        ObjectMapper objectMapper = new ObjectMapper();
        String rawResponse = sb.toString();
        //파싱작업
        XmlMapper xmlMapper = new XmlMapper();
        log.debug(rawResponse);
        JsonNode rootNode = xmlMapper.readTree(rawResponse);
        //노드탐색 후 매핑
        JsonNode itemsNode = rootNode.path("body").path("items").path("item");
        List<HolidayApiVo> list = new ArrayList<>();
        if(itemsNode.isArray()) {
            for(JsonNode n : itemsNode) {
                HolidayApiVo v = new HolidayApiVo();
                v = objectMapper.readValue(n.toString(), HolidayApiVo.class);
                list.add(v);
            }
            for(HolidayApiVo v : list) {
            }
        }else if(!itemsNode.isEmpty()){
            HolidayApiVo v = new HolidayApiVo();
            v = objectMapper.readValue(itemsNode.toString(), HolidayApiVo.class);
            list.add(v);
        }
        list.forEach( v -> log.debug( "찾은 휴일정보 {}",v.toString()));
        return list;
    }

}
