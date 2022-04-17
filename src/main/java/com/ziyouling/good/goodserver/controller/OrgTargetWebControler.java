package com.ziyouling.good.goodserver.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ziyouling.good.goodserver.repositories.MarketTradeTargetRepository;
import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;

/**
    * 公司
 * @author jiangjiang.li
 *
 */
@Controller
public class OrgTargetWebControler {
	
	@Autowired
	private MarketTradeTargetRepository marketTradeTargets;
	/**
	   *    获取最近满足条件的标的
	 * @param model
	 * @return
	 */
	@GetMapping("/create_org_target")
	private String listTargets(Long bind, Model model)
	{
		MarketTradeTarget mtt = null;
		if(bind != null)
		{
			Optional<MarketTradeTarget> optional = marketTradeTargets.findById(bind);
			if(optional.isPresent())
			{
				mtt = optional.get();
			}
		}
		model.addAttribute("bind_id", bind);
		model.addAttribute("nickname", mtt.getName());
		return "create_org_target";
	}
}
