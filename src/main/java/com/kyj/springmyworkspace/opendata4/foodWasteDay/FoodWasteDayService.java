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

	// ������ �����ϴ� �޼���
//	@Scheduled(cron = "0 0 9 1 * *") // �Ŵ� 1�� 9�ÿ� ����
	@SuppressWarnings("deprecation")
	@Scheduled(fixedRate = 1000 * 60 * 30) // �׽�Ʈ��, 30�и���, ���α׷��� ���۵� �� �ѹ��� �ٷ� ����
	public void requestFoodWasteData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--!����!--");

		getFoodWasteData("W01"); // ���α�
		getFoodWasteData("W02"); // �߱�
		getFoodWasteData("W03"); // ��걸
//		getFoodWasteData("W04"); // ������
//		getFoodWasteData("W05"); // ������
//		getFoodWasteData("W06"); // ���빮��
		getFoodWasteData("W07"); // �߶���
		getFoodWasteData("W08"); // ���ϱ�
//		getFoodWasteData("W09"); // ���ϱ�
//		getFoodWasteData("W0A"); // ������
//		getFoodWasteData("W0B"); // �����
//		getFoodWasteData("W0C"); // ����
//		getFoodWasteData("W0D"); // ���빮��
//		getFoodWasteData("W0E"); // ������
//		getFoodWasteData("W0F"); // ��õ��
//		getFoodWasteData("W0G"); // ������
//		getFoodWasteData("W0H"); // ���α�
//		getFoodWasteData("W0I"); // ��õ��
		getFoodWasteData("W0J"); // ��������
//		getFoodWasteData("W0K"); // ���۱�
//		getFoodWasteData("W0L"); // ���Ǳ�
//		getFoodWasteData("W0M"); // ���ʱ�
		getFoodWasteData("W0N"); // ������
//		getFoodWasteData("W0P"); // ������
//		getFoodWasteData("W6Z"); // ���ı�

	}

	// �����͸� ��û�ϴ� �Լ�

	private void getFoodWasteData(String cityCode) throws IOException {

		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";

		// ������ ��û URL�� ������ ��
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/RfidFoodWasteServiceNew"); // ���� �ּ�
		builder.append("/getCityDateList");// �󼼱���ּ�
		builder.append("?serviceKey=" + serviceKey); // ����Ű
		builder.append("&type=json"); // ���䵥�������� json
		builder.append("&disYear=2021"); // �����
		builder.append("&disMonth=03"); // �����
		builder.append("&cityCode=" + cityCode); // �����ڵ�
		builder.append("&page=1"); // ������ ��ȣ
		builder.append("&rowNum=10"); // �� �������� ������ ����

//		------------������ �߰��ϴ¹� ����---------

// �������
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
		FoodWasteDayData data1 = new Gson().fromJson(data, FoodWasteDayData.class);
		System.out.println(data1);

		// 4. ���� ��ü�� Entity ��ü�� ��ȯ�Ͽ� ����
		for (FoodWasteDayData.List1 list1 : data1.getData().getList()) {
			repo.save(new FoodWasteDay(list1));

		}

	}
}
