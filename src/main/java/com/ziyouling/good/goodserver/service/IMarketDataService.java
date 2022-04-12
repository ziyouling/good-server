package com.ziyouling.good.goodserver.service;

import java.util.List;

import com.ziyouling.good.goodserver.vo.NameAndCode;

/**
   * 市场数据
 * @author jiangjiang.li
 *
 */
public interface IMarketDataService {
	/**
	     * 获取市场内所有交易标的
	 * @param market
	 * @return
	 */
	List<NameAndCode> loadAllTargets(String market);
}
