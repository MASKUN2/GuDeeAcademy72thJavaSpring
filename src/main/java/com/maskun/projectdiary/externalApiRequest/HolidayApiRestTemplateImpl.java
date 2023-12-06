package com.maskun.projectdiary.externalApiRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class HolidayApiRestTemplateImpl implements HolidayApi{

    @Override
    public List<HolidayApiVo> getHolidayList(String yearMonth) throws IOException, JAXBException {
        log.debug("yearMonth : {}",yearMonth.toString());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
        String serviceKey = "ZbmPZ9Fmf4lkuzc5QHJOW5eGFvcOxN52X%2FBMmG6A5SBVoVwogSlJ035lgjUPXAZFwZiqmXRhMAqBGAryiHatIQ%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String solYear = yearMonth.substring(0, 4);
        String solMonth = yearMonth.substring(5, 7);
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("serviceKey",serviceKey)
            .queryParam("pageNo", pageNo)
            .queryParam("numOfRows", numOfRows)
            .queryParam("solYear", solYear)
            .queryParam("solMonth", solMonth).build(true).toUri();
        log.debug(uri.toString());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        String xmlResponse = responseEntity.getBody().toString();
        log.debug(xmlResponse);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode rootNode = xmlMapper.readTree(xmlResponse);
        List<JsonNode> nodeList = rootNode.path("body").get("items").findValues("item");
        log.debug(rootNode.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode itemsNode = rootNode.path("body").path("items").path("item");
        System.out.println(rootNode.path("body").path("items").findValues("item"));
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
        return null;

    }
}
