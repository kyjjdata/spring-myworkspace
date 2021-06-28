package com.kyj.springmyworkspace.opendata3.foodwastetime;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodWasteTimeId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1743176488971107945L;
	private String disYear;
	private String disMonth;
	private String citySggName;

}
