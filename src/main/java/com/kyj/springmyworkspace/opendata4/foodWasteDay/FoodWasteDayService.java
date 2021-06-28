package com.kyj.springmyworkspace.opendata4.foodWasteDay;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class FoodWasteDayService {

	FoodWasteDayRepository repo;

	@Autowired
	public FoodWasteDayService(FoodWasteDayRepository repo) {
		this.repo = repo;
	}

	// 스케줄 실행하는 메서드
//	@Scheduled(cron = "0 0 9 1 * *") // 매달 1일 9시에 실행
	@SuppressWarnings("deprecation")
	@Scheduled(fixedRate = 1000 * 60 * 30) // 테스트용, 30분마다, 프로그램이 시작될 때 한번은 바로 실행
	public void requestFoodWasteData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--!실행!--");

		getFoodWasteData("W01"); // 종로구
		getFoodWasteData("W02"); // 중구
		getFoodWasteData("W03"); // 용산구
//		getFoodWasteData("W04"); // 성동구
//		getFoodWasteData("W05"); // 광진구
//		getFoodWasteData("W06"); // 동대문구
		getFoodWasteData("W07"); // 중랑구
		getFoodWasteData("W08"); // 성북구
//		getFoodWasteData("W09"); // 강북구
//		getFoodWasteData("W0A"); // 도봉구
//		getFoodWasteData("W0B"); // 노원구
//		getFoodWasteData("W0C"); // 은평구
//		getFoodWasteData("W0D"); // 서대문구
//		getFoodWasteData("W0E"); // 마포구
//		getFoodWasteData("W0F"); // 양천구
//		getFoodWasteData("W0G"); // 강서구
//		getFoodWasteData("W0H"); // 구로구
//		getFoodWasteData("W0I"); // 금천구
		getFoodWasteData("W0J"); // 영등포구
//		getFoodWasteData("W0K"); // 동작구
//		getFoodWasteData("W0L"); // 관악구
//		getFoodWasteData("W0M"); // 서초구
		getFoodWasteData("W0N"); // 강남구
//		getFoodWasteData("W0P"); // 강동구
//		getFoodWasteData("W6Z"); // 송파구

	}

	// 데이터를 요청하는 함수

	private void getFoodWasteData(String cityCode) throws IOException {

		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";

		// 데이터 요청 URL을 만들어야 함
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/RfidFoodWasteServiceNew"); // 서비스 주소
		builder.append("/getCityDateList");// 상세기능주소
		builder.append("?serviceKey=" + serviceKey); // 서비스키
		builder.append("&type=json"); // 응답데이터형식 json
		builder.append("&disYear=2021"); // 배출년
		builder.append("&disMonth=03"); // 배출월
		builder.append("&cityCode=" + cityCode); // 지역코드
		builder.append("&page=1"); // 페이지 번호
		builder.append("&rowNum=10"); // 한 페이지당 데이터 갯수

//		------------데이터 추가하는법 연습---------

// 여기까지
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
		FoodWasteDayData data1 = new Gson().fromJson(data, FoodWasteDayData.class);
		System.out.println(data1);

		// 4. 응답 객체를 Entity 객체로 변환하여 저장
		for (FoodWasteDayData.List1 list1 : data1.getData().getList()) {
			repo.save(new FoodWasteDay(list1));

		}

	}
}
