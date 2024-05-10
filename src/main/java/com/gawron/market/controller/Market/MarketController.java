package com.gawron.market.controller.Market;

import com.gawron.market.repository.Market.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {
    @Autowired
    private MarketRepository marketRepository;

    @GetMapping("/index")
    public String index(){
        return "/index";
    }
}
