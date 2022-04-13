package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.Market;
import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;

public interface MarketTradeTargetRepository extends CrudRepository<MarketTradeTarget, Long> {
	MarketTradeTarget findByMarketAndCode(Market market, String code);
}
