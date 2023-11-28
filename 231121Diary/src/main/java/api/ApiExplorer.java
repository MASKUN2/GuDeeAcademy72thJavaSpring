package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
/**해당년월의 공휴일을 조회하는 공공 APi의 요청클래스입니다.
 * https://www.data.go.kr/data/15012690/openapi.do
 * */
public class ApiExplorer {
    public static List<ApiVo> getHolidays (LocalDate date) throws IOException, ParserConfigurationException, SAXException {
    	String thisYear = String.valueOf(date.getYear());
    	String thisMonth = String.valueOf(date.getMonthValue());
    	if(thisMonth.length() == 1) {
    		thisMonth = "0"+thisMonth;
    	}
    	
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=ZbmPZ9Fmf4lkuzc5QHJOW5eGFvcOxN52X%2FBMmG6A5SBVoVwogSlJ035lgjUPXAZFwZiqmXRhMAqBGAryiHatIQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(thisYear, "UTF-8")); /*연*/
        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(thisMonth, "UTF-8")); /*월*/
        URL url = new URL(urlBuilder.toString());
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
        String xmlString = sb.toString();
        
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode rootNode = xmlMapper.readTree(xmlString.getBytes());

        ObjectMapper objectMapper = new ObjectMapper();

        // Get the "item" array from the "items" object
        JsonNode itemsNode = rootNode.path("body").path("items").path("item");
        System.out.println(rootNode);
        System.out.println(itemsNode);
        System.out.println(itemsNode.isArray());
        System.out.println("---");
        List<ApiVo> list = new ArrayList<>();
        if(itemsNode.isArray()) {
		    for(JsonNode n : itemsNode) {
		    	ApiVo v = new ApiVo();
		    	System.out.println(n.toString());
		    	v = objectMapper.readValue(n.toString(), ApiVo.class);
		    	list.add(v);
		    }
		    for(ApiVo v : list) {
	        	System.out.println(v.toString());
	        }
		    return list;
        }else if(!itemsNode.isEmpty()){
        	ApiVo v = new ApiVo();
        	v = objectMapper.readValue(itemsNode.toString(), ApiVo.class);
        	System.out.println(v.toString());
        	list.add(v);
        	return list;
		}else {
			return null;
		}
        

        
        
        
        
        
        
    }
    
    

}
