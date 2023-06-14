/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameData.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author а353
 */

public class Achievment {

    @Override
    public String toString() {
        return "Achievment{" + "displayName=" + displayName + ", description=" + description + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
    private String name;
    private String displayName;
    private String description;
    private String icon;
    
}
