package com.gawron.market.controller.Market;

import com.gawron.market.model.EUNE.ItemOnServer;
import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.EUNE.ItemOnServerEUNERepository;
import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import com.gawron.market.repository.Market.MarketRepository;
import com.gawron.market.repository.NA.ItemOnServerNARepository;
import com.gawron.market.repository.NA.PlayerNARepository;
import com.gawron.market.service.HistoryService;
import com.gawron.market.service.MarketService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MarketController {
    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private PlayerNARepository playerNARepository;

    @Autowired
    private PlayerEUNERepository playerEUNERepository;

    @Autowired
    private ItemOnServerEUNERepository itemOnServerEUNERepository;

    @Autowired
    private ItemOnServerNARepository itemOnServerNARepository;

    @Autowired
    private MarketService marketService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/index")
    public String index(HttpSession session){
        if(isUserLogged(session)){
            return "/logSuccess";
        }
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    private static boolean isUserLogged(HttpSession session){
        try{
            String isLogged = session.getAttribute("nickname").toString();
            return true;
        }
        catch(NullPointerException e){
            return false;
        }
    }

    @PostMapping("/login/authorization")
    public String loginAuthorization(@RequestParam Map<String,String> loginParams, HttpServletResponse response, HttpSession session){
        String server = loginParams.get("server");
        String nickname = loginParams.get("nickname");
        String password = loginParams.get("password");

        if(server.equals("EUNE")){
            PlayerEUNE candidate = playerEUNERepository.findByNicknameAndPassword(nickname, password);
            if(candidate == null){
                response.setStatus(401);
                return "/login";
            }
            session.setAttribute("nickname", candidate.getNickname());
            session.setAttribute("server", server);
        }
        else{
            PlayerNA candidate = playerNARepository.findByNicknameAndPassword(nickname, password);
            if(candidate == null){
                response.setStatus(401);
                return "/login";
            }
            session.setAttribute("nickname", candidate.getNickname());
            session.setAttribute("server", server);
        }
        response.setStatus(201);
        return "/logSuccess";
    }

    @GetMapping("/logSuccess")
    public String logSuccess(){
        return "/logSuccess";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/index";
    }

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/register/save")
    public String addPlayer(@RequestParam Map<String, String> newPlayer, HttpServletResponse response){
        String server = newPlayer.get("server");
        String nickname = newPlayer.get("nickname");
        String password = newPlayer.get("password");

        if(server.equals("EUNE")){
            PlayerEUNE player= playerEUNERepository.findByNickname(nickname);
            if(player != null){
                response.setStatus(401);
                return "/register";
            }
            playerEUNERepository.save(new PlayerEUNE(nickname, password));
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(nickname);
            if(player != null){
                response.setStatus(401);
                return "/register";
            }
            playerNARepository.save(new PlayerNA(nickname, password));
        }
        response.setStatus(201);
        return "/registerSuccess";
    }

    @GetMapping("/userInventory")
    public String userInventory(Model model, HttpSession session){
        if(!isUserLogged(session)){
            return "/index";
        }

        if(session.getAttribute("server").equals("EUNE")){
            PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());
            List<ItemOnServer> items = itemOnServerEUNERepository.findAllByItemOwner(player.getId());
            model.addAttribute("items", items);
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());
            List<com.gawron.market.model.NA.ItemOnServer> items = itemOnServerNARepository.findAllByItemOwner(player.getId());
            model.addAttribute("items", items);
        }

        return "/userInventory";
    }

    @RequestMapping(value="sell", method = RequestMethod.GET)
    public String sell(@RequestParam("id") int id, Model model, HttpSession session){
        if(!isUserLogged(session)){ return "/index"; }

        if(session.getAttribute("server").equals("EUNE")){
            ItemOnServer itemOnServer = itemOnServerEUNERepository.findById(id);
            model.addAttribute("item", itemOnServer);
        }
        else{
            com.gawron.market.model.NA.ItemOnServer itemOnServer = itemOnServerNARepository.findById(id);
            model.addAttribute("item", itemOnServer);
        }

        return "/setPrize";
    }

    @RequestMapping(value="buy", method = RequestMethod.GET)
    public String buy(@RequestParam("id") int id, Model model, HttpSession session){
        if(!isUserLogged(session)){ return "/index"; }

        model.addAttribute("msg", marketService.buyItem(session, id));

        return "/buyMsg";
    }

    @RequestMapping(value="setPrize/confirm", method = RequestMethod.POST)
    public String setPrizeConfirm(@RequestParam Map<String,String> prize, @RequestParam("id") int id, HttpSession session, Model model){
        if(!isUserLogged(session)){ return "/index"; }

        String msg = marketService.putOnMarket(id, prize.get("prize"), session);

        model.addAttribute("msg", msg);

        return "/putOnMarket";
    }

    @GetMapping("/market")
    public String marketplace(HttpSession session, Model model){
        if(!isUserLogged(session)){ return "/index"; }

        model.addAttribute("items", marketService.getItems());

        if(session.getAttribute("server").equals("EUNE")){
            PlayerEUNE player = playerEUNERepository.findByNickname(session.getAttribute("nickname").toString());
            model.addAttribute("saldo", player.getSaldo());
        }
        else{
            PlayerNA player = playerNARepository.findByNickname(session.getAttribute("nickname").toString());
            model.addAttribute("saldo", player.getSaldo());
        }

        return "/market";
    }

    @GetMapping("/myOffers")
    public String myOffers(HttpSession session, Model model){
        if(!isUserLogged(session)){ return "/index"; }

        model.addAttribute("items", marketService.getMyOffers(session));

        return "/myOffers";
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model){
        if(!isUserLogged(session)){ return "/index"; }

        model.addAttribute("itemsSold", historyService.getHistorySell(session));
        model.addAttribute("itemsBought", historyService.getHistoryBuy(session));

        return "/history";
    }

}
