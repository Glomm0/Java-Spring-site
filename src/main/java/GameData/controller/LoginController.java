/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.controller;

import GameData.converter.UserConverter;
import GameData.model.User;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

/**
 *
 * @author а353
 */
@Controller
@SessionAttributes(value="LoggedUser")
public class LoginController {
    @RequestMapping("/openid")
    public String abcd(){
        OpenIdManager manager = new OpenIdManager();
        manager.setReturnTo("http://localhost:8080/kursovaya/openid/page");
        manager.setRealm("http://localhost:8080");
        Endpoint endpoint = manager.lookupEndpoint("https://steamcommunity.com/openid/"); 
        Association association = manager.lookupAssociation(endpoint); 
        String url = manager.getAuthenticationUrl(endpoint, association); 
        return ("redirect:" + url);
    }
    
    @RequestMapping("/openid/page")
    public String bdcdc(Model model,@RequestParam(value="openid.identity") String id){
        String temp_id=id.substring(id.lastIndexOf("/")+1);
        User user=new UserConverter().convert(temp_id);
        model.addAttribute("LoggedUser",user);
        return "redirect:/";
    }
    @RequestMapping("/openid/logout")
    public String logout(Model model,SessionStatus status,WebRequest request,HttpSession session){
        status.setComplete();
        return "redirect:/";
    }
}
