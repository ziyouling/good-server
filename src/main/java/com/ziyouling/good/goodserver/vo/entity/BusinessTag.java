package com.ziyouling.good.goodserver.vo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
    * 行业标签
 * @author jiangjiang.li
 *
 */
@Entity
public class BusinessTag {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	/**
	 * 周热度排名
	 */
	private int weekHotOrder;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 周热度排名
	 */
	public int getWeekHotOrder() {
		return weekHotOrder;
	}

	public void setWeekHotOrder(int weekHotOrder) {
		this.weekHotOrder = weekHotOrder;
	}
	
	
}
