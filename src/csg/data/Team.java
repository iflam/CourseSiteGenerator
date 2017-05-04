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
public class Team<E extends Comparable<E>> implements Comparable<E> {
    private StringProperty name;
    private StringProperty color;
    private StringProperty textColor;
    private StringProperty link;
    private String red;
    private String green;
    private String blue;
    private String tRed;
    private String tGreen;
    private String tBlue;
    
    public Team(String n, String c, String tColor, String l){
        name = new SimpleStringProperty(n);
        color = new SimpleStringProperty(c);
        textColor = new SimpleStringProperty(tColor);
        link = new SimpleStringProperty(l);
        red = Integer.parseInt(c.substring(1,3),16)+"";
        green = Integer.parseInt(c.substring(3,5),16)+"";
        blue = Integer.parseInt(c.substring(5),16)+"";
        tRed = Integer.parseInt(tColor.substring(1,3),16)+"";
        tGreen = Integer.parseInt(tColor.substring(3,5),16)+"";
        tBlue = Integer.parseInt(tColor.substring(5),16)+"";
    }

    public String gettRed() {
        return tRed;
    }

    public String gettGreen() {
        return tGreen;
    }

    public String gettBlue() {
        return tBlue;
    }

    public String getGreen() {
        return green;
    }

    public String getBlue() {
        return blue;
    }
    public String getRed() {
        return red;
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
    @Override
    public String toString() {
        return name.getValue();
    }
    @Override
    public int compareTo(E o) {
       return getName().compareTo(((Team)o).getName());
    }
    
    
}
