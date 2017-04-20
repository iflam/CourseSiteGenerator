/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Itai
 */
public class ScheduleItem {
    private StringProperty type;
    private StringProperty date;
    private String time;
    private StringProperty title;
    private StringProperty topic;
    private String link;
    private String criteria;
    
    public ScheduleItem(String h, String d, String ti, String time, String to, String l, String c){
        type = new SimpleStringProperty(h);
        date = new SimpleStringProperty(d);
        this.time = time;
        title = new SimpleStringProperty(ti);
        topic = new SimpleStringProperty(to);
        link = l;
        criteria = c;
    }

    public String getType() {
        return type.getValue();
    }

    public void setType(String holiday) {
        this.type.setValue(holiday);
    }

    public String getTime(){
        return time;
    }
    
    public void setTime(String s){
        time = s;
    }
    public String getDate() {
        return date.getValue();
    }

    public void setDate(Date d) {
        this.date.setValue(d.getDay()+"_"+d.getMonth()+"_"+d.getYear());
    }
    
    public void setDate(String d){
        date.setValue(d);
    }

    public String getTitle() {
        return title.getValue();
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public String getTopic() {
        return topic.getValue();
    }

    public void setTopic(String topic) {
        this.topic.setValue(topic);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
    
    public StringProperty typeProperty(){
        return type;
    }
    
    public StringProperty dateProperty(){
        return date;
    }
    
    public StringProperty titleProperty(){
        return title;
    }
    
    public StringProperty topicProperty(){
        return topic;
    }
    
}
