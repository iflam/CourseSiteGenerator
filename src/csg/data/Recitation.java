/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Itai
 */
public class Recitation<E extends Comparable<E>> implements Comparable<E> {
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String ta1;
    private String ta2;
    
    public Recitation(String initSection, String initInstructor, String initDayTime, String initLocation,
            String initTa1, String initTa2){
        section = initSection;
        instructor = initInstructor;
        dayTime = initDayTime;
        location = initLocation;
        ta1 = initTa1;
        ta2 = initTa2;
        
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTa1(String ta1) {
        this.ta1 = ta1;
    }

    public void setTa2(String ta2) {
        this.ta2 = ta2;
    }

    public String getSection() {
        return section;
    }
    public void setSection(String s){
        section = s;
    }
    
    public String getInstructor() {
        return instructor;
    }

    public String getDayTime() {
        return dayTime;
    }

    public String getLocation() {
        return location;
    }

    public String getTa1() {
        return ta1;
    }

    public String getTa2() {
        return ta2;
    }
    
    @Override
    public int compareTo(E o) {
        return getSection().compareTo(((Recitation)o).getSection());
    }
    @Override
    public String toString() {
        return section;
    }
    
}
