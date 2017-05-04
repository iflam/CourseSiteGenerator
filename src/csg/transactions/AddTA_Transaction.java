/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;

/**
 *
 * @author Itai
 */
public class AddTA_Transaction implements jTPS_Transaction{
    public final String viewType = "taTab";
    TAData data;
    ObservableList teachingAssistants;
    TeachingAssistant t;
    public AddTA_Transaction(TAData data, TeachingAssistant t){
        teachingAssistants = data.getTeachingAssistants();
        this.t = t;
        this.data = data;
    }
    @Override
    public void doTransaction() {
        data.addTA(t.getName(), t.getEmail(),t.getUndergrad());
    }

    @Override
    public void undoTransaction() {
        data.removeTA(t.getName());
    }
    @Override
    public String getViewType() {
        return viewType;
    }
    
}
