package com.ziyouling.good.goodserver.service;

import java.util.List;

import com.ziyouling.good.goodserver.vo.NameAndCode;
import com.ziyouling.good.goodserver.vo.service.FinanceIndicator;

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
	
	/**
	 *      获取最近几年的财务指标，倒叙排列
	 * @param market
	 * @param code
	 * @return
	 */
	List<FinanceIndicator> loadFinanceIndicator(String market, String code);
}
