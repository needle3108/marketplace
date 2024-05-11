package com.gawron.market.controller.Market;

import com.gawron.market.model.EUNE.PlayerEUNE;
import com.gawron.market.model.NA.PlayerNA;
import com.gawron.market.repository.EUNE.PlayerEUNERepository;
import com.gawron.market.repository.Market.MarketRepository;
import com.gawron.market.repository.NA.PlayerNARepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MarketController {
    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private PlayerNARepository playerNARepository;

    @Autowired
    private PlayerEUNERepository playerEUNERepository;

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
}
