/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.model;

import GameData.converter.jsonClasses.Genre;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author а353
 */
@Getter
@Setter
public class Info {

    @Override
    public String toString() {
        return "Info{" + "description=" + description + ", website=" + website + ", developers=" + developers + ", genres=" + genres + '}';
    }
    @SerializedName("short_description")
    private String description;
    private String website;
    private ArrayList<String> developers;
    private ArrayList<Genre> genres;
    private String name;
    
}
