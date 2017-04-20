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
    private StringProperty section;
    private StringProperty instructor;
    private StringProperty dayTime;
    private StringProperty location;
    private StringProperty ta1;
    private StringProperty ta2;
    
    public Recitation(String initSection, String initInstructor, String initDayTime, String initLocation,
            String initTa1, String initTa2){
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        dayTime = new SimpleStringProperty(initDayTime);
        location = new SimpleStringProperty(initLocation);
        ta1 = new SimpleStringProperty(initTa1);
        ta2 = new SimpleStringProperty(initTa2);
        
    }
    
    public void setInstructor(String instructor) {
        this.instructor.setValue(instructor);
    }

    public void setDayTime(String dayTime) {
        this.dayTime.setValue(dayTime);
    }

    public void setLocation(String location) {
        this.location.setValue(location);
    }
    public void setTa1(String ta1){
        this.ta1.setValue(ta1);
    }

    public void setTa2(String ta2) {
        this.ta2.setValue(ta2);
    }

    public String getSection() {
        return section.getValue();
    }
    public void setSection(String s){
        section.setValue(s);
    }
    
    public String getInstructor() {
        return instructor.getValue();
    }

    public String getDayTime() {
        return dayTime.getValue();
    }

    public String getLocation() {
        return location.getValue();
    }

    public String getTa1() {
        return ta1.getValue();
    }

    public String getTa2() {
        return ta2.getValue();
    }
    
    @Override
    public int compareTo(E o) {
        return getSection().compareTo(((Recitation)o).getSection());
    }
    @Override
    public String toString() {
        return section.getValue();
    }
    
    public StringProperty sectionProperty(){
        return section;
    }
    
    public StringProperty instructorProperty(){
        return instructor;
    }
    
    public StringProperty dayTimeProperty(){
        return dayTime;
    }
    
    public StringProperty locationProperty(){
        return location;
    }
    
    public StringProperty ta1Property(){
        return ta1;
    }
    
    public StringProperty ta2Property(){
        return ta2;
    }
    
}
