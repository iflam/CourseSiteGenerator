/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Itai
 */
public class Team {
    private StringProperty name;
    private StringProperty color;
    private StringProperty textColor;
    private StringProperty link;
    
    public Team(String n, String c, String tColor, String l){
        name = new SimpleStringProperty(n);
        color = new SimpleStringProperty(c);
        textColor = new SimpleStringProperty(tColor);
        link = new SimpleStringProperty(l);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getColor() {
        return color.getValue();
    }

    public void setColor(String color) {
        this.color.setValue(color);
    }

    public String getTextColor() {
        return textColor.getValue();
    }

    public void setTextColor(String textColor) {
        this.textColor.setValue(textColor);
    }

    public String getLink() {
        return link.getValue();
    }

    public void setLink(String link) {
        this.link.setValue(link);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public StringProperty colorProperty(){
        return color;
    }
    
    public StringProperty textcolorProperty(){
        return textColor;
    }
    
    public StringProperty linkProperty(){
        return link;
    }
    
    
}
