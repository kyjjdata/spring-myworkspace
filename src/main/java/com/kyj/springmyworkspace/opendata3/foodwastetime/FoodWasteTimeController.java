package com.kyj.springmyworkspace.opendata3.foodwastetime;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodWasteTimeController {

	private FoodWasteTimeRepository repo;

	public FoodWasteTimeController(FoodWasteTimeRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/opendata/foodWasteTime", method = RequestMethod.GET)
	public List<FoodWasteTime> getListByDataType() {
		Order[] orders = { new Order(Sort.Direction.DESC, "disMonth"), new Order(Sort.Direction.ASC, "citySggName") };

		// �ֱ� 12�ð� �����͸� ��ȸ
		return repo.findAll(PageRequest.of(0, 24, Sort.by(orders))).toList();
	}
}