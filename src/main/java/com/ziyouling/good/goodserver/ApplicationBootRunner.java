package com.ziyouling.good.goodserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ziyouling.good.goodserver.repositories.MarketRepository;
import com.ziyouling.good.goodserver.vo.entity.Market;
@Component
public class ApplicationBootRunner implements ApplicationRunner {

	@Autowired
	private MarketRepository markets;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//初始化市场
		if(markets.count() <= 0)
		{
			Market market = new Market();
			market.setName("上海证券交易所");
			market.setCodePrefix("sh");
			
			markets.save(market);
			
			market = new Market();
			market.setName("深圳证券交易所");
			market.setCodePrefix("sz");
			
			markets.save(market);
		}
	}

}
