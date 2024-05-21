package com.gawron.market.repository.Market;

import com.gawron.market.model.Market.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Integer> {
    List<Market> findAllByItemOwnerAndItemServer(int itemOwner, String itemServer);
}
