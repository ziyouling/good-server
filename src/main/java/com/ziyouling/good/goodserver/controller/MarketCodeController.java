package com.ziyouling.good.goodserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyouling.good.goodserver.service.IMarketDataService;
import com.ziyouling.good.goodserver.vo.Respond;

@RestController
@RequestMapping("/market")
public class MarketCodeController {

	@Autowired
	private IMarketDataService marketData;
	
	/**
	    * 获取所在市场的交易标的
	 * @param market
	 * @return
	 */
	@GetMapping("/load_targets")
	private Respond loadTargets(String market)
	{
		Respond respond = new Respond();
		respond.setResult(marketData.loadAllTargets(market));
		return respond;
	}
}
