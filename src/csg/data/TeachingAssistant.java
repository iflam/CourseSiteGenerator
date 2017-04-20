package csg.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final StringProperty name;
    private final StringProperty email;
    private BooleanProperty undergrad;

    /**
     * Constructor initializes the TA name
     */
    public TeachingAssistant(String initName, String initEmail, boolean initIsUndergrad) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        undergrad = new SimpleBooleanProperty(initIsUndergrad);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail){
        email.set(initEmail);
    }
    
    public Boolean getUndergrad(){
        return undergrad.get();
    }
    
    public void setUndergrad(boolean s){
        undergrad.set(s);
    }
    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }
    
    public BooleanProperty undergradProperty(){
        return undergrad;
    }
    
//    public void undergradProperty(){
//        System.out.println(undergrad);
//        if(undergrad.getValue())
//            undergrad.setValue(Boolean.FALSE);
//        else
//            undergrad.setValue(Boolean.TRUE);
//    }
}