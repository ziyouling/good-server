package com.ziyouling.good.goodserver.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyouling.good.goodserver.service.IMarketDataService;
import com.ziyouling.good.goodserver.service.IMarketTradeTargetService;
import com.ziyouling.good.goodserver.vo.NameAndCode;
import com.ziyouling.good.goodserver.vo.Respond;
import com.ziyouling.good.goodserver.vo.TypedRespond;
import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;
import com.ziyouling.good.goodserver.vo.service.FinanceIndicator;

@RestController
@RequestMapping("/market")
public class MarketCodeController {

	@Autowired
	private IMarketDataService marketData;
	
	@Autowired
	private IMarketTradeTargetService marketTradeTargetService;
	
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
	
	
	@GetMapping("/roes")
	private Respond loadTargets(String market, String code)
	{
		Respond respond = new Respond();
		respond.setResult(marketData.loadFinanceIndicator(market, code));
		return respond;
	}
	
	
	@GetMapping("/list_targets_min_roe")
	private Respond listTargets(double roe)
	{
		Respond respond = new Respond();
		int year = 3;
		List<MinRoeTarget> resultList = new ArrayList<MinRoeTarget>();
		
		//sz
		addMinRoeTargets(resultList, "sz", year, roe);
		//sh
		addMinRoeTargets(resultList, "sh", year, roe);
		System.out.println("list_targets_min_roe: " +roe + " size:" + resultList.size());
		respond.setResult(resultList);
		return respond;
	}
	
	@GetMapping("/update_targets_roe_sat")
	private Respond updateTargets()
	{
		Respond respond = new Respond();
		TypedRespond<Integer> typed1 = marketTradeTargetService.updateAllMarketTradeTargets();
		TypedRespond<Integer> typed2 = marketTradeTargetService.updateAllMarketTradeTargetsRoeSat(3);
		respond.setResult("标的个数： " + typed1.getResult() + "统计个数 :" + typed2.getResult());
		return respond;
	}
	
	private void addMinRoeTargets(List<MinRoeTarget> resultList, String market, int year, double minRoe)
	{
		List<NameAndCode>  targets = marketData.loadAllTargets(market);
		for(NameAndCode item : targets)
		{
			MinRoeTarget target = createOneMinRoeTarget(market, item, year);
			if(target == null)
			{
				continue;
			}
			if(target.getMinRoe() < minRoe)
			{
				continue;
			}
			resultList.add(target);
		}
	}
	
	private MinRoeTarget createOneMinRoeTarget(String market, NameAndCode code, int year)
	{
		System.out.println("load fiance indicator:" + code.getName() + "(" + code.getCode() + ")");
		List<FinanceIndicator> indicators = marketData.loadFinanceIndicator(market, code.getCode());
		//1，少于7年的忽略。
		if(indicators == null || indicators.size() < year || indicators.size() < 7)
		{
			return null;
		}
		//2,退市的忽略，最近没有财报的
		int todayYear =Calendar.getInstance().get(Calendar.YEAR)-2;
		FinanceIndicator latest = indicators.get(0);
		if(latest.getReportYear() < todayYear )
		{
			return null;
		}
		double minRoe = Double.MAX_VALUE;
		double avgRoe = 0;
		double highRoe = 0;
		int count = 0;
		while(--year >= 0)
		{
			FinanceIndicator one = indicators.get(year);
			double roe = one.getRoe();
			highRoe = Math.max(highRoe, roe);
			minRoe = Math.min(minRoe, roe);
			avgRoe += roe;
			count++;
		}
		avgRoe /= count;
		
		MinRoeTarget target = new MinRoeTarget();
		target.setAvgRoe(avgRoe);
		target.setHighRoe(highRoe);
		target.setMinRoe(minRoe);
		target.setAvgRoe(avgRoe);
		return target;
	}
}
