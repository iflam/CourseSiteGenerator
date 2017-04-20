/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Itai
 */
public class ProjectData{
    CourseSiteGeneratorApp app;
    ObservableList<Team> teams;
    ObservableList<Student> students;
    public ProjectData(CourseSiteGeneratorApp initApp){
        app = initApp;
        teams = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
    }
    
    public ObservableList<Team> getTeams(){
        return teams;
    }
    
    public ObservableList<Student> getStudents(){
        return students;
    }
    
    public void addTeam(String name, String color, String textColor, String link){
        teams.add(new Team(name,color,textColor,link));
    }
    
    public void addStudent(String fn, String ln, String role, String team){
        students.add(new Student(fn,ln,role,team));
    }
    
}
