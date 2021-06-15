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

	// Id -> 유일한 대표값이 되는 것들 -> PK, Id(Identifier)
	// DB -> 합성키 : 여러개의 컬럼을 합쳐서 PK로 사용함
	// JPA -> 합성ID : 여러개의 필드를 합쳐서 Id로 사용함

	// 데이터의 중복 저장 방지용으로 id를 지정함
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
