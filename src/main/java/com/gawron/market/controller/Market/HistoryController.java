package com.gawron.market.controller.Market;

import com.gawron.market.repository.Market.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HistoryController {
    @Autowired
    private HistoryRepository historyRepository;
}
