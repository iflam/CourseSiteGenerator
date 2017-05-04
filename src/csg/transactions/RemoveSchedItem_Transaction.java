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
public class RemoveSchedItem_Transaction implements jTPS_Transaction{
    public final String viewType = "scheduleTab";
    ScheduleData sData;
    ScheduleItem sItem;
    public RemoveSchedItem_Transaction(ScheduleData initData, ScheduleItem initS){
        sData = initData;
        sItem = initS;
    }
    
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        sData.getItems().remove(sItem);
    }

    @Override
    public void undoTransaction() {
        sData.addItem(sItem);
    }
    
}
