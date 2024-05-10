package com.gawron.market.controller.NA;

import com.gawron.market.repository.NA.PlayerNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerNAController {
    @Autowired
    private PlayerNARepository playerNARepository;
}
