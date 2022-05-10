package com.ziyouling.good.goodserver.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ziyouling.good.goodserver.repositories.MinRoeTargetRepository;
import com.ziyouling.good.goodserver.vo.bounds.MinRoeWebTarget;
import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;

@Controller
public class MarketCodeWebController {
	
	
	@Autowired
	private MinRoeTargetRepository minRoeTargets;
	
	/**
	   *    获取最近满足条件的标的
	 * @param model
	 * @return
	 */
	@GetMapping("/market_targets")
	private String listTargets(Model model)
	{
		Date reportDate = new Date();
		reportDate.setYear(reportDate.getYear() -2);
		List<MinRoeTarget> list = minRoeTargets.findAllByDurationYearGreaterThanAndEndAfterAndMinRoeGreaterThanEqualOrderByAvgRoeDesc(2, reportDate, 15);
		
		List<MinRoeWebTarget> resultList = new ArrayList<MinRoeWebTarget>();
		for(MinRoeTarget item : list)
		{
			double roeHigh = item.getHighRoe();
			double roeMin = item.getMinRoe();
			if(roeHigh >= roeMin*3)
			{
				continue;
			}
			resultList.add(new MinRoeWebTarget(item));
		}
		model.addAttribute("items", resultList);
		return "market_targets";
	}
	
}
