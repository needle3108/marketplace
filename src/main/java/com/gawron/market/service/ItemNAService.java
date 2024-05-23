package com.gawron.market.service;

import com.gawron.market.model.Market.Market;
import com.gawron.market.model.NA.ItemOnServer;
import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.NA.ItemOnServerNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemNAService {
    @Autowired
    public ItemOnServerNARepository itemOnServerNARepository;

    @Transactional("NATransactionManager")
    public void deleteItem(int id) throws Exception{
        itemOnServerNARepository.delete(itemOnServerNARepository.findById(id));
    }

    @Transactional("NATransactionManager")
    public void addItem(PlayerNA player, Market item) throws Exception{
        itemOnServerNARepository.save(new ItemOnServer(item.getItemName(), player.getId(), item.getUuid()));
    }
}
