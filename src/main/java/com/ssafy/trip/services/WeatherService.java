package com.ssafy.trip.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherService {

    private String baseDate = "20210531";	//조회하고싶은 날짜
    private String baseTime = "0500";	//조회하고싶은 시간
    private static String JSON = "json";	//조회하고 싶은 type(json, xml 중 고름)

    public void lookUpWeather(double nx, double ny) throws IOException, JSONException {

//		참고문서에 있는 url주소
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
//         홈페이지에서 받은 키
        String serviceKey = "v1VAPk70HR6pmBnm6B27dyTdZJVF%2FKQ6gB8APGQlEeuHnDHFd4euccNJuPkUWigZFKGQ0SXUZu0TDdCIL1h2rA%3D%3D";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(""+nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(""+ny, "UTF-8")); //위도
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(JSON, "UTF-8"));	/* 타입 */

        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
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
        String result= sb.toString();

        //=======이 밑에 부터는 json에서 데이터 파싱해 오는 부분이다=====//

        //아래 블로그 참조
//        https://toubi-tobap.tistory.com/10

    }
}
