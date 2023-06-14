/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.converter.jsonClasses;

/**
 *
 * @author а353
 */
public class Friend {

    @Override
    public String toString() {
        return "Friend{" + "steamid=" + steamid + ", relationship=" + relationship + ", friend_since=" + friend_since + '}';
    }

    public Friend(String steamid, String relationship, Integer friend_since) {
        this.steamid = steamid;
        this.relationship = relationship;
        this.friend_since = friend_since;
    }
    public String steamid;
    public String relationship;
    public Integer friend_since;
}
