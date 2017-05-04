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
public class ToggleTA_Transaction implements jTPS_Transaction{
    public String viewType = "taTab";
    TAData data;
    String cellKey;
    String taName;
    public ToggleTA_Transaction(TAData data, TeachingAssistant t, String cellKey){
        this.data = data;
        taName = t.getName();
        this.cellKey = cellKey;
    }
    @Override
    public void doTransaction() {
        data.toggleTAOfficeHours(cellKey, taName);
    }

    @Override
    public void undoTransaction() {
        data.toggleTAOfficeHours(cellKey, taName);
    }

    @Override
    public String getViewType() {
        return viewType;
    }
    
}
