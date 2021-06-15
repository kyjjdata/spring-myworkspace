package com.kyj.springmyworkspace.opendata2.foodwaste;

import java.util.List;

import lombok.Data;

@Data // getter,setter
public class FoodWasteData {
	private Data1 data;

	@Data
	public class Data1 {
		private List<List1> list;
	}

	@Data
	public class List1 {
		private String disYear;
		private String disMonth;

		private String disDay;
		private String disDayCount;
		private String citySidoName;
		private String citySggName;
		private String disQuantity;
		private String dayAverQuantity;
		private String disCount;
		private String dayAverCount;

	}
}
