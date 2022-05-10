package com.ziyouling.good.goodserver.vo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 公司所在的行业标签
* @author jiangjiang.li
*
*/
@Entity
public class CompanyTag {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private BusinessTag tag;
	
	@ManyToOne
	private Company company;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BusinessTag getTag() {
		return tag;
	}

	public void setTag(BusinessTag tag) {
		this.tag = tag;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
