package com.gawron.market.service;

import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PlayerEUNEService {
    @Autowired
    private PlayerEUNERepository playerEUNERepository;

    @Transactional("EUNETransactionManager")
    public void changeSaldo(PlayerEUNE player, double saldo){
        playerEUNERepository.updatePlayer(BigDecimal.valueOf(saldo), player.getId());
    }
}
