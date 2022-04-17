package com.ziyouling.good.goodserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ziyouling.good.goodserver.service.ITradeTargetService;
import com.ziyouling.good.goodserver.vo.Respond;
import com.ziyouling.good.goodserver.vo.bounds.TradeTargetCreateReq;
import com.ziyouling.good.goodserver.vo.entity.TradeTarget;


@RestController
public class OrgTargetControler {
	@Autowired
	private ITradeTargetService service;
	
	/**
	   *    创建企业
	 */
	@PostMapping("/api/org_target_new")
	private Respond createOneOrgTarget(@RequestBody TradeTargetCreateReq req)
	{
		Respond respond = new Respond();
		StringBuffer error = new StringBuffer();
		TradeTarget target = service.create(req, error);
		if(target == null)
		{
			respond.setCode(-1);
			respond.setMsg(error.toString());
		}else
		{
			respond.setResult(target.getId());
		}
		return respond;
	}
	
	/**
	   *    创建企业
	 */
	@PostMapping("/api/bind_target_market_org")
	private Respond bindTarget(Long market, Long org)
	{
		Respond respond = new Respond();
		StringBuffer error = new StringBuffer();
		boolean success = service.bindTo(org, market, error);
		if(!success)
		{
			respond.setCode(-1);
			respond.setMsg(error.toString());
		}
		return respond;
	}
}
