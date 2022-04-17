package com.ziyouling.good.goodserver.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.MarketTradeTarget;
import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;

public interface MinRoeTargetRepository extends CrudRepository<MinRoeTarget, Long> {
	MinRoeTarget findByTarget(MarketTradeTarget target);
	
	
	List<MinRoeTarget> findAllByDurationYearGreaterThanAndEndAfterAndMinRoeGreaterThanEqualOrderByAvgRoeDesc(int durationYear, Date end, double roe);
}
