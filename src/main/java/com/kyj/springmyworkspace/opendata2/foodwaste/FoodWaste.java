package com.kyj.springmyworkspace.opendata2.foodwaste;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(FoodWasteId.class)
public class FoodWaste {

	// Id -> ������ ��ǥ���� �Ǵ� �͵� -> PK, Id(Identifier)
	// DB -> �ռ�Ű : �������� �÷��� ���ļ� PK�� �����
	// JPA -> �ռ�ID : �������� �ʵ带 ���ļ� Id�� �����

	// �������� �ߺ� ���� ���������� id�� ������
	private String citySidoName;
	@Id
	private String citySggName;
	@Id
	private String disYear;
	@Id
	private String disMonth;
	private String disDay;
	private String disDayCount;
	private String dayAverQuantity;
	private String disQuantity;
	private String disCount;
	private String dayAverCount;

	public FoodWaste(FoodWasteData.List1 list1) {
		this.citySidoName = list1.getCitySidoName();
		this.citySggName = list1.getCitySggName();
		this.disYear = list1.getDisYear();
		this.disMonth = list1.getDisMonth();
		this.disDay = list1.getDisDay();
		this.disDayCount = list1.getDisDayCount();
		this.dayAverQuantity = list1.getDayAverQuantity();
		this.disQuantity = list1.getDisQuantity();
		this.disCount = list1.getDisCount();
		this.dayAverCount = list1.getDayAverCount();

	}

}
