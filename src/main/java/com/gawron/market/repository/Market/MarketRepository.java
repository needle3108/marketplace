package com.gawron.market.repository.Market;

import com.gawron.market.model.Market.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
