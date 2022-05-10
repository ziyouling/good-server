package com.ziyouling.good.goodserver.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyouling.good.goodserver.repositories.MarketRepository;
import com.ziyouling.good.goodserver.repositories.MarketTradeTargetRepository;
import com.ziyouling.good.goodserver.repositories.MinRoeTargetRepository;
import com.ziyouling.good.goodserver.service.IMarketDataService;
import com.ziyouling.good.goodserver.service.IMarketTradeTargetService;
import com.ziyouling.good.goodserver.vo.NameAndCode;
import com.ziyouling.good.goodserver.vo.TypedRespond;
import com.ziyouling.good.goodserver.vo.entity.Market;
import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;
import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;
import com.ziyouling.good.goodserver.vo.service.FinanceIndicator;

@Service
public class MarketTradeTargetService implements IMarketTradeTargetService{

	@Autowired
	private IMarketDataService marketData;
	
	@Autowired
	private MarketRepository markets;
	
	@Autowired
	private MarketTradeTargetRepository marketTradeTargets;
	
	@Autowired
	private MinRoeTargetRepository minRoeTarget;
	
	@Override
	public synchronized TypedRespond<Integer> updateAllMarketTradeTargets() {
		TypedRespond<Integer> result = new TypedRespond<Integer> ();
		Iterator<Market> it = markets.findAll().iterator();
		int count = 0;
		while(it.hasNext())
		{
			Market m = it.next();
			String prefix = m.getCodePrefix();
			try
			{
				List<NameAndCode>  targets = marketData.loadAllTargets(prefix);
				for(NameAndCode item : targets)
				{
					MarketTradeTarget mtt = marketTradeTargets.findByMarketAndCode(m, item.getCode());
					if(mtt == null)
					{
						mtt = new MarketTradeTarget();
						mtt.setMarket(m);
						mtt.setCode(item.getCode());
					}
					mtt.setName(item.getName());
					marketTradeTargets.save(mtt);
					count++;
					System.out.println("save market trade target:" + item.getName() + "(" + item.getCode() + ")");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		result.setResult(count);
		return result;
	}

	@Override
	public synchronized TypedRespond<Integer> updateAllMarketTradeTargetsRoeSat(int year) {
		TypedRespond<Integer> result = new TypedRespond<Integer> ();
		Iterator<MarketTradeTarget> it = marketTradeTargets.findAll().iterator();
		int total = 0;
		int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
		int latestYear =yearCurrent-2;
		while(it.hasNext())
		{
			try
			{
				MarketTradeTarget m = it.next();
				int yearcurrent = year;
				List<FinanceIndicator> indicators =  marketData.loadFinanceIndicator(m.getMarket().getCodePrefix(), m.getCode());
				//1，少于7年的忽略。
				if(indicators == null || indicators.size() < yearcurrent || indicators.size() < 7)
				{
					continue;
				}
				//2,退市的忽略，最近没有财报的
				FinanceIndicator latest = indicators.get(0);
				if(latest.getReportYear() < latestYear )
				{
					continue;
				}
				//上市少于3年的。
				Date listDate = marketData.getListDate(m.getMarket().getCodePrefix(), m.getCode());
				if(listDate == null)
				{
					continue;
				}
				if(listDate.getYear() + 1900 > yearCurrent - 3)
				{
					continue;
				}
				double minRoe = Double.MAX_VALUE;
				double avgRoe = 0;
				double highRoe = 0;
				int count = 0;
				while(--yearcurrent >= 0)
				{
					FinanceIndicator one = indicators.get(yearcurrent);
					double roe = one.getRoe();
					highRoe = Math.max(highRoe, roe);
					minRoe = Math.min(minRoe, roe);
					avgRoe += roe;
					count++;
				}
				avgRoe /= count;
				
				MinRoeTarget target = minRoeTarget.findByTarget(m);
				if(target == null)
				{
					target = new MinRoeTarget();
					target.setTarget(m);
				}
				target.setAvgRoe(avgRoe);
				target.setHighRoe(highRoe);
				target.setMinRoe(minRoe);
				target.setAvgRoe(avgRoe);
				target.setBegin(indicators.get(count-1).getReportDate());
				target.setEnd(indicators.get(0).getReportDate());
				target.setDurationYear(count);
				
				minRoeTarget.save(target);
				System.out.println("save min roe sat:" + m.getName() + "(" + m.getCode() + ")");
				total++;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		result.setResult(total);
		return result;
	}

}
