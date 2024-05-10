package com.gawron.market.controller.EUNE;

import com.gawron.market.repository.EUNE.ItemOnServerEUNERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ItemOnServerEUNEController {
    @Autowired
    private ItemOnServerEUNERepository itemOnServerEUNERepository;
}
