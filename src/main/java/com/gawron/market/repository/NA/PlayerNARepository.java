package com.gawron.market.repository.NA;

import com.gawron.market.model.NA.PlayerNA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerNARepository extends JpaRepository<PlayerNA, Integer> {
    PlayerNA findByNicknameAndPassword(String nickname, String password);
    PlayerNA findByNickname(String nickname);
}
