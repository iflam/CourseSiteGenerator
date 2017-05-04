/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ScheduleData;
import csg.view.ScheduleView;
import java.time.LocalDate;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class ChangeEnd_Transaction implements jTPS_Transaction{
    public final String viewType = "scheduleTab";
    ScheduleData sData;
    LocalDate newDate;
    LocalDate OgDate;
    ScheduleView sV;
    public ChangeEnd_Transaction(ScheduleView sV, ScheduleData s, LocalDate d){
        sData = s;
        newDate = d;
        OgDate = sData.getEndDateAsDate();
        this.sV = sV;
    }
    @Override
    public void doTransaction() {
        sData.setEndDate(newDate);
        sV.getEndDate().setValue(newDate);
    }

    @Override
    public void undoTransaction() {
        sData.setEndDate(OgDate);
        sV.getEndDate().setValue(OgDate);
    }
    @Override
    public String getViewType() {
        return viewType;
    }
}
