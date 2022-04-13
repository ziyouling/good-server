package com.ziyouling.good.goodserver.vo.service;

import java.util.Date;

import com.ziyouling.good.goodserver.vo.entity.ReportType;

public class FinanceIndicator {
	private double roe;
	private ReportType reportType;
	
	private Date reportDate;
	private int reportYear;
	public double getRoe() {
		return roe;
	}
	public void setRoe(double roe) {
		this.roe = roe;
	}
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public int getReportYear() {
		return reportYear;
	}
	public void setReportYear(int reportYear) {
		this.reportYear = reportYear;
	}
	
	
	
}
