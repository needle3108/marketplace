package com.gawron.market.repository.NA;

import com.gawron.market.model.NA.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerNARepository extends JpaRepository<Player, Integer> {
}
