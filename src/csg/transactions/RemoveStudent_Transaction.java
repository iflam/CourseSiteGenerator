/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ProjectData;
import csg.data.Student;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class RemoveStudent_Transaction implements jTPS_Transaction{
    public final String viewType = "projectTab";
    ProjectData data;
    Student s;
    public RemoveStudent_Transaction(ProjectData initData, Student initS){
        data = initData;
        s = initS;
    }
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        data.getStudents().remove(s);
    }

    @Override
    public void undoTransaction() {
        data.addStudent(s);
    }
    
}
