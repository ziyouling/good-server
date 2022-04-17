package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.TradeTarget;

public interface TradeTargetRepository extends CrudRepository<TradeTarget, Long> {
	TradeTarget findByNickname(String nickName);
}
