/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Itai
 */
public class Student {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty role;
    private StringProperty team;
    
    public Student(String fn, String ln, String r, String t){
        firstName = new SimpleStringProperty(fn);
        lastName = new SimpleStringProperty(ln);
        role = new SimpleStringProperty(r);
        team = new SimpleStringProperty(t);
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    public String getRole() {
        return role.getValue();
    }

    public void setRole(String role) {
        this.role.setValue(role);
    }

    public String getTeam() {
        return team.getValue();
    }

    public void setTeam(String team) {
        this.team.setValue(team);
    }
    
    public StringProperty firstnameProperty(){
        return firstName;
    }
    
    public StringProperty lastnameProperty(){
        return lastName;
    }
    
    public StringProperty roleProperty(){
        return role;
    }
    
    public StringProperty teamProperty(){
        return team;
    }
    
    
}
