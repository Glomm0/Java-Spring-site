/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.controller;

import GameData.model.Achievment;
import GameData.model.Game;
import GameData.model.User;
import GameData.service.ApiRequests;
import GameData.service.GameService;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author а353
 */
@Controller

public class HomeController {
@Autowired
ApiRequests ar;
@Autowired
GameService gs;
@RequestMapping("/")

public String goHome(Model model,HttpSession session) throws IOException {
//        if (!ar.doneFetchingAll){
//            gs.saveAll(ar.getAllGames());
//            ar.doneFetchingAll=true;
//        }
        User user=(User)session.getAttribute("LoggedUser");
        ApiRequests ar=new ApiRequests();
        
        ArrayList<Game> ts=ar.getTopSellers();
        for(Game g:ts){
            try{
            g.setName(gs.findByAppId(g.getAppid()).getName());
            }catch(Exception e){
            g.setName("Undefined");
            }
        }
        
        List<Game> games= ts.subList(0, 10);
        
        model.addAttribute("page",games);
        
        
    return "home";
    

}
}
