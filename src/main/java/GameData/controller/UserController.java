/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.controller;

import GameData.converter.JsonConverter;
import GameData.model.Achievment;
import GameData.service.ApiRequests;
import GameData.model.Game;
import GameData.model.User;
import GameData.service.GameService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author а353
 */
@Controller
@SessionAttributes(value = "User")
public class UserController {
    @Autowired
    ApiRequests ar;
    @Autowired
    GameService gameService;
    @RequestMapping(value="/user/{id}")
    public String toUserPage(Model model, @PathVariable("id") User user) throws IOException{
        model.addAttribute("User",user);
        ArrayList<Game> games=ar.getLastGamesByUserId(user.getSteamId());
        model.addAttribute("lastGames",games);
        return "userpage";
    }
    @RequestMapping(value="/user/{id}/games")
    public String toGamesPage(Model model, @PathVariable("id") User user) throws IOException{
        
        model.addAttribute("User",user);
        ArrayList<Game> games=ar.getGamesByUserId(user.getSteamId());
        model.addAttribute("Games",games);
        games=(ArrayList<Game>) gameService.findAllByUserId(user.getSteamId());
        model.addAttribute("UserGames",games);
        return "usergames";
    }
    @RequestMapping("/user/{id}/achievments")
    public String toAchievmentsPage(Model model, @PathVariable("id") User user) throws IOException{
    ArrayList<Game> lastGames=ar.getLastGamesByUserId(user.getSteamId());
        HashMap<Game,ArrayList<Achievment>> res=new HashMap();
        model.addAttribute("lastGames",lastGames);
        for(Game game:lastGames){
            Integer gameId=game.getAppid();
            ArrayList<Achievment> temp=ar.getUndoneAchievmentsByUserIdAndGameId(user.getSteamId(), gameId);
            if (temp.size()>0){
            res.put(game,temp );
            }
        }
        
        model.addAttribute("playerAchievments",res);
        
        return "achievments";
    
    }
   @ExceptionHandler(java.io.IOException.class)
    public String handleException(
    java.io.IOException ex, Model m){
        m.addAttribute("exceptionMessage","Error with request to steam");
return "global_error";
}

}
