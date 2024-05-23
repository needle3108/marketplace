package com.gawron.market.repository.EUNE;

import com.gawron.market.model.EUNE.PlayerEUNE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PlayerEUNERepository extends JpaRepository<PlayerEUNE, Integer> {
    PlayerEUNE findByNicknameAndPassword(String nickname, String password);
    PlayerEUNE findByNickname(String nickname);
    PlayerEUNE findById(int id);

    @Modifying
    @Query("update PlayerEUNE p set p.saldo = :saldo where p.id = :id")
    void updatePlayer(@Param(value = "saldo") BigDecimal saldo, @Param(value = "id") int id);
}
