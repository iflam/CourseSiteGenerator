/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;

/**
 *
 * @author Itai
 */
public class UpdateTA_Transaction implements jTPS_Transaction{
    TAData data;
    ObservableList teachingAssistants;
    TeachingAssistant t;
    String name;
    String email;
    String OgName;
    String OgEmail;
    public UpdateTA_Transaction(TAData data, TeachingAssistant t, String name, String email){
        teachingAssistants = data.getTeachingAssistants();
        this.t = t;
        this.data = data;
        this.name = name;
        this.email = email;
        OgName = t.getName();
        OgEmail = t.getEmail();
    }
    @Override
    public void doTransaction() {
        data.updateTA(t, name, email);
    }

    @Override
    public void undoTransaction() {
        TeachingAssistant b = new TeachingAssistant(name, email);
        data.updateTA(b, OgName, OgEmail);
    }
    
}
