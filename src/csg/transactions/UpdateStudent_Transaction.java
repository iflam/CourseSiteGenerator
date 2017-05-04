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
public class UpdateStudent_Transaction implements jTPS_Transaction{
    public final String viewType = "projectTab";
    ProjectData data;
    Student newStudent;
    Student ogStudent;
    public UpdateStudent_Transaction(ProjectData initData,Student ogStudent, Student initS){
        data = initData;
        newStudent = initS;
        this.ogStudent = ogStudent;
    }
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        data.updateStudent(ogStudent, newStudent);
    }

    @Override
    public void undoTransaction() {
        data.updateStudent(newStudent, ogStudent);
    }
    
}
