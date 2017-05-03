/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.TAView;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import csg.file.TimeSlot;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Itai
 */
public class TAData{

    // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    CourseSiteGeneratorApp app;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistant> teachingAssistants;

    // THIS WILL STORE ALL THE OFFICE HOURS GRID DATA, WHICH YOU
    // SHOULD NOTE ARE StringProperty OBJECTS THAT ARE CONNECTED
    // TO UI LABELS, WHICH MEANS IF WE CHANGE VALUES IN THESE
    // PROPERTIES IT CHANGES WHAT APPEARS IN THOSE LABELS
    HashMap<String, StringProperty> officeHours;
    
    // THESE ARE THE LANGUAGE-DEPENDENT VALUES FOR
    // THE OFFICE HOURS GRID HEADERS. NOTE THAT WE
    // LOAD THESE ONCE AND THEN HANG ON TO THEM TO
    // INITIALIZE OUR OFFICE HOURS GRID
    ArrayList<String> gridHeaders;

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;
    boolean undoRedo = false;
    
    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 0;
    public static final int MAX_END_HOUR = 24;

    /**
     * This constructor will setup the required data structures for
     * use, but will have to wait on the office hours grid, since
     * it receives the StringProperty objects from the Workspace.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    public TAData(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        teachingAssistants = FXCollections.observableArrayList();

        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        
        //THIS WILL STORE OUR OFFICE HOURS
        officeHours = new HashMap();
        
        // THESE ARE THE LANGUAGE-DEPENDENT OFFICE HOURS GRID HEADERS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
    }
    
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    public void resetData() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();
    }
    // ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    
    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }
    
    public ObservableList getTeachingAssistants() {
        return teachingAssistants;
    }
    
    public String getCellKey(int col, int row) {
        return col + "_" + row;
    }

    public StringProperty getCellTextProperty(int col, int row) {
        String cellKey = getCellKey(col, row);
        return officeHours.get(cellKey);
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }
    
    public void setOfficeHours(HashMap<String, StringProperty> oh){
        officeHours = oh;
    }
    public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }

    public String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        boolean isMorn = time.contains("am");
        int milHour = hour;
        if (!isMorn)
            if(!(hour == 12))
                milHour += 12;
        row += (milHour - startHour) * 2;
        if (time.contains("_30"))
            row += 1;
        return getCellKey(col, row);
    }
    
    public TeachingAssistant getTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return ta;
            }
        }
        return null;
    }
    
    /**
     * This method is for giving this data manager the string property
     * for a given cell.
     */
    public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, prop);
    }    
    
    /**
     * This method is for setting the string property for a given cell.
     */
    public void setGridProperty(ArrayList<ArrayList<StringProperty>> grid,
                                int column, int row, StringProperty prop) {
        grid.get(row).set(column, prop);
    }
    
    private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
            
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        ((TAView)workspaceComponent.getTAView()).reloadOfficeHoursGrid(this);
    }
    
    public void addCellToGrid(StringProperty x,int col, int row) {       
        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, x);
   }
    public void initOfficeHoursTest(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;
        
        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
    }
    public void changeOfficeHours(int startHour, int endHour){
        ArrayList<TimeSlot> officeHourTimes = TimeSlot.buildOfficeHoursList(this);
        String cellKey;
        boolean prompt = false;
        this.startHour = startHour;
        this.endHour = endHour;
        if(startHour > endHour){
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty("WRONG_HOURS_FORMAT_TITLE"), props.getProperty("WRONG_HOURS_FORMAT_MESSAGE"));
            return;
        }
        for(TimeSlot ts: officeHourTimes){
            if(Integer.parseInt(ts.getTime().substring(0,ts.getTime().indexOf("_"))) < startHour || (Integer.parseInt(ts.getTime().substring(0,ts.getTime().indexOf("_"))) >= endHour)){
                prompt = true;
                break;
            }
        }
        if(!undoRedo && prompt){
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            AppYesNoCancelDialogSingleton dialog = AppYesNoCancelDialogSingleton.getSingleton();
            dialog.show(props.getProperty("CHANGE_HOURS_PROMPT_TITLE"), props.getProperty("CHANGE_HOURS_PROMPT_MESSAGE"));
            switch (dialog.getSelection()) {
                case "Yes":
                    break;
                default:
                    return;
            }
        }
        undoRedo = false;
        this.startHour = startHour;
        this.endHour = endHour;
        TAView workspaceComponent = (TAView)((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getTAView();
        workspaceComponent.resetWorkspaceNoStack();
        officeHours.clear();
        workspaceComponent.reloadOfficeHoursGrid(this);
        for(TimeSlot ts : officeHourTimes){
            try{
                String[] s = ts.getTime().split("_");
                if(s[0].equals(startHour-1+""))
                    continue;
                addOfficeHoursReservation(ts.getDay(),ts.getTime(),ts.getName());
            }
            catch(Exception e){
                
            }
        }
        
    }
    
    public void setUndoRedo(boolean bool){
        undoRedo = bool;
    }
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }
    
    public void initHoursTest(String startHourText, String endHourText){
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHoursTest(initStartHour, initEndHour);
        }
    }

    public boolean containsTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
        }
        return false;
    }

    public void addTA(String initName, String initEmail, boolean initIsUndergrad) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail, initIsUndergrad);

        // ADD THE TA
        if (!containsTA(initName) || !containsTA(initEmail)) {
            teachingAssistants.add(ta);
        }
        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
    public void updateTA(TeachingAssistant ta, String name, String email){
        
        String cellKey = "";
        for(int col = 0; col < (endHour-startHour)*2; col++){
            for(int row = 0; row < 7; row++){
                cellKey = row + "_" + col;
                StringProperty cellProp = officeHours.get(cellKey);
                String cellText = cellProp.getValue();
                if(cellText.contains(ta.getName())){
                    cellProp.setValue(name);
                }
            }
            for(TeachingAssistant tas : teachingAssistants){
                if(tas.getName().equals(ta.getName())){
                    teachingAssistants.remove(tas);
                    break;
                }
            }
        }
        addTA(name,email,ta.getUndergrad());
        Collections.sort(teachingAssistants);
        RecitationData recData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
    }
   
    public void removeTA(String initName){
        //If ta is same as the string to remove, remove that TA from the list.
        String cellKey = "";
        for(int col = 0; col < (endHour-startHour)*2; col++){
            for(int row = 0; row < 7; row++){
                cellKey = row + "_" + col;
                StringProperty cellProp = officeHours.get(cellKey);
                String cellText = cellProp.getValue();
                if(cellText.contains(initName)){
                    removeTAFromCell(cellProp,initName);
                }
            }
        }
        for(TeachingAssistant ta : teachingAssistants){
            if(ta.getName().equals(initName)){
                teachingAssistants.remove(ta);
                break;
            }
        }
          
    }
    public void addOfficeHoursReservation(String day, String time, String taName) {
        String cellKey = getCellKey(day, time);
        toggleTAOfficeHours(cellKey, taName);
    }
    
    /**
     * This function toggles the taName in the cell represented
     * by cellKey. Toggle means if it's there it removes it, if
     * it's not there it adds it.
     */
    public void toggleTAOfficeHours(String cellKey, String taName) {
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();
        if(cellText.equals(taName)){
            cellText = cellText.replace(taName,"");
            cellProp.setValue(cellText);
        }
        else if(cellText.contains(taName)){
            cellText = cellText.replace("\n" + taName,"");
            cellProp.setValue(cellText);
        }
        else if(cellText.equals("")){
            cellProp.setValue(taName);
        }
        else{
            cellProp.setValue(cellText + "\n" + taName);
        }
    }
    
    /**
     * This method removes taName from the office grid cell
     * represented by cellProp.
     */
    public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        }
        // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        }
        // IT MUST BE ANOTHER TA IN THE CELL
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }
    
}
