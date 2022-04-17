package com.ziyouling.good.goodserver.service;

import com.ziyouling.good.goodserver.vo.bounds.TradeTargetCreateReq;
import com.ziyouling.good.goodserver.vo.entity.TradeTarget;

public interface ITradeTargetService {
	/**
	    *  创建企业，如果已存在，就返回null，表示创建失败
	 * @param req
	 * @return
	 */
	TradeTarget create(TradeTargetCreateReq req, StringBuffer error);
	
	boolean bindTo(long org, long marketTargetid, StringBuffer error);
}
