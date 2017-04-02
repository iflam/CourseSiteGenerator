package csg.view;

import csg.CourseSiteGeneratorApp;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;

/**
 * This class serves as the workspace class for our Course Site Generator program. 
 * This class is a general class that contains all of the workspace (view) components from 
 * each tab in our Course Site Generator
 * 
 * @author Itai Flam
 * @version 1.0
 */
public class CourseSiteGeneratorWorkspace extends AppWorkspaceComponent{
    //Provides access to the App Components
    CourseSiteGeneratorApp app;
    //For interactions
    CourseSiteGeneratorController controller;
    //Each tab's view
    CourseView course;
    ProjectView project;
    RecitationView recitation;
    ScheduleView schedule;
    TAView ta;
    View currentView;
    
    public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp initApp){
        app = initApp;
        currentView = ta;
    }
    
    @Override
    public void resetWorkspace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
