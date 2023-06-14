/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.controller;

import GameData.converter.JsonConverter;
import GameData.converter.jsonClasses.Friend;
import GameData.service.ApiRequests;
import GameData.model.User;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author а353
 */
@Controller
@SessionAttributes(value = "LoggedUser")
public class FriendsController {
    
    
    @RequestMapping("/friends")
    public String toInfo(Model model,@ModelAttribute("LoggedUser") User user) throws MalformedURLException, IOException{
        if (user==null){
            return "redirect:/";
        }
        ApiRequests ar=new ApiRequests();
        model.addAttribute("ar",ar);
        
        return "friends";
    }
    @ModelAttribute("FriendList")
    public List<Friend> getFriends(@ModelAttribute("LoggedUser") User user) throws MalformedURLException, IOException{
        ApiRequests ar=new ApiRequests();
        
        if (user.getSteamId()==null){
            return null;
        }
        Gson gson = new Gson();
        JsonConverter jc=new JsonConverter();
        ArrayList<Friend> ans=ar.getFriends(user.getSteamId());
        
        
        return ans;
    }
    
    
}
