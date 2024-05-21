package com.gawron.market.service;

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
}
