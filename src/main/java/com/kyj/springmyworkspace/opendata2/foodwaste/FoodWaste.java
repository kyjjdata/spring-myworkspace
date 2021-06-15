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
	@Id
	private String disYear;
	@Id
	private String disMonth;
	@Id
	private String citySggName;

	private String disDay;
	private String disDayCount;
	private String citySidoName;
	private String disQuantity;
	private String dayAverQuantity;
	private String disCount;
	private String dayAverCount;

	public FoodWaste(FoodWasteData.List1 list1) {

		this.disYear = list1.getDisYear();
		this.disMonth = list1.getDisMonth();
		this.citySggName = list1.getCitySggName();
		this.disDay = list1.getDisDay();
		this.disDayCount = list1.getDisDayCount();
		this.citySidoName = list1.getCitySidoName();
		this.disQuantity = list1.getDisQuantity();
		this.dayAverQuantity = list1.getDayAverQuantity();
		this.disCount = list1.getDisCount();
		this.dayAverCount = list1.getDayAverCount();

	}

}
