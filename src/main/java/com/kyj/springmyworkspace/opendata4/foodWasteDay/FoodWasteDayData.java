package com.kyj.springmyworkspace.opendata4.foodWasteDay;

import java.util.List;

import lombok.Data;

@Data // getter,setter
public class FoodWasteDayData {
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
		private String citySidoName;
		private String citySggName;
		private String disQuantity;
		private String disCount;
		private String disDate;
		private String disQuantityRate;
		private String disCountRate;
	}

	private Data3 data2;

	@Data
	public class Data3 {
		private List<List1> list;
	}

	@Data
	public class List2 {
		private String disYear;
		private String disMonth;
		private String disDay;
		private String citySidoName;
		private String citySggName;
		private String disQuantity;
		private String disCount;
		private String disDate;
		private String disQuantityRate;
		private String disCountRate;
	}
}
