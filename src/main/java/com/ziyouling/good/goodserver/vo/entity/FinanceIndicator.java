package com.ziyouling.good.goodserver.vo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
    * 财务指标
 * @author jiangjiang.li
 *
 */

@Entity
public class FinanceIndicator {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
	private MarketTradeTarget target;
	
	/**
	 * 报告日期
	 */
	private Date reportDate;
	
	/**
	 * 报告类型
	 */
	private ReportType reportType;
	
	private double roe;

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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public double getRoe() {
		return roe;
	}

	public void setRoe(double roe) {
		this.roe = roe;
	}
	
}
