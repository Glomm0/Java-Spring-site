/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.converter;
import GameData.converter.jsonClasses.AchievmentCompleteon;
import GameData.converter.jsonClasses.Friend;
import GameData.converter.jsonClasses.Friendlist;
import GameData.model.Achievment;
import GameData.model.Game;
import GameData.model.Info;
import GameData.model.News;

import GameData.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author а353
 */
public class JsonConverter {
    public JsonConverter() {
    }
    public ArrayList<Friend> friendsFromJson(String input){
        Gson gson=new Gson();
        input=input.substring(input.indexOf(':')+1,input.length()-1);
//        System.out.println(input); 
        Friendlist ans=gson.fromJson(input, Friendlist.class);
//        ArrayList<Friend> fr=new ArrayList<Friend>();
//        fr.add(new Friend("76561198026352365","friend",1589727156));
//        Friendlist ans=new Friendlist(fr);
//        System.out.println(gson.toJson(ans));
//         System.out.println(ans.friends.get(0));
        return ans.friends;
    }
    public User userFromJson(String input){
        User user=new User();
        Gson gson=new Gson();
        input=input.substring(input.indexOf('[')+1,input.length()-3);
        user=gson.fromJson(input, user.getClass());
        return user;
    }
    public ArrayList<Game> gamesFromJson(String input){
        ArrayList<Game> games=new ArrayList<>();
        input=input.substring(input.indexOf('['),input.length()-2);
        Type listOfGamesObject = new TypeToken<ArrayList<Game>>() {}.getType();
        Gson gson=new Gson();
        games=gson.fromJson(input, listOfGamesObject);
        return games;
    }
    public ArrayList<News> newsFromJson(String input){
        ArrayList<News> news=new ArrayList<>();
        input=input.substring(input.indexOf('['),input.lastIndexOf(']')+1);
        Type listOfGamesObject = new TypeToken<ArrayList<News>>() {}.getType();
        Gson gson=new Gson();
        news=gson.fromJson(input, listOfGamesObject);
        return news;
    }
    public ArrayList<Achievment> achievmentsFromJson(String input){
        ArrayList<Achievment> achs=new ArrayList<>();
        try{
        input=input.substring(input.indexOf('['),input.lastIndexOf(']')+1);
        Type listOfGamesObject = new TypeToken<ArrayList<Achievment>>() {}.getType();
        Gson gson=new Gson();
        JsonReader reader = new JsonReader(new StringReader(input));
        reader.setLenient(true);
        achs=gson.fromJson(reader, listOfGamesObject);
        return achs;
        }catch (Exception e){
           return null;
        }
    }
    public ArrayList<AchievmentCompleteon> doneAchievmentsFromJson(String input){
        ArrayList<AchievmentCompleteon> achs=new ArrayList<>();
        try{
        input=input.substring(input.indexOf('['),input.lastIndexOf(']')+1);
        Type listOfGamesObject = new TypeToken<ArrayList<AchievmentCompleteon>>() {}.getType();
        Gson gson=new Gson();
        JsonReader reader = new JsonReader(new StringReader(input));
        reader.setLenient(true);
        achs=gson.fromJson(reader, listOfGamesObject);
        return achs;
        }catch (Exception e){
           return null;
        }
    }
    public Info infoFromJson(String input){
        Info temp=new Info();
        input=input.substring(input.indexOf("type")-2,input.length()-2);
        Gson gson=new Gson();
        Info res=gson.fromJson(input, Info.class);
        return res;
    }
}
