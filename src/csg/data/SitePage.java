/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Itai
 */
public class SitePage {
    private BooleanProperty isUsed;
    private StringProperty title;
    private StringProperty file;
    private StringProperty script;
    
    public SitePage(String t, String f, String s, boolean i){
        title = new SimpleStringProperty(t);
        file = new SimpleStringProperty(f);
        script = new SimpleStringProperty(s);
        isUsed = new SimpleBooleanProperty(i);
    }

    public BooleanProperty useProperty(){
        return isUsed;
    }
    
    public StringProperty titleProperty(){
        return title;
    }
    
    public StringProperty fileProperty(){
        return file;
    }
    
    public StringProperty scriptProperty(){
        return script;
    }
    
    public void updateUsed(){
        if(isUsed.getValue())
            isUsed.setValue(Boolean.FALSE);
        else
            isUsed.setValue(Boolean.TRUE);      
    }
}
