/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.model;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author а353
 */
@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Override
    public String toString() {
        return "User{" + ", nickname=" + nickname + ", avatarUrl=" + avatarUrl + ", steamid=" + steamId + '}';
    }
    public User() {
        this.games = new ArrayList<Game>();
    }
//    private Integer id;
    @SerializedName("personaname")
    private String nickname;
    @SerializedName("avatar")
    private String avatarUrl;
    @SerializedName("steamid")
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long steamId;
    @OneToMany
    @JoinColumn(name="user_id")
    private List<Game> games;
    @SerializedName("avatarfull")
    private String avatarFullUrl;
    @SerializedName("lastlogoff")
    private Integer lastLogoff;
    @SerializedName("loccountrycode")
    private String locCountryCode;
    @SerializedName("timecreated")
    private Integer timeCreated;
    public void addGame(Game g){
        games.add(g);
    }
    public String getLastLogoffRel(){
        java.util.Date time=new java.util.Date((long)this.lastLogoff*1000);
        Long temp=(-1*TimeUnit.MILLISECONDS.toHours(time.getTime()-new java.util.Date().getTime()));
        return temp.toString()+" hours ago";
        
    }
    public String getTimeCreatedDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String time=format.format(new java.util.Date((long)this.timeCreated*1000));
        
        return time;
        
    }
}
