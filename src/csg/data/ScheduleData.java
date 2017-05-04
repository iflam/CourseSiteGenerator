/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.ScheduleView;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Itai
 */
public class ScheduleData{
    CourseSiteGeneratorApp app;
    ObservableList<ScheduleItem> scheduleItems;
    String startDate;
    String endDate;
    public ScheduleData(CourseSiteGeneratorApp initApp){
        app = initApp;
        scheduleItems = FXCollections.observableArrayList();
        startDate = "01_01_2017";
        endDate = "12_12_2017";
    }
    
    public void resetDates(){
        startDate = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView().getDefaultStart();
        endDate = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView().getDefaultEnd();
    }
    public ObservableList<ScheduleItem> getItems(){
        return scheduleItems;
    }
    public String getStartDate(){
        return startDate;
    }
    public LocalDate getStartDateAsDate(){
        return ScheduleView.makeDate(startDate);
    }
    public LocalDate getEndDateAsDate(){
        return ScheduleView.makeDate(endDate);
    }
    
    public String getEndDate(){
        return endDate;
    }
    public void addItem(String h, String d, String ti,String time, String to, String l, String c){
        scheduleItems.add(new ScheduleItem(h,d,ti,time,to,l,c));
    }
    
    public void addItem(ScheduleItem s){
        scheduleItems.add(s);
    }
    
    public void setStartDate(String s){
        startDate = s;
    }
    
    public void setEndDate(String s){
        endDate = s;
    }
    
    public void setStartDate(LocalDate date){
        startDate = ((date.getMonthValue()<10)?"0":"") +date.getMonthValue()+"_"+((date.getDayOfMonth()<10)?"0":"")+date.getDayOfMonth()+"_"+date.getYear();
    }
    
    public void setEndDate(LocalDate date){
        endDate = ((date.getMonthValue()<10)?"0":"") +date.getMonthValue()+"_"+((date.getDayOfMonth()<10)?"0":"")+date.getDayOfMonth()+"_"+date.getYear();
    }
}
