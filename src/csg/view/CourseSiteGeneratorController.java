/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorProp.INCORRECT_FORMAT_TA_EMAIL_MESSAGE;
import static csg.CourseSiteGeneratorProp.INCORRECT_FORMAT_TA_EMAIL_TITLE;
import static csg.CourseSiteGeneratorProp.MISSING_TA_EMAIL_MESSAGE;
import static csg.CourseSiteGeneratorProp.MISSING_TA_EMAIL_TITLE;
import static csg.CourseSiteGeneratorProp.MISSING_TA_NAME_MESSAGE;
import static csg.CourseSiteGeneratorProp.MISSING_TA_NAME_TITLE;
import static csg.CourseSiteGeneratorProp.TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE;
import static csg.CourseSiteGeneratorProp.TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE;
import csg.data.CourseSiteGeneratorData;
import csg.data.RecitationData;
import csg.data.ScheduleData;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import djf.ui.AppMessageDialogSingleton;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;
import csg.transactions.AddTA_Transaction;
import csg.transactions.ChangeEnd_Transaction;
import csg.transactions.ChangeStart_Transaction;
import csg.transactions.HourChange_Transaction;
import csg.transactions.RemoveTA_Transaction;
import csg.transactions.ToggleTA_Transaction;
import csg.transactions.UpdateTA_Transaction;
import static djf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static djf.settings.AppStartupConstants.PATH_WORK;
import java.io.File;
import java.time.LocalDate;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Itai
 */
public class CourseSiteGeneratorController {

   // THE APP PROVIDES ACCESS TO OTHER COMPONENTS AS NEEDED
    CourseSiteGeneratorApp app;
    static boolean isUndoRedo;

    /**
     * Constructor, note that the app must already be constructed.
     * @param initApp
     */
    public CourseSiteGeneratorController(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        isUndoRedo = false;
    }
    /**
     * This method reponds to switching a tab's display
     * @param selectedItem
     */
    
    /**
     * This method responds to when the user requests to change the 
     * start date via the UI. Note that it must first do some
     * validation to make sure a unique name and email address
     * has been provided.
     * @param d
     */
    
    public void handleChangeStartDate(LocalDate d){
        if(isUndoRedo){
            isUndoRedo = false;
            return;
        }
        ScheduleData s = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        ScheduleView sV = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView();
        jTPS_Transaction transaction = new ChangeStart_Transaction(sV,s,d);
        app.getStack().addTransaction(transaction);
        app.getGUI().updateToolbarControls(false);
    }
    
