package com.gawron.market.service;

import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.NA.PlayerNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PlayerNAService {
    @Autowired
    private PlayerNARepository playerNARepository;

    @Transactional("NATransactionManager")
    public void changeSaldo(PlayerNA player, double saldo){
        playerNARepository.updatePlayer(BigDecimal.valueOf(saldo), player.getId());
    }
}
