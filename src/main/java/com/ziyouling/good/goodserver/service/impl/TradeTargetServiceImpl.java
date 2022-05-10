package com.ziyouling.good.goodserver.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ziyouling.good.goodserver.repositories.MarketTradeTargetRepository;
import com.ziyouling.good.goodserver.repositories.PersonRepository;
import com.ziyouling.good.goodserver.repositories.CompanyRepository;
import com.ziyouling.good.goodserver.service.ITradeTargetService;
import com.ziyouling.good.goodserver.vo.bounds.TradeTargetCreateReq;
import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;
import com.ziyouling.good.goodserver.vo.entity.Person;
import com.ziyouling.good.goodserver.vo.entity.Company;

@Service
public class TradeTargetServiceImpl implements ITradeTargetService {

	@Autowired
	private CompanyRepository targets;
	
	@Autowired
	private PersonRepository persons;
	
	
	@Autowired
	private MarketTradeTargetRepository marketTradeTargets;
	
	@Transactional
	@Override
	public synchronized Company create(TradeTargetCreateReq req, StringBuffer error) {
		if(!StringUtils.hasText(req.getNickname()))
		{
			error.append("简称不能为空" );
			return null;
		}
		Company target = targets.findByNickname(req.getNickname());
		if(target != null)
		{
			error.append("简称重复拉" );
			return null;
		}
		target = new Company();
		targets.save(target);
		updateTarget(target, req, error);
		return target;
	}

	@Override
	public boolean bindTo(long org, long marketTargetid, StringBuffer error) {
		Optional<MarketTradeTarget> optional = marketTradeTargets.findById(marketTargetid);
		if(!optional.isPresent())
		{
			error.append("找不到marketTargetid对应的标的");
			return false;
		}
		Optional<Company> optional2 = targets.findById(org);
		if(!optional2.isPresent())
		{
			error.append("找不到对应的企业");
			return false;
		}
		MarketTradeTarget mtt = optional.get();
		mtt.setTarget(optional2.get());
		marketTradeTargets.save(mtt);
		return true;
	}
	
	
	private void updateTarget(Company target, TradeTargetCreateReq req, StringBuffer error)
	{
		target.setNickname(req.getNickname());
		target.setName(req.getName());
		target.setDescription(req.getDescription());
		target.setPhone(req.getPhone());
		target.setAddress(req.getAddress());
		target.setWebsite(req.getWebsite());
		
		if(StringUtils.hasText(req.getLeader()))
		{
//			String name = req.getLeader().trim();
//			
//			Person person = persons.findByName(name);
//			if(person == null)
//			{
//				person = new Person();
//				person.setName(name);;
//				persons.save(person);
//			}
//			target.setLeader(person);
			//TODO 建立高管库
		}
		//建立和行业标签的关联，
		if(req.getTags() != null)
		{
			
		}
		targets.save(target);
	}

}
