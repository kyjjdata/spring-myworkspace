package com.kyj.springmyworkspace.opendata3.foodwastetime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(FoodWasteTimeId.class)
public class FoodWasteTime {

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
	private String disHour;
	private String disQuantity;
	private String disCount;
	private String disQuantityRate;
	private String disCountRate;

	public FoodWasteTime(FoodWasteTimeData.List1 list1) {
		this.citySidoName = list1.getCitySidoName();
		this.citySggName = list1.getCitySggName();
		this.disYear = list1.getDisYear();
		this.disMonth = list1.getDisMonth();
		this.disHour = list1.getDisHour();
		this.disQuantity = list1.getDisQuantity();
		this.disCount = list1.getDisCount();
		this.disQuantityRate = list1.getDisQuantityRate();
		this.disCountRate = list1.getDisCountRate();

	}

}
