package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;
import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;

public interface MinRoeTargetRepository extends CrudRepository<MinRoeTarget, Long> {
	MinRoeTarget findByTarget(MarketTradeTarget target);
}
