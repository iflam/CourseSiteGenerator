package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import djf.ui.AppGUI;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

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
    AppGUI gui;
    CourseSiteGeneratorApp app;
    //For interactions
    CourseSiteGeneratorController controller;
    //Each tab's view
    CourseView course;
    ProjectView project;
    RecitationView recitation;
    ScheduleView schedule;
    TAView ta;
    TabPane tp;
    Tab currentTab;
    
    public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp initApp){
        app = initApp;
        TabPane tp = new TabPane();
        Tab courseTab = new Tab("Course Details");
        courseTab.setClosable(false);
        Tab projectTab = new Tab("Project Data");
        projectTab.setClosable(false);
        Tab recitationTab = new Tab("Recitation Data");
        recitationTab.setClosable(false);
        Tab taTab = new Tab("TA Data");
        taTab.setClosable(false);
        Tab scheduleTab = new Tab("Schedule Data");
        scheduleTab.setClosable(false);
        tp.setPadding(new Insets(10,10,10,10));
        tp.getTabs().addAll(courseTab,taTab,recitationTab,scheduleTab,projectTab);
        workspace = new BorderPane();
        ((BorderPane) workspace).setCenter(tp);
        controller = new CourseSiteGeneratorController(app);
        course = new CourseView(app);
        courseTab.setContent(course.getvBox());
    }
    
    @Override
    public void resetWorkspace() {
        
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        app.getGUI().getAppPane().setCenter(workspace);
    }
    
    public TAView getTAView(){
        return ta;
    }
    public CourseView getCourseView(){
        return course;
    }
    public ProjectView getProjectView(){
        return project;
    }
    public ScheduleView getScheduleView(){
        return schedule;
    }
    public RecitationView getRecitationView(){
        return recitation;
    }
}
