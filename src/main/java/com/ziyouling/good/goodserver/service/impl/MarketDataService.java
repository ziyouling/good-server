package com.ziyouling.good.goodserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziyouling.good.goodserver.service.IMarketDataService;
import com.ziyouling.good.goodserver.vo.NameAndCode;

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
	
	private String  getTargetsUrl(int maxCount, String market)
	{
		String marketCode=market.toLowerCase().equals("sh") ? "m:1+t:2,m:1+t:23" : "m:0+t:6,m:0+t:80";
		String url = "http://29.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112405327984867230366_1649635281498&pn=1&pz=" + maxCount +
				"&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=" + marketCode + "&fields=f12,f14&_=" + System.currentTimeMillis();
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

}
