package com.ziyouling.good.goodserver.vo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *   交易所
* @author jiangjiang.li
*
*/

@Entity
public class MinRoeTarget {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
	private MarketTradeTarget target;

	private double minRoe;
	
	private double avgRoe;
	
	private double highRoe;
	
	private Date begin;
	
	private Date end;
	
	private int durationYear;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MarketTradeTarget getTarget() {
		return target;
	}

	public void setTarget(MarketTradeTarget target) {
		this.target = target;
	}

	public double getMinRoe() {
		return minRoe;
	}

	public void setMinRoe(double minRoe) {
		this.minRoe = minRoe;
	}

	public double getAvgRoe() {
		return avgRoe;
	}

	public void setAvgRoe(double avgRoe) {
		this.avgRoe = avgRoe;
	}

	public double getHighRoe() {
		return highRoe;
	}

	public void setHighRoe(double highRoe) {
		this.highRoe = highRoe;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getDurationYear() {
		return durationYear;
	}

	public void setDurationYear(int durationYear) {
		this.durationYear = durationYear;
	}
}