    public void handleChangeEndDate(LocalDate d){
        if(isUndoRedo){
            isUndoRedo = false;
            return;
        }
        ScheduleData s = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        ScheduleView sV = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView();
        jTPS_Transaction transaction = new ChangeEnd_Transaction(sV,s,d);
        app.getStack().addTransaction(transaction);
        app.getGUI().updateToolbarControls(false);
    }
    public void handleAddTA() {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        TAView workspace = workspaceComponent.getTAView();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        
        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        TAData data = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));    
            if(email.isEmpty()){
                dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
        }
        }
        //DID THE USER NEGLECT TO PROVIDE AN EMAIL ADDRESS?
        else if(email.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
        }
        //IS THE EMAIL IN A BAD FORMAT?
        // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.containsTA(name)) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
        }
        // EVERYTHING IS FINE, ADD A NEW TA
        else {
            if(!email.matches("[A-Za-z0-9]+(\\.*[A-Za-z0-9]+)+\\@[A-Za-z0-9]+\\.[A-za-z0-9]+")){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INCORRECT_FORMAT_TA_EMAIL_TITLE), props.getProperty(INCORRECT_FORMAT_TA_EMAIL_MESSAGE));
            }
            else{
            // ADD THE NEW TA TO THE DATA
           // data.addTA(name,email);
           TeachingAssistant t = new TeachingAssistant(name,email,true);
           jTPS_Transaction transaction = new AddTA_Transaction(data,t);
           app.getStack().addTransaction(transaction);
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");
            
            
            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            }
        }
        app.getGUI().updateToolbarControls(false);
    }

    public void handleCtrlz(){
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.setRedo(true);
        TabPane x = ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).getTabPane();
        jTPS_Transaction currentTransaction = app.getStack().getCurrentTransaction();
        isUndoRedo = true;
        int currentTab = 0;
        switch(currentTransaction.getViewType()){
            case "courseTab":
                currentTab = 0;
                break;
            case "taTab":
                currentTab = 1;
                break;
            case "recitationTab":
                currentTab = 2;
                break;
            case "scheduleTab":
                currentTab = 3;
                break;
            case "projectTab":
                currentTab = 4;
                break;
        }
        x.getSelectionModel().select(currentTab);
        app.getStack().undoTransaction();
        if(app.getStack().isFirst()){
            data.setUndo(false);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setUndo(false);
        }
        if(app.getStack().isLast()){
            data.setRedo(false);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setRedo(false);
        }
        else{
            data.setRedo(true);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setRedo(true);
        }
        app.getGUI().updateToolbarControls(false);
    }
    
    public void handleCtrly(){
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        isUndoRedo = true;
        data.setRedo(true);
        TabPane x = ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).getTabPane();
        jTPS_Transaction currentTransaction = app.getStack().getNextTransaction();
        if(currentTransaction == null){
            return;
        }
        isUndoRedo = true;
        int currentTab = 0;
        switch(currentTransaction.getViewType()){
            case "courseTab":
                currentTab = 0;
                break;
            case "taTab":
                currentTab = 1;
                break;
            case "recitationTab":
                currentTab = 2;
                break;
            case "scheduleTab":
                currentTab = 3;
                break;
            case "projectTab":
                currentTab = 4;
                break;
        }
        x.getSelectionModel().select(currentTab);
        app.getStack().doTransaction();
        if(app.getStack().isFirst()){
            data.setUndo(false);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setUndo(false);
        }
        else{
            data.setUndo(true);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setUndo(true);
        }
        if(app.getStack().isLast()){
            data.setRedo(false);
            ((CourseSiteGeneratorWorkspace)(app.getWorkspaceComponent())).setRedo(false);
        }
        app.getGUI().updateToolbarControls(false);
    }
    /**
     * This function provides a response for when the user clicks
     * on the office hours grid to add or remove a TA to a time slot.
     * 
     * @param pane The pane that was toggled.
     */
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        TAView workspace = workspaceComponent.getTAView();
        TableView taTable = workspace.getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            TAData data = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
            String cellKey = pane.getId();

            // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
            jTPS_Transaction transaction = new ToggleTA_Transaction(data,ta,cellKey);
            app.getStack().addTransaction(transaction);
            app.getGUI().updateToolbarControls(false);
        }
    }
    
    public void handleDeleteTA(TeachingAssistant ta){
        //GET THE TA LIST
        TAData data = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        jTPS_Transaction transaction = new RemoveTA_Transaction(data,ta);
           app.getStack().addTransaction(transaction);
           app.getGUI().updateToolbarControls(false);
    }
    
    public void handleUpdateTA(TeachingAssistant ta, String name, String email){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TAData data = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        TAView workspace = workspaceComponent.getTAView();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        boolean notGood = false;
        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));    
            if(email.isEmpty()){
                dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
            notGood = true;
        }
        }
        //DID THE USER NEGLECT TO PROVIDE AN EMAIL ADDRESS?
        else if(email.isEmpty()){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
            notGood = true;
        }
        // DOES A DIFFERENT TA ALREADY HAVE THE SAME NAME?
        else if (data.containsTA(name)) {
            ObservableList<TeachingAssistant> teachingAssistants = data.getTeachingAssistants();
            int i = 0;
            for(TeachingAssistant t : teachingAssistants){
                if(t.getName().equals(name)){
                    i++;
                }
                if(ta.getEmail() == null){
                    break;
                }
                if((t == ta) &&(ta.getEmail().equals(email)))
                    i++;
                if(i>=2){
                    notGood = true;
                    break;
                }
            }
            if(notGood){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
            }
        }
        else {
            if(!email.matches("[A-Za-z0-9]+(\\.*[A-Za-z0-9]+)+\\@[A-Za-z0-9]+\\.[A-za-z0-9]+")){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INCORRECT_FORMAT_TA_EMAIL_TITLE), props.getProperty(INCORRECT_FORMAT_TA_EMAIL_MESSAGE));
            }
            else{
            // ADD THE NEW TA TO THE DATA
            RecitationData recData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
            jTPS_Transaction transaction = new UpdateTA_Transaction(recData,data,ta,name,email);
            app.getStack().addTransaction(transaction);
            nameTextField.requestFocus();
            notGood = true;
            }
        }
        if(!notGood){
            if(!email.matches("[A-Za-z0-9]+(\\.*[A-Za-z0-9]+)+\\@[A-Za-z0-9]+\\.[A-za-z0-9]+")){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INCORRECT_FORMAT_TA_EMAIL_TITLE), props.getProperty(INCORRECT_FORMAT_TA_EMAIL_MESSAGE));
            }
            RecitationData recData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
            jTPS_Transaction transaction = new UpdateTA_Transaction(recData,data,ta,name,email);
            app.getStack().addTransaction(transaction);
            nameTextField.requestFocus();
        }
        app.getGUI().updateToolbarControls(false);
    }
    public void handleHoursChange(String start, String end){
        int startTime;
        int endTime;
        TAData data = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        boolean startMorn = true;
        if(start.contains("pm"))
            startMorn = false;
        boolean endMorn = true;
        if(end.contains("pm"))
            endMorn = false;
        startTime = Integer.parseInt(start.substring(0,start.indexOf(":")));
        endTime = Integer.parseInt(end.substring(0,end.indexOf(":")));
        if(!startMorn){
            if(!(startTime == 12))
                startTime +=12;
        }
        else{
            if(startTime == 12){
                startTime = 0;
            }
        }
        if(!endMorn){
            if(!(endTime == 12))
                endTime+=12;
        }
        else{
            if(endTime == 12){
                endTime = 0;
            }
        }
        jTPS_Transaction transaction = new HourChange_Transaction(data,startTime,endTime,(TAView)((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getTAView());
        app.getStack().addTransaction(transaction);
        app.getGUI().updateToolbarControls(false);
    }
    
    //COURSE VIEW CONTROLLERS:
    //CHANGE EXPORT DIR
    public void handleChangeExportDir(){
      
        
    }
    
}
