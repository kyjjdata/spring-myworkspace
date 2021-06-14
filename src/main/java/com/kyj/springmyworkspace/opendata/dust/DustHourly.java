package com.kyj.springmyworkspace.opendata.dust;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(DustHourlyId.class)
public class DustHourly {

	// dataTime + itemCode �ΰ��� ��ġ�� ���ϰ� �� ��ǥ���� �� -> PK, Id(Identifier)
	// DB -> �ռ�Ű (Composite Key) : �������� �÷��� ���ļ� Primary Key�� �����
	// JPA -> �ռ�Id(Composite Id) : �������� �ʵ带 ���ļ� Id�� �����

	// �������� �ߺ� ���� ���������� id�� ������
	@Id
	private String dataTime; // 2021-06-14 14:00
	@Id
	private String itemCode; // PM10, PM25

	private String seoul;
	private String gyeonggi;
	private String incheon;
	private String gangwon;
	private String sejong;
	private String chungbuk;
	private String chungnam;
	private String daejeon;
	private String gyeongbuk;
	private String gyeongnam;
	private String daegu;
	private String ulsan;
	private String busan;
	private String jeonbuk;
	private String jeonnam;
	private String gwangju;
	private String jeju;

	public DustHourly(DustHourlyResponse.Item item) {
		this.dataTime = item.getDataTime();
		this.itemCode = item.getItemCode();
		this.busan = item.getBusan();
		this.chungbuk = item.getChungbuk();
		this.chungnam = item.getChungnam();
		this.daegu = item.getDaegu();
		this.daejeon = item.getDaejeon();
		this.gangwon = item.getGangwon();
		this.gwangju = item.getGwangju();
		this.gyeongbuk = item.getGyeongbuk();
		this.gyeonggi = item.getGyeonggi();
		this.gyeongnam = item.getGyeongnam();
		this.incheon = item.getIncheon();
		this.jeju = item.getJeju();
		this.jeonbuk = item.getJeonbuk();
		this.jeonnam = item.getJeonnam();
		this.sejong = item.getSejong();
		this.seoul = item.getSeoul();
		this.ulsan = item.getUlsan();
	}

}
