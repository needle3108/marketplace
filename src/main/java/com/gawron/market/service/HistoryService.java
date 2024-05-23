package com.gawron.market.service;

import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.model.Market.History;
import com.gawron.market.model.Market.Market;
import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import com.gawron.market.repository.Market.HistoryRepository;
import com.gawron.market.repository.NA.PlayerNARepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    PlayerEUNERepository playerEUNERepository;

    @Autowired
    PlayerNARepository playerNARepository;

    @Transactional("marketTransactionManager")
    public void addHistory(HttpSession session, Market item) throws Exception{
        if(session.getAttribute("server").equals("EUNE")){
            PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());
            historyRepository.save(new History(item.getItemServer(), item.getItemOwner(), session.getAttribute("server").toString(),
                    player.getId(), item.getUuid(), item.getItemName(), item.getPrize()));
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());
            historyRepository.save(new History(item.getItemServer(), item.getItemOwner(), session.getAttribute("server").toString(),
                    player.getId(), item.getUuid(), item.getItemName(), item.getPrize()));
        }
    }

    public List<History> getHistorySell(HttpSession session){
        if(session.getAttribute("server").equals("EUNE")){
            PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());
            return historyRepository.findAllBySellerServerAndSellerID(session.getAttribute("server").toString(), player.getId());
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());
            return historyRepository.findAllBySellerServerAndSellerID(session.getAttribute("server").toString(), player.getId());
        }
    }

    public List<History> getHistoryBuy(HttpSession session){
        if(session.getAttribute("server").equals("EUNE")){
            PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());
            return historyRepository.findAllByBuyerServerAndBuyerID(session.getAttribute("server").toString(), player.getId());
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());
            return historyRepository.findAllByBuyerServerAndBuyerID(session.getAttribute("server").toString(), player.getId());
        }
    }
}
