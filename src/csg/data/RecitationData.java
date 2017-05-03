/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Itai
 */
public class RecitationData{
    ObservableList<Recitation> recitations;
    CourseSiteGeneratorApp app;
    
    public RecitationData(CourseSiteGeneratorApp initApp){
        app = initApp;
        recitations = FXCollections.observableArrayList();
    
    }
    
    public void addRecitation(String initSection, String initInstructor, String initDayTime, String initLocation,
            String initTa1, String initTa2){
        Recitation r = new Recitation(initSection, initInstructor, initDayTime, initLocation, initTa1, initTa2);
        recitations.add(r);
    }
    
    public void addRecitation(Recitation r){
        recitations.add(r);
    }
    
    public void removeRecitation(Recitation r){
        recitations.remove(r);
    }
    public ObservableList<Recitation> getRecitations(){
        return recitations;
    }
}
