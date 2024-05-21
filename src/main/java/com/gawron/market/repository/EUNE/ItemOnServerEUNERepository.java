package com.gawron.market.repository.EUNE;

import com.gawron.market.model.EUNE.ItemOnServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOnServerEUNERepository extends JpaRepository<ItemOnServer, Integer> {
    List<ItemOnServer> findAllByItemOwner(int itemOwner);
    ItemOnServer findById(int id);
}
