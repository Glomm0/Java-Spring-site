/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.service;

import GameData.converter.JsonConverter;
import GameData.converter.jsonClasses.AchievmentCompleteon;
import GameData.converter.jsonClasses.Friend;
import GameData.model.Achievment;
import GameData.model.Game;
import GameData.model.Info;
import GameData.model.News;
import GameData.model.User;
import GameData.service.GameService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 *
 * @author а353
 */
@Component
public class ApiRequests {
    @Autowired
    GameService gameService;
    private String ApiKey;
    public ApiRequests() {
        ApiKey="******";
    }
    public boolean doneFetchingAll=false;
    private static String getStringFromInputStream(InputStream is) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    String line;
    try {
        br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            sb.append(line);

        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return sb.toString();
}
    public ArrayList<Friend> getFriends(Long userId) throws MalformedURLException, IOException{
        String url="http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key=%s&steamid=%d&relationship=friend";
        url=String.format(url,ApiKey, userId);
        InputStream stream=(InputStream) new URL(url).getContent();
        JsonConverter js=new JsonConverter();
        String ans=getStringFromInputStream(stream);
        ArrayList<Friend> friends=js.friendsFromJson(ans);
        return friends;
    }
    @Async
    public User getUserById(Long userId) throws MalformedURLException, IOException{
        String url=" http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=%s&steamids=%d";
        url=String.format(url,ApiKey, userId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        User user=js.userFromJson(ans);
        return user;
    }
    public ArrayList<Game> getGamesByUserId(Long userId) throws MalformedURLException, IOException{
        String url="https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key=%s&steamid=%d&include_appinfo=true";
        url=String.format(url,ApiKey, userId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<Game> games=js.gamesFromJson(ans);
        return games;
    }
    public ArrayList<Game> getLastGamesByUserId(Long userId) throws MalformedURLException, IOException{
        String url="http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=%s&steamid=%d&format=json";
        url=String.format(url,ApiKey, userId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<Game> games=js.gamesFromJson(ans);
        return games;
    }
    public ArrayList<News> getNewsByGameId(Integer appId) throws MalformedURLException, IOException{
        String url="https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=%d";
        url=String.format(url, appId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<News> news=js.newsFromJson(ans);
        return news;
    }
    public ArrayList<Achievment> getAllAchievmentsByGameId(Integer appId) throws MalformedURLException, IOException{
        String url="https://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v2/?key=%s&appid=%d";
        url=String.format(url,ApiKey, appId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<Achievment> achs=js.achievmentsFromJson(ans);
        return achs;
    }
    public ArrayList<Achievment> getUndoneAchievmentsByUserIdAndGameId(Long userId,Integer appId) throws MalformedURLException, IOException{
        String url="https://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v1/?key=%s&steamid=%d&appid=%d";
        url=String.format(url,ApiKey,userId, appId);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<AchievmentCompleteon> achs=js.doneAchievmentsFromJson(ans);
        ArrayList<Achievment> res=new ArrayList();
        ArrayList<Achievment> all=this.getAllAchievmentsByGameId(appId);
        if(achs!=null && all!=null){
        for(AchievmentCompleteon a:achs){
            if(a.achieved==0){
                Achievment temp=new Achievment();
                for(Achievment b:all){
                    if(b.getName().equals(a.apiname)){
                        res.add(b);
                    }
                }
            }
        }
        return res;
        }else return new ArrayList<Achievment>();
    }
    public ArrayList<Game> getTopSellers() throws MalformedURLException, IOException{
        String url="https://api.steampowered.com/ISteamChartsService/GetMostPlayedGames/v1/";
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<Game> games=js.gamesFromJson(ans);
        return games;
        
    }
    
    public ArrayList<Game> getAllGames() throws MalformedURLException, IOException{
        String url="http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=STEAMKEY&format=json";
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        ArrayList<Game> games=js.gamesFromJson(ans);
        return games;
    }
    public Info getInfoByGameAppId(Integer appid) throws MalformedURLException, IOException{
        String url="https://store.steampowered.com/api/appdetails?appids=%d";
        url=String.format(url,appid);
        InputStream stream=(InputStream) new URL(url).getContent();
        String ans=getStringFromInputStream(stream);
        JsonConverter js=new JsonConverter();
        Info info=js.infoFromJson(ans);
        return info;
    }
}
