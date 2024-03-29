package com.kyj.springmyworkspace.opendata.dust;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class DustHourlyService {

	DustHourlyRepository repo;

	@Autowired
	public DustHourlyService(DustHourlyRepository repo) {
		this.repo = repo;
	}

//스케줄을 실행하는 메서드
	// cron tab 시간 형식
	// 초 분 시 일 월 년 (0초 30분 매시 매일 매월 매년)
//	@Scheduled(cron = "0 30 * * * *") // 매시 30분에 수집

	@SuppressWarnings("deprecation")
	// 고정 비율, ms(milli second 단위) 1000 ==1초 ,
	@Scheduled(fixedRate = 1000 * 60 * 30) // 30분마다, 테스트용 스케줄, 프로그램이 시작될 때 한번은 바로 실행됨
	public void requestDustHourlyData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--실행--");
//		getDustHourlyData("PM10"); // 미세
//		getDustHourlyData("PM25"); // 초미세

	}

	// 데이터를 요청하는 메서드
	private void getDustHourlyData(String itemCode) throws IOException {
		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";
		// 데이터 요청 URL을 만들어야 함
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/ArpltnStatsSvc"); // 서비스 주소
		builder.append("/getCtprvnMesureLIst");
		builder.append("?itemCode=" + itemCode); // 아이템 코드 (PM10, PM25)
		builder.append("&dataGubun=HOUR"); // 시간단위 조회
		builder.append("&pageNo=1"); // 현재부터 가까운 시간의 페이지만 조회(1페이지)
		builder.append("&numOfRows=24"); // 현재부터 24시간의 데이터 조회
		builder.append("&returnType=json"); // 응답 데이터 형식으로 JSON을 받음
		builder.append("&ServiceKey=" + serviceKey);

		// 0. 요청 URL 확인
		System.out.println(builder.toString());

		// 1. URL 주소로 접속 및 데이터 읽기
		URL url = new URL(builder.toString()); // 문자열로부터 URL 객체 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL 주소에 접속함
		byte[] result = con.getInputStream().readAllBytes(); // 본문데이터

		// 2. byte[] -> String(JSON)으로 변환
		String data = new String(result);
		System.out.println(data);

		// 3. String(JSON) -> Object로 변환을 해야함
		// 구조가 있는 형식(Class로 찍어낸 Object)으로 변환해야 사용할 수 있음
		// fromJson (JSON문자열, 변환할타입)
		DustHourlyResponse response = new Gson().fromJson(data, DustHourlyResponse.class);
		System.out.println(response);

		// 4. 응답 객체를 Entity 객체로 변환하여 저장
		for (DustHourlyResponse.Item item : response.getResponse().getBody().getItems()) {
			repo.save(new DustHourly(item));
		}
	}

}
