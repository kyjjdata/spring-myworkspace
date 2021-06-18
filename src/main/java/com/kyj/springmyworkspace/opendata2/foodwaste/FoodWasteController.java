package com.kyj.springmyworkspace.opendata2.foodwaste;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodWasteController {

	private FoodWasteRepository repo;

	public FoodWasteController(FoodWasteRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/opendata/foodWaste", method = RequestMethod.GET)
	public List<FoodWaste> getListByDataType() {
		Order[] orders = { new Order(Sort.Direction.DESC, "disMonth"), new Order(Sort.Direction.ASC, "citySggName") };

		// 최근 12시간 데이터만 조회
		return repo.findAll(PageRequest.of(0, 24, Sort.by(orders))).toList();
	}
}