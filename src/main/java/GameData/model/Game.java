/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.model;

import GameData.service.GameService;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author а353
 */
@Entity
@Table(name="games")
public class Game {

    public void setPeak(Long peak) {
        this.peak = peak;
    }

    public Long getPeak() {
        return peak;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public Integer getId() {
        return Id;
    }

    public String getImage_id() {
        return image_id;
    }

    @Override
    public String toString() {
        return "Game{" + "appid=" + appid + ", name=" + name + ", img_icon_url=" + img_icon_url + ", has_community_visible_stats=" + has_community_visible_stats + ", rtime_last_played=" + rtime_last_played + '}';
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_icon_url(String img_icon_url) {
        this.img_icon_url = img_icon_url;
    }

    public void setHas_community_visible_stats(boolean has_community_visible_stats) {
        this.has_community_visible_stats = has_community_visible_stats;
    }

    public void setRtime_last_played(Integer rtime_last_played) {
        this.rtime_last_played = rtime_last_played;
    }

    public Integer getAppid() {
        return appid;
    }

    public String getName() {
        return name;
    }

    public String getImg_icon_url() {
        return img_icon_url;
    }

    public boolean isHas_community_visible_stats() {
        return has_community_visible_stats;
    }

    public Integer getRtime_last_played() {
        return rtime_last_played;
    }

    public Game(Integer appid, String name, String img_icon_url, boolean has_community_visible_stats, Integer rtime_last_played) {
        this.appid = appid;
        this.name = name;
        this.img_icon_url = img_icon_url;
        this.has_community_visible_stats = has_community_visible_stats;
        this.rtime_last_played = rtime_last_played;
    }
    public Game() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer appid;
    private String name;
    private String img_icon_url;
    private boolean has_community_visible_stats;
    private Integer rtime_last_played;
    private String image_id;
    @SerializedName("peak_in_game")
    private Long peak;
    public String getImageUrl(){
        return "http://media.steampowered.com/steamcommunity/public/images/apps/"+this.appid+"/"+this.img_icon_url+".jpg";
    }
    public String getBannerUrl(){
        return "https://cdn.cloudflare.steamstatic.com/steam/apps/"+this.appid+"/"+"header.jpg";
    }
    public String getLoadedImagePath(){
        return "..\\..\\resources\\images\\"+this.getImage_id();
    }
   
}
