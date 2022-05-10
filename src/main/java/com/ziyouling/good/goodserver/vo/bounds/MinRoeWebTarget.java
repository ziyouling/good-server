package com.ziyouling.good.goodserver.vo.bounds;

import java.util.Date;

import com.ziyouling.good.goodserver.vo.entity.MinRoeTarget;
import com.ziyouling.good.goodserver.vo.entity.Company;

public class MinRoeWebTarget {
	
	private long id;
	private String name;
	private String code;
	private String market;
	private double roeLow;
	private double roeHigh;
	private double roeAvg;
	
	private Date endDate;
	private int durationYear;
	
	private String organizationName ;
	private long organizationId;
	public MinRoeWebTarget()
	{
		
	}
	
	public MinRoeWebTarget(MinRoeTarget source)
	{
		this.id = source.getTarget().getId();
		this.name = source.getTarget().getName();
		this.code = source.getTarget().getCode();
		
		this.market = source.getTarget().getMarket().getCodePrefix();
		
		this.roeLow = source.getMinRoe();
		this.roeHigh = source.getHighRoe();
		this.roeAvg = source.getAvgRoe();
		
		this.endDate = source.getEnd();
		this.durationYear = source.getDurationYear();
		
		Company trade = source.getTarget().getTarget();;
		if(trade != null)
		{
			this.organizationName = trade.getName();
			this.organizationId = trade.getId();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public double getRoeLow() {
		return roeLow;
	}
	public void setRoeLow(double roeLow) {
		this.roeLow = roeLow;
	}
	public double getRoeHigh() {
		return roeHigh;
	}
	public void setRoeHigh(double roeHigh) {
		this.roeHigh = roeHigh;
	}
	public double getRoeAvg() {
		return roeAvg;
	}
	public void setRoeAvg(double roeAvg) {
		this.roeAvg = roeAvg;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getDurationYear() {
		return durationYear;
	}
	public void setDurationYear(int durationYear) {
		this.durationYear = durationYear;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
