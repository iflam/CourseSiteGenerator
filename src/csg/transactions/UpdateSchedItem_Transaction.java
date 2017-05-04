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
public class UpdateSchedItem_Transaction implements jTPS_Transaction{
     public final String viewType = "scheduleTab";
    ScheduleData sData;
    ScheduleItem newSItem;
    ScheduleItem ogSItem;
    public UpdateSchedItem_Transaction(ScheduleData initData, ScheduleItem oldS, ScheduleItem newS){
        sData = initData;
        newSItem = newS;
        ogSItem = oldS;
    }
    
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        sData.getItems().remove(ogSItem);
        sData.addItem(newSItem);
    }

    @Override
    public void undoTransaction() {
        sData.getItems().remove(newSItem);
        sData.addItem(ogSItem);
    }
    
}
