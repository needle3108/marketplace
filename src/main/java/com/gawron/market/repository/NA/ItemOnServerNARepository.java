package com.gawron.market.repository.NA;

import com.gawron.market.model.NA.ItemOnServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOnServerNARepository extends JpaRepository<ItemOnServer, Integer> {
    List<ItemOnServer> findAllByItemOwner(int itemOwner);
    ItemOnServer findById(int id);
}
