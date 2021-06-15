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

//�������� �����ϴ� �޼���
	// cron tab �ð� ����
	// �� �� �� �� �� �� (0�� 30�� �Ž� ���� �ſ� �ų�)
//	@Scheduled(cron = "0 30 * * * *") // �Ž� 30�п� ����

	@SuppressWarnings("deprecation")
	// ���� ����, ms(milli second ����) 1000 ==1�� ,
	@Scheduled(fixedRate = 1000 * 60 * 30) // 30�и���, �׽�Ʈ�� ������, ���α׷��� ���۵� �� �ѹ��� �ٷ� �����
	public void requestDustHourlyData() throws IOException {
		System.out.println(new Date().toLocaleString() + "--����--");
//		getDustHourlyData("PM10"); // �̼�
//		getDustHourlyData("PM25"); // �ʹ̼�

	}

	// �����͸� ��û�ϴ� �޼���
	private void getDustHourlyData(String itemCode) throws IOException {
		String serviceKey = "wTrcDn%2FlP2SPXUgF6GqcEahKSyka9C34jNuu271iB1VDCbS9I3Y%2F2oINqGvOlsHkU8st6H0Fe%2FURODA9un7eww%3D%3D";
		// ������ ��û URL�� ������ ��
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584/ArpltnStatsSvc"); // ���� �ּ�
		builder.append("/getCtprvnMesureLIst");
		builder.append("?itemCode=" + itemCode); // ������ �ڵ� (PM10, PM25)
		builder.append("&dataGubun=HOUR"); // �ð����� ��ȸ
		builder.append("&pageNo=1"); // ������� ����� �ð��� �������� ��ȸ(1������)
		builder.append("&numOfRows=24"); // ������� 24�ð��� ������ ��ȸ
		builder.append("&returnType=json"); // ���� ������ �������� JSON�� ����
		builder.append("&ServiceKey=" + serviceKey);

		// 0. ��û URL Ȯ��
		System.out.println(builder.toString());

		// 1. URL �ּҷ� ���� �� ������ �б�
		URL url = new URL(builder.toString()); // ���ڿ��κ��� URL ��ü ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); // URL �ּҿ� ������
		byte[] result = con.getInputStream().readAllBytes(); // ����������

		// 2. byte[] -> String(JSON)���� ��ȯ
		String data = new String(result);
		System.out.println(data);

		// 3. String(JSON) -> Object�� ��ȯ�� �ؾ���
		// ������ �ִ� ����(Class�� �� Object)���� ��ȯ�ؾ� ����� �� ����
		// fromJson (JSON���ڿ�, ��ȯ��Ÿ��)
		DustHourlyResponse response = new Gson().fromJson(data, DustHourlyResponse.class);
		System.out.println(response);

		// 4. ���� ��ü�� Entity ��ü�� ��ȯ�Ͽ� ����
		for (DustHourlyResponse.Item item : response.getResponse().getBody().getItems()) {
			repo.save(new DustHourly(item));
		}
	}

}
