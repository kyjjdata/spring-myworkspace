package com.kyj.springmyworkspace.opendata2.foodwaste;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class FoodWasteService {

	FoodWasteRepository repo;

	@Autowired
	public FoodWasteService(FoodWasteRepository repo) {
		this.repo = repo;
	}

	// 스케줄 실행하는 메서드
//	@Scheduled(cron = "0 0 9 1 * *") // 매달 1일 9시에 실행
	@SuppressWarnings("deprecation")
	@Scheduled(fixedRate = 1000 * 60 * 30) // 테스트용, 30분마다, 프로그램이 시작될 때 한번은 바로 실행
	public void requestFoodWasteData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--!실행!--");
		getFoodWasteData("W07"); // 중랑구
		getFoodWasteData("W01"); // 종로구
		getFoodWasteData("W02"); // 중구
	}

	// 데이터를 요청하는 함수
	private void getFoodWasteData(String cityCode) throws IOException {
		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";
		// 데이터 요청 URL을 만들어야 함
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/RfidFoodWasteServiceNew"); // 서비스 주소
		builder.append("/getCityDayList");// 상세기능주소
		builder.append("?serviceKey=" + serviceKey); // 서비스키
		builder.append("&type=json"); // 응답데이터형식 json
		builder.append("&disYear=2021"); // 배출년
		builder.append("&disMonth=03"); // 배출월
		builder.append("&cityCode=" + cityCode); // 지역코드
		builder.append("&page=1"); // 페이지 번호
		builder.append("&rowNum=7"); // 한 페이지당 데이터 갯수

		// 0. 요청 URL 확인
		System.out.println(builder.toString());

		// 1. URL 주소로 접속 및 데이터 읽기
		URL url = new URL(builder.toString()); // 문자열로부터 URL 객체 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL 주소에 접속
		byte[] result = con.getInputStream().readAllBytes(); // 본문(list) 데이터를 바이트 단위로 읽어들임.

		// 2. byte[] -> String(JSON)으로 변환, "UTF-8"로 변환
		String data = new String(result, "UTF-8");
		System.out.println(data);

		// 3. String(JSON) -> Object로 변환
		// 구조가 있는 형식(Class로 찍어낸 Object)으로 변환해야 사용할 수 있음.
		FoodWasteData data1 = new Gson().fromJson(data, FoodWasteData.class);
		System.out.println(data1);

		// 4. 응답 객체를 Entity 객체로 변환하여 저장
		for (FoodWasteData.List1 list1 : data1.getData().getList()) {
			repo.save(new FoodWaste(list1));

		}

	}
}
