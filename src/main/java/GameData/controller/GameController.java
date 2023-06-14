/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.controller;

import GameData.service.ApiRequests;
import GameData.model.Game;
import GameData.model.News;
import GameData.model.User;
import GameData.service.GameService;
import GameData.service.UserService;
import GameData.service.Utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author а353
 */
@Controller
@SessionAttributes(value = "LoggedUser")
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;
    Utils utils=new Utils();
    @GetMapping("/add")
    public String addGame(Model model,@ModelAttribute("LoggedUser")User user) {
        if (user.getSteamId()==null){
            return "redirect:/";
        }
        Game b=new Game();
        model.addAttribute("game",b); //Adding object to model
        return "addgame";
    }
    @PostMapping("/save")
    public String saveGame(@ModelAttribute("game") Game b,@ModelAttribute("LoggedUser")User user,Model model,@RequestParam("image") CommonsMultipartFile  file,HttpSession s) throws NoSuchAlgorithmException {
        if (user.getSteamId()  ==null){
            return "redirect:/";
        }
        String fileName;
        fileName=utils.toShaString(file.getOriginalFilename())+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        b.setImage_id(fileName);
        gameService.save(b);
        user.addGame(b);
        
        userService.save(user);
        
        
        b=new Game();
        
        byte[] data = file.getBytes();
        
        String filePath
            = "C:\\Users\\A353\\Desktop\\misc_code\\currs\\kursovaya\\target\\kursovaya\\resources\\images"+ File.separator
              + fileName;
  
        // Try block to check for exceptions
        try {
            
            // Creating an object of FileOutputStream class
            
            FileOutputStream fileout= new FileOutputStream(filePath);
            fileout.write(data);
  
            // Closing connections of file
            // using close() method
            fileout.close();
            
        }
  
        // Catch block to handle the exceptions
        catch (Exception e) {
  
            // Displaying the exception/s along with
            // line number using printStackTrace() method
            e.printStackTrace();
            System.out.println(e);
        }
        
        model.addAttribute("game",b);
        return "addgame";
    }
    @RequestMapping("/{appId}")
    public String toGamePage(Model model,@PathVariable Integer appId) throws IOException{
        ApiRequests ar=new ApiRequests();
        ArrayList<News> news=ar.getNewsByGameId(appId);
        model.addAttribute("news",news);
        Game game=new Game();
        game.setAppid(appId);
        model.addAttribute("game",game);
        model.addAttribute("Info",ar.getInfoByGameAppId(appId));
        
        return "game";
    }
    
    @GetMapping("/delete")
    public String deleteGame(@ModelAttribute("LoggedUser")User user,@RequestParam("id") Integer id,Model model,@ModelAttribute("UserGames")ArrayList<Game>games){
        if (user.getSteamId()==null){
            return "redirect:/";
        }
        gameService.deleteById(id);
        
        return "redirect:/user/"+user.getSteamId()+"/games";
    }
}
