/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import properties_manager.PropertiesManager;

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
    
    public void addTeam(Team t){
        teams.add(t);
    }
    public void addTeam(ObservableList<Student> studentList, Team t){
        teams.add(t);
        for(Student student: students){
            for(Student s: studentList){
                if(student.getFirstName().equals(s.getFirstName()) && student.getLastName().equals(s.getLastName())){
                    student.setTeam(s.getTeam());
                }
            }
        }
    }
    public void addStudent(String fn, String ln, String role, String team){
        students.add(new Student(fn,ln,role,team));
    }
    
    public void addStudent(Student s){
        students.add(s);
    }
    
    public void updateTeam(Team originalTeam, Team newTeam){
        for(Student s : students){
            if(s.getTeam().equals(originalTeam.getName())){
                s.setTeam(newTeam.getName());
            }
        }
        teams.remove(originalTeam);
        teams.add(newTeam);
    }
    
    public void removeTeam(Team t){
        teams.remove(t);
        boolean didNotSelect = false;
        for(Student s: students){
            if(s.getTeam().equals(t.getName())){
                String newTeamName = promptUserForTeam(s.getFirstName() + " " + s.getLastName());
                if(!newTeamName.equals("-1")){
                    s.setTeam(newTeamName);
                }
                else{
                    didNotSelect = true;
                    s.setTeam("");
                }
            }
        }
        if(didNotSelect){
            Alert alert = new Alert(AlertType.INFORMATION);
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            alert.setTitle(props.getProperty(CourseSiteGeneratorProp.NO_TEAM_TEXT.toString()));
            alert.setHeaderText(props.getProperty(CourseSiteGeneratorProp.NO_TEAM_MESSAGE.toString()));
            alert.setContentText(props.getProperty(CourseSiteGeneratorProp.NO_TEAM_CONTENT.toString()));
            alert.showAndWait();
        }
    }
    
    public String promptUserForTeam(String s){
        if(!teams.isEmpty()){
            ChoiceDialog<String> dialog = new ChoiceDialog(teams.get(0).getName(),teams);
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            dialog.setTitle(props.getProperty(CourseSiteGeneratorProp.TEAM_PROMPT_TEXT.toString()));
            dialog.setHeaderText(props.getProperty(CourseSiteGeneratorProp.TEAM_PROMPT_MESSAGE.toString()));
            dialog.setContentText(props.getProperty(CourseSiteGeneratorProp.TEAM_ENTER_TEXT.toString()) + " " + s+":");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                return result.get();
            }
        }
        return "-1";
    }
    public void updateStudent(Student ogStudent, Student newStudent){
        students.remove(ogStudent);
        students.add(newStudent);
    }
}
