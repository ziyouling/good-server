package com.ziyouling.good.goodserver.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ziyouling.good.goodserver.repositories.BusinessTagRepository;
import com.ziyouling.good.goodserver.repositories.CompanyRepository;
import com.ziyouling.good.goodserver.service.ITradeTargetService;
import com.ziyouling.good.goodserver.vo.Respond;
import com.ziyouling.good.goodserver.vo.bounds.TradeTargetCreateReq;
import com.ziyouling.good.goodserver.vo.entity.BusinessTag;
import com.ziyouling.good.goodserver.vo.entity.Company;


@RestController
@RequestMapping("/api")
public class CompanyControler {
	@Autowired
	private ITradeTargetService service;
	
	@Autowired
	private CompanyRepository tradeTargets;
	
	@Autowired
	private BusinessTagRepository businessTags;
	
	
	/**
	   *    创建企业
	 */
	@PostMapping("org_target_new")
	private Respond createOneOrgTarget(@RequestBody TradeTargetCreateReq req)
	{
		Respond respond = new Respond();
		StringBuffer error = new StringBuffer();
		Company target = service.create(req, error);
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
	@PostMapping("org_target_update")
	private Respond updateOneOrgTarget(@RequestBody TradeTargetCreateReq req)
	{
		Respond respond = new Respond();
		StringBuffer error = new StringBuffer();
		Company target = service.create(req, error);
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
	@PostMapping("bind_target_market_org")
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
	
	/**
	   *    创建企业
	 */
	@GetMapping("get_org_target")
	private Respond getTarget( Long id)
	{
		Respond respond = new Respond();
		Optional<Company> optional = tradeTargets.findById(id);
		respond.setResult(optional.isPresent() ? optional.get() : null);
		return respond;
	}
	
	
	/**
	  *返回行业标签，默认按照周热度排名
	 */
	@GetMapping("list_org_tags")
	private Respond listTags(int count)
	{
		Respond respond = new Respond();
		PageRequest request = PageRequest.of(0, count, Sort.by(Direction.DESC,"weekHotOrder"));
		Page<BusinessTag> page = businessTags.findAll(request);
		respond.setResult(page.getContent());
		return respond;
	}
	
	/**
	  * 过滤，获取行业标签
	 */
	@GetMapping("filter_org_tags")
	private Respond filterTags(String name, int count)
	{
		Respond respond = new Respond();
		PageRequest request = PageRequest.of(0, count, Sort.by(Direction.DESC,"weekHotOrder"));
		Page<BusinessTag> page = businessTags.findAllByNameStartsWith(name, request);
		respond.setResult(page.getContent());
		return respond;
	}
	
	/**
	 * 添加tag
	 */
	@PostMapping("add_org_tag")
	private Respond addTag(String name)
	{
		Respond respond = new Respond();
		if(StringUtils.hasText(name))
		{
			respond.setCode(-1);
			respond.setMsg("tag不能为空!");
			return respond;
		}
		BusinessTag tag = businessTags.findByName(name);
		if(tag != null)
		{
			tag = new BusinessTag();
			tag.setName(name);
			businessTags.save(tag);
		}
		respond.setResult(tag);
		return respond;
	}
}
