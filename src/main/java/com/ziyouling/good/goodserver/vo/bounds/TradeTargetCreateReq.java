package com.ziyouling.good.goodserver.vo.bounds;

public class TradeTargetCreateReq {
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
	
	private String description;
	
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

	private String leader;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
}
