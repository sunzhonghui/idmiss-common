package com.idmiss.common.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idmiss.common.order.service.OrderGenerateService;
import com.idmiss.global.model.Result;
import com.idmiss.global.util.ResultUtil;

@RequestMapping("order")
@RestController()
public class OrderGenerateController {

	@Autowired
	private OrderGenerateService generateService;
	
	@GetMapping("get/{type}")
	public Result getOrderNo(@PathVariable("type") String type) {
		return ResultUtil.success(generateService.getOrder(type));
	}
	
}
