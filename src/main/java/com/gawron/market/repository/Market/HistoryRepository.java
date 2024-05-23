package com.gawron.market.repository.Market;

import com.gawron.market.model.Market.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findAllBySellerServerAndSellerID(String sellerServer, int sellerID);
    List<History> findAllByBuyerServerAndBuyerID(String buyerServer, int buyerID);
}
