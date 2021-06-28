package com.kyj.springmyworkspace.opendata4.foodWasteDay;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@IdClass(FoodWasteDayId.class)
public class FoodWasteDay {

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
	private String disQuantity;
	private String disCount;
	private String disDate;
	private String disQuantityRate;
	private String disCountRate;

	public FoodWasteDay(FoodWasteDayData.List1 list1) {
		this.citySidoName = list1.getCitySidoName();
		this.citySggName = list1.getCitySggName();
		this.disYear = list1.getDisYear();
		this.disMonth = list1.getDisMonth();
		this.disDay = list1.getDisDay();
		this.disQuantity = list1.getDisQuantity();
		this.disCount = list1.getDisCount();
		this.disDate = list1.getDisDate();
		this.disQuantityRate = list1.getDisQuantityRate();
		this.disCountRate = list1.getDisCountRate();

	}

}
