package com.gawron.market.service;

import com.gawron.market.model.EUNE.ItemOnServer;
import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.model.Market.Market;
import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.EUNE.ItemOnServerEUNERepository;
import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import com.gawron.market.repository.Market.MarketRepository;
import com.gawron.market.repository.NA.ItemOnServerNARepository;
import com.gawron.market.repository.NA.PlayerNARepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@EnableTransactionManagement
public class MarketService {
    @Autowired
    public ItemOnServerEUNERepository itemOnServerEUNERepository;

    @Autowired
    public ItemOnServerNARepository itemOnServerNARepository;

    @Autowired
    public MarketRepository marketRepository;

    @Autowired
    public ItemEUNEService itemEUNEService;

    @Autowired
    public ItemNAService itemNAService;

    @Autowired
    public PlayerEUNERepository playerEUNERepository;

    @Autowired
    public PlayerNARepository playerNARepository;

    @Autowired
    public PlayerEUNEService playerEUNEService;

    @Autowired
    public PlayerNAService playerNAService;

    @Autowired
    public HistoryService historyService;

    @Transactional("marketTransactionManager")
    public String putOnMarket(int id, String prize, HttpSession session){
        try{
             if(session.getAttribute("server").equals("EUNE")){
                 ItemOnServer item = itemOnServerEUNERepository.findById(id);
                 marketRepository.save(new Market(item.getItemOwner(), item.getName(), "EUNE", new BigDecimal(prize), item.getUuid()));
                 itemEUNEService.deleteItem(id);
             }
             else{
                 com.gawron.market.model.NA.ItemOnServer item = itemOnServerNARepository.findById(id);
                 marketRepository.save(new Market(item.getItemOwner(), item.getName(), "NA", new BigDecimal(prize), item.getUuid()));
                 itemNAService.deleteItem(id);
             }
             return "Item has been putted on the market";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @Transactional("marketTransactionManager")
    public String buyItem(HttpSession session, int id){
        try{
            Market item = marketRepository.findById(id);
            if(session.getAttribute("server").equals("EUNE")){
                PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());

                if(player.getSaldo().doubleValue() < item.getPrize().doubleValue()){
                    return "You don`t have enough money to buy this item";
                }

                playerEUNEService.changeSaldo(player, player.getSaldo().doubleValue() - item.getPrize().doubleValue());
                itemEUNEService.addItem(player, item);
            }
            else{
                PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());

                if(player.getSaldo().doubleValue() < item.getPrize().doubleValue()){
                    return "You don`t have enough money to buy this item";
                }

                playerNAService.changeSaldo(player, player.getSaldo().doubleValue() - item.getPrize().doubleValue());
                itemNAService.addItem(player, item);
            }

            if(item.getItemServer().equals("EUNE")){
                PlayerEUNE player = playerEUNERepository.findById(item.getItemOwner());
                playerEUNEService.changeSaldo(player, player.getSaldo().doubleValue() + item.getPrize().doubleValue());
            }
            else{
                PlayerNA player = playerNARepository.findById(item.getItemOwner());
                playerNAService.changeSaldo(player, player.getSaldo().doubleValue() + item.getPrize().doubleValue());
            }

            historyService.addHistory(session, item);
            marketRepository.delete(item);

            return "Item has benn bought!!!\nCheck your inventory";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    public List<Market> getItems(){
        return marketRepository.findAll();
    }

    public List<Market> getMyOffers(HttpSession session){
        if(session.getAttribute("server").equals("EUNE")){
            return marketRepository.findAllByItemOwnerAndItemServer(playerEUNERepository.findByNickname(session.getAttribute("nickname").toString()).getId(), "EUNE");
        }
        else{
            return marketRepository.findAllByItemOwnerAndItemServer(playerNARepository.findByNickname(session.getAttribute("nickname").toString()).getId(), "NA");
        }
    }
}
