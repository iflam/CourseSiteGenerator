/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ProjectData;
import csg.data.Student;
import csg.data.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class RemoveTeam_Transaction implements jTPS_Transaction{
    public final String viewType = "projectTab";
    ProjectData data;
    Team t;
    ObservableList<Student> studentsWithThatTeam;
    public RemoveTeam_Transaction(ProjectData initData, Team initT){
        data = initData;
        t = initT;
        studentsWithThatTeam = FXCollections.observableArrayList();
        for(Student s: data.getStudents())
            if(s.getTeam().equals(t.getName()))
                studentsWithThatTeam.add(new Student(s.getFirstName(),s.getLastName(),s.getRole(),s.getTeam()));
    }
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        data.removeTeam(t);
    }

    @Override
    public void undoTransaction() {
        data.addTeam(studentsWithThatTeam,t);
    }
    
}
