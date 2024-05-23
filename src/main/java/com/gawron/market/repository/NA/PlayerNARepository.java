package com.gawron.market.repository.NA;

import com.gawron.market.model.NA.PlayerNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface PlayerNARepository extends JpaRepository<PlayerNA, Integer> {
    PlayerNA findByNicknameAndPassword(String nickname, String password);
    PlayerNA findByNickname(String nickname);
    PlayerNA findById(int id);

    @Modifying
    @Query("update PlayerNA p set p.saldo = :saldo where p.id = :id")
    void updatePlayer(@Param(value = "saldo") BigDecimal saldo, @Param(value = "id") int id);
}
