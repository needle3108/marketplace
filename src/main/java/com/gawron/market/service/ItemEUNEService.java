package com.gawron.market.service;

import com.gawron.market.model.EUNE.ItemOnServer;
import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.model.Market.Market;
import com.gawron.market.repository.EUNE.ItemOnServerEUNERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemEUNEService {
    @Autowired
    public ItemOnServerEUNERepository itemOnServerEUNERepository;

    @Transactional("EUNETransactionManager")
    public void deleteItem(int id) throws Exception{
            itemOnServerEUNERepository.delete(itemOnServerEUNERepository.findById(id));
    }

    @Transactional("EUNETransactionManager")
    public void addItem(PlayerEUNE player, Market item) throws Exception{
        itemOnServerEUNERepository.save(new ItemOnServer(item.getItemName(), player.getId(), item.getUuid()));
    }
}
