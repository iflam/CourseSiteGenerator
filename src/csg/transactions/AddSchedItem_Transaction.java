/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ScheduleData;
import csg.data.ScheduleItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class AddSchedItem_Transaction implements jTPS_Transaction{
    public final String viewType = "scheduleTab";
    ScheduleData sData;
    ScheduleItem sItem;
    public AddSchedItem_Transaction(ScheduleData initData, ScheduleItem initS){
        sData = initData;
        sItem = initS;
    }
    
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        sData.addItem(sItem);
    }

    @Override
    public void undoTransaction() {
        sData.getItems().remove(sItem);
    }
    
}
