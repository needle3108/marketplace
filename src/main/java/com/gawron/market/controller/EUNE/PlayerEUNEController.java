package com.gawron.market.controller.EUNE;

import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerEUNEController {
    @Autowired
    private PlayerEUNERepository playerEUNERepository;
}
