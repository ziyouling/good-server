package com.ziyouling.good.goodserver.service;

import com.ziyouling.good.goodserver.vo.TypedRespond;

public interface IMarketTradeTargetService {
	/**
	   *  加载A股所有的交易标的，返回总数
	 */
	TypedRespond<Integer> updateAllMarketTradeTargets();
	
	/**
	   *  加载A股所有的交易标的roe统计，返回总数
	   *  
	   * @param year。最近年限的统计
	 */
	TypedRespond<Integer> updateAllMarketTradeTargetsRoeSat(int year);
}
