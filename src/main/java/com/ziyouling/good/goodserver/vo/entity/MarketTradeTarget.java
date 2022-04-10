package com.ziyouling.good.goodserver.vo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *   市场交易标的
* @author jiangjiang.li
*
*/

@Entity
public class MarketTradeTarget {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Market market;
	
	@ManyToOne
	private TradeTarget target;
	
	/**
	 * 代码
	 */
	private String code;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public TradeTarget getTarget() {
		return target;
	}

	public void setTarget(TradeTarget target) {
		this.target = target;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
