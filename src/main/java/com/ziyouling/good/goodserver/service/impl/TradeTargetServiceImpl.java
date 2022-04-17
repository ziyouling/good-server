package com.ziyouling.good.goodserver.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ziyouling.good.goodserver.repositories.MarketTradeTargetRepository;
import com.ziyouling.good.goodserver.repositories.PersonRepository;
import com.ziyouling.good.goodserver.repositories.TradeTargetRepository;
import com.ziyouling.good.goodserver.service.ITradeTargetService;
import com.ziyouling.good.goodserver.vo.bounds.TradeTargetCreateReq;
import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;
import com.ziyouling.good.goodserver.vo.entity.Person;
import com.ziyouling.good.goodserver.vo.entity.TradeTarget;

@Service
public class TradeTargetServiceImpl implements ITradeTargetService {

	@Autowired
	private TradeTargetRepository targets;
	
	@Autowired
	private PersonRepository persons;
	
	
	@Autowired
	private MarketTradeTargetRepository marketTradeTargets;
	
	@Transactional
	@Override
	public synchronized TradeTarget create(TradeTargetCreateReq req, StringBuffer error) {
		if(!StringUtils.hasText(req.getNickname()))
		{
			error.append("简称不能为空" );
			return null;
		}
		TradeTarget target = targets.findByNickname(req.getNickname());
		if(target != null)
		{
			error.append("简称重复拉" );
			return null;
		}
		target = new TradeTarget();
		target.setNickname(req.getNickname());
		target.setName(req.getName());
		target.setDescription(req.getDescription());
		target.setPhone(req.getPhone());
		target.setAddress(req.getAddress());
		target.setWebsite(req.getAddress());
		
		if(StringUtils.hasText(req.getLeader()))
		{
			String name = req.getLeader().trim();
			
			Person person = persons.findByName(name);
			if(person == null)
			{
				person = new Person();
				person.setName(name);;
				persons.save(person);
			}
			target.setLeader(person);
		}
		targets.save(target);
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
		Optional<TradeTarget> optional2 = targets.findById(org);
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

}
