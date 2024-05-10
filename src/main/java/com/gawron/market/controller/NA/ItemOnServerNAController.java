package com.gawron.market.controller.NA;

import com.gawron.market.repository.NA.ItemOnServerNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ItemOnServerNAController {
    @Autowired
    private ItemOnServerNARepository itemOnServerNARepository;
}
