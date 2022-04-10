package com.ziyouling.good.goodserver.vo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
  *  交易标的，主要是企业
 * @author jiangjiang.li
 *
 */

@Entity
public class TradeTarget {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	/**
	 * 简称
	 */
	private String nickname;
	
	
	/**
	 * 全称
	 */
	private String name;
	
	
	/**
	 * 简介
	 */
	@Column(length = 512)
	private String desc;
	
	/**
	 * 官网
	 */
	private String website;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 办公地址
	 */
	private String address;
	
	/**
	 * 现任领导
	 */
	private Person leader;
	
	/**
	 * 评分
	 */
	private double score;
	
	/**
	 * 盈利性
	 */
	private double profitScore;
	
	/**
	 * 护城河
	 */
	private double moatScore;
	
	/**
	 * 持久性
	 */
	private double duraScore;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person getLeader() {
		return leader;
	}

	public void setLeader(Person leader) {
		this.leader = leader;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getProfitScore() {
		return profitScore;
	}

	public void setProfitScore(double profitScore) {
		this.profitScore = profitScore;
	}

	public double getMoatScore() {
		return moatScore;
	}

	public void setMoatScore(double moatScore) {
		this.moatScore = moatScore;
	}

	public double getDuraScore() {
		return duraScore;
	}

	public void setDuraScore(double duraScore) {
		this.duraScore = duraScore;
	}
}
