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

	// ������ �����ϴ� �޼���
//	@Scheduled(cron = "0 0 9 1 * *") // �Ŵ� 1�� 9�ÿ� ����
	@SuppressWarnings("deprecation")
	@Scheduled(fixedRate = 1000 * 60 * 30) // �׽�Ʈ��, 30�и���, ���α׷��� ���۵� �� �ѹ��� �ٷ� ����
	public void requestFoodWasteData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--!����!--");
		getFoodWasteData("W07"); // �߶���
		getFoodWasteData("W01"); // ���α�
		getFoodWasteData("W02"); // �߱�
	}

	// �����͸� ��û�ϴ� �Լ�
	private void getFoodWasteData(String cityCode) throws IOException {
		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";
		// ������ ��û URL�� ������ ��
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/RfidFoodWasteServiceNew"); // ���� �ּ�
		builder.append("/getCityDayList");// �󼼱���ּ�
		builder.append("?serviceKey=" + serviceKey); // ����Ű
		builder.append("&type=json"); // ���䵥�������� json
		builder.append("&disYear=2021"); // �����
		builder.append("&disMonth=03"); // �����
		builder.append("&cityCode=" + cityCode); // �����ڵ�
		builder.append("&page=1"); // ������ ��ȣ
		builder.append("&rowNum=7"); // �� �������� ������ ����

		// 0. ��û URL Ȯ��
		System.out.println(builder.toString());

		// 1. URL �ּҷ� ���� �� ������ �б�
		URL url = new URL(builder.toString()); // ���ڿ��κ��� URL ��ü ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL �ּҿ� ����
		byte[] result = con.getInputStream().readAllBytes(); // ����(list) �����͸� ����Ʈ ������ �о����.

		// 2. byte[] -> String(JSON)���� ��ȯ, "UTF-8"�� ��ȯ
		String data = new String(result, "UTF-8");
		System.out.println(data);

		// 3. String(JSON) -> Object�� ��ȯ
		// ������ �ִ� ����(Class�� �� Object)���� ��ȯ�ؾ� ����� �� ����.
		FoodWasteData data1 = new Gson().fromJson(data, FoodWasteData.class);
		System.out.println(data1);

		// 4. ���� ��ü�� Entity ��ü�� ��ȯ�Ͽ� ����
		for (FoodWasteData.List1 list1 : data1.getData().getList()) {
			repo.save(new FoodWaste(list1));

		}

	}
}
