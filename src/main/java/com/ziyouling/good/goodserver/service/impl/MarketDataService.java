package com.ziyouling.good.goodserver.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ziyouling.good.goodserver.service.IMarketDataService;
import com.ziyouling.good.goodserver.vo.NameAndCode;
import com.ziyouling.good.goodserver.vo.entity.ReportType;
import com.ziyouling.good.goodserver.vo.service.FinanceIndicator;

@Service
public class MarketDataService implements IMarketDataService {

	@Override
	public List<NameAndCode> loadAllTargets(String market) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.getForEntity(getTargetsUrl(100000, market), String.class);
		String result = response.getBody();
		int index = result.indexOf('[');
		result = result.substring(index,result.length() - 4);
		Gson gson = new Gson();
		List<fNameAndCode> list = new ArrayList<fNameAndCode>();
		list = gson.fromJson(result, new TypeToken<List<fNameAndCode>>() {}.getType());
		list.sort(( p1,p2)-> p1.getF12().compareTo(p2.getF12()));
		
		List<NameAndCode> resultList = new ArrayList<NameAndCode>();
		for(fNameAndCode f : list)
		{
			if(f.f14 == null)
			{
				continue;
			}
			if(f.f14.toLowerCase().startsWith("pt"))
			{
				continue;
			}
			resultList.add(new NameAndCode(f.f12, f.f14));
		}
		return resultList;
	}
	@Override
	public List<FinanceIndicator> loadFinanceIndicator(String market, String code) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.getForEntity(getFinanceIndicatorUrl(market, code), String.class);
		String result = response.getBody();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		FinanceIndicatorResult fir = gson.fromJson(result, FinanceIndicatorResult.class);
		List<FinanceIndicator> resultList = new ArrayList<FinanceIndicator>();
		if(fir != null && fir.data != null)
		{
			for(FinanceIndicatorOne one : fir.data)
			{
				FinanceIndicator indicator = new FinanceIndicator();
				indicator.setReportDate(one.getREPORT_DATE());
				indicator.setReportType(ReportType.YEAR);
				indicator.setReportYear(Integer.parseInt((one.getREPORT_YEAR())));
				indicator.setRoe(one.getROEJQ());
				resultList.add(indicator);
			}
		}
		return resultList;
	}
	

	@Override
	public Date getListDate(String market, String code) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.getForEntity(getCompanyInfoUrl(market, code), String.class);
		String result = response.getBody();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		CompanyInfo info = gson.fromJson(result, CompanyInfo.class);
		if(info == null ||info.getFxxg() == null || info.getFxxg().size() < 1)
		{
			return null;
		}
		return info.getFxxg().get(0).getLISTING_DATE();
	}
	
	
	private String  getTargetsUrl(int maxCount, String market)
	{
		String marketCode=market.toLowerCase().equals("sh") ? "m:1+t:2,m:1+t:23" : "m:0+t:6,m:0+t:80";
		String url = "http://29.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112405327984867230366_1649635281498&pn=1&pz=" + maxCount +
				"&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=" + marketCode + "&fields=f12,f14&_=" + System.currentTimeMillis();
		return url;
	}
	
	private String  getFinanceIndicatorUrl(String market, String code)
	{
		String url = "https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/ZYZBAjaxNew?type=1&code=" + market.toUpperCase()  + code;
		return url;
	}
	
	private String getCompanyInfoUrl(String market, String code)
	{
		String url = "https://emweb.eastmoney.com/PC_HSF10/CompanySurvey/PageAjax?code=" + market.toUpperCase()  + code;
		return url;
	}
	
	private static class fNameAndCode
	{
		private String f12;
		private String f14;
		public String getF12() {
			return f12;
		}
		public void setF12(String f12) {
			this.f12 = f12;
		}
		public String getF14() {
			return f14;
		}
		public void setF14(String f14) {
			this.f14 = f14;
		}
	}

	
	private static class FinanceIndicatorResult
	{
		private int pages;
		private int count;
		private List<FinanceIndicatorOne> data;
		public int getPages() {
			return pages;
		}
		public void setPages(int pages) {
			this.pages = pages;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public List<FinanceIndicatorOne> getData() {
			return data;
		}
		public void setData(List<FinanceIndicatorOne> data) {
			this.data = data;
		}
	}
	
	private static class FinanceIndicatorOne
	{
		private double ROEJQ;
		private double ROEKCJQ;
		private String REPORT_TYPE;
		private String REPORT_YEAR;
		
		private Date REPORT_DATE;

		public double getROEJQ() {
			return ROEJQ;
		}

		public void setROEJQ(double rOEJQ) {
			ROEJQ = rOEJQ;
		}

		public double getROEKCJQ() {
			return ROEKCJQ;
		}

		public void setROEKCJQ(double rOEKCJQ) {
			ROEKCJQ = rOEKCJQ;
		}

		public String getREPORT_TYPE() {
			return REPORT_TYPE;
		}

		public void setREPORT_TYPE(String rEPORT_TYPE) {
			REPORT_TYPE = rEPORT_TYPE;
		}

		public String getREPORT_YEAR() {
			return REPORT_YEAR;
		}

		public void setREPORT_YEAR(String rEPORT_YEAR) {
			REPORT_YEAR = rEPORT_YEAR;
		}

		public Date getREPORT_DATE() {
			return REPORT_DATE;
		}

		public void setREPORT_DATE(Date rEPORT_DATE) {
			REPORT_DATE = rEPORT_DATE;
		}
	}
	
	private static class CompanyInfo
	{
		//基本资料
		private List<CompanyDesc> jbzl;
		//发行相关
		private List<CompanyMarketDesc> fxxg;
		public List<CompanyDesc> getJbzl() {
			return jbzl;
		}
		public void setJbzl(List<CompanyDesc> jbzl) {
			this.jbzl = jbzl;
		}
		public List<CompanyMarketDesc> getFxxg() {
			return fxxg;
		}
		public void setFxxg(List<CompanyMarketDesc> fxxg) {
			this.fxxg = fxxg;
		}
		
		
	}
	
	private static class CompanyDesc
	{
		private String STR_NAMEA;
		private String CHAIRMAN;
		private String ADDRESS;
		private String EM2016;
		public String getSTR_NAMEA() {
			return STR_NAMEA;
		}
		public void setSTR_NAMEA(String sTR_NAMEA) {
			STR_NAMEA = sTR_NAMEA;
		}
		public String getCHAIRMAN() {
			return CHAIRMAN;
		}
		public void setCHAIRMAN(String cHAIRMAN) {
			CHAIRMAN = cHAIRMAN;
		}
		public String getADDRESS() {
			return ADDRESS;
		}
		public void setADDRESS(String aDDRESS) {
			ADDRESS = aDDRESS;
		}
		public String getEM2016() {
			return EM2016;
		}
		public void setEM2016(String eM2016) {
			EM2016 = eM2016;
		}
		
	}
	
	private static class CompanyMarketDesc
	{
		private Date LISTING_DATE;
		private Date FOUND_DATE;
		private long TOTAL_FUNDS;
		private long TOTAL_ISSUE_NUM;
		public Date getLISTING_DATE() {
			return LISTING_DATE;
		}
		public void setLISTING_DATE(Date lISTING_DATE) {
			LISTING_DATE = lISTING_DATE;
		}
		public Date getFOUND_DATE() {
			return FOUND_DATE;
		}
		public void setFOUND_DATE(Date fOUND_DATE) {
			FOUND_DATE = fOUND_DATE;
		}
		public long getTOTAL_FUNDS() {
			return TOTAL_FUNDS;
		}
		public void setTOTAL_FUNDS(long tOTAL_FUNDS) {
			TOTAL_FUNDS = tOTAL_FUNDS;
		}
		public long getTOTAL_ISSUE_NUM() {
			return TOTAL_ISSUE_NUM;
		}
		public void setTOTAL_ISSUE_NUM(long tOTAL_ISSUE_NUM) {
			TOTAL_ISSUE_NUM = tOTAL_ISSUE_NUM;
		}
	}

}
