package com.ziyouling.good.goodserver.vo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *   交易所
* @author jiangjiang.li
*
*/

@Entity
public class Market {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	
	private String codePrefix;


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


	public String getCodePrefix() {
		return codePrefix;
	}


	public void setCodePrefix(String codePrefix) {
		this.codePrefix = codePrefix;
	}
}
