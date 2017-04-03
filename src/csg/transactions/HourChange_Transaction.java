/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.file.TimeSlot;
import csg.view.TAView;

/**
 *
 * @author Itai
 */
public class HourChange_Transaction implements jTPS_Transaction{
    TAData data;
    HashMap<String, StringProperty> OGofficeHours;
    int startTime;
    int endTime;
    int OGStartTime;
    int OGEndTime;
    ArrayList<TimeSlot> OGofficeHourTimes;
    TAView app;
    public HourChange_Transaction(TAData data,int startTime, int endTime, TAView app){
        this.data = data;
        this.startTime = startTime;
        this.endTime = endTime;
        OGStartTime = data.getStartHour();
        OGEndTime = data.getEndHour();
        OGofficeHours = new HashMap<>(data.getOfficeHours());
        OGofficeHourTimes = TimeSlot.buildOfficeHoursList(data);
        this.app = app;
    }
    @Override
    public void doTransaction() {
       data.changeOfficeHours(startTime, endTime);
    }

    @Override
    public void undoTransaction() {
        //MAKE SURE WORKING FOR MULTIPLE
        // :C
        data.changeOfficeHours(OGStartTime, OGEndTime);
        app.clearOfficeHoursGrid(data);
        for(TimeSlot ts : OGofficeHourTimes){
            try{
                data.addOfficeHoursReservation(ts.getDay(),ts.getTime(),ts.getName());
            }
            catch(Exception e){
                
            }
        }  
    }
    
}
