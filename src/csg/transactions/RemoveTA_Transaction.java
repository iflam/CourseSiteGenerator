/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.file.TimeSlot;

/**
 *
 * @author Itai
 */
public class RemoveTA_Transaction implements jTPS_Transaction{
    TAData data;
    ArrayList<TimeSlot> OGofficeHourTimes;
    ObservableList teachingAssistants;
    TeachingAssistant t;
    public RemoveTA_Transaction(TAData data, TeachingAssistant t){
        teachingAssistants = data.getTeachingAssistants();
        this.t = t;
        this.data = data;
        OGofficeHourTimes = TimeSlot.buildOfficeHoursList(data);
    }
    @Override
    public void doTransaction() {
        data.removeTA(t.getName());
    }

    @Override
    public void undoTransaction() {
        data.addTA(t.getName(), t.getEmail(),t.isIsUndergrad());
        for(TimeSlot ts : OGofficeHourTimes){
            try{
                if(ts.getName().equals(t.getName()))
                    data.addOfficeHoursReservation(ts.getDay(),ts.getTime(),ts.getName());
            }
            catch(Exception e){
                
            }
        }  
    }
    
}
