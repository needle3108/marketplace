package com.gawron.market.repository.EUNE;

import com.gawron.market.model.EUNE.PlayerEUNE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerEUNERepository extends JpaRepository<PlayerEUNE, Integer> {
    PlayerEUNE findByNicknameAndPassword(String nickname, String password);
    PlayerEUNE findByNickname(String nickname);
}
