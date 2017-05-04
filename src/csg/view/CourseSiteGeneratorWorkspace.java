package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.CourseSiteGeneratorData;
import csg.style.CourseSiteGeneratorStyle;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import static djf.settings.AppPropertyType.ABOUT_ICON;
import static djf.settings.AppPropertyType.ABOUT_TOOLTIP;
import static djf.settings.AppPropertyType.REDO_ICON;
import static djf.settings.AppPropertyType.REDO_TOOLTIP;
import static djf.settings.AppPropertyType.UNDO_ICON;
import static djf.settings.AppPropertyType.UNDO_TOOLTIP;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import djf.ui.AppGUI;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import properties_manager.PropertiesManager;

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
    FlowPane undoRedoToolbar;
    Button undoButton;
    Button redoButton;
    Button aboutButton;
    
    public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp initApp){
        app = initApp;
        course = new CourseView(app);
        project = new ProjectView(app);
        recitation = new RecitationView(app);
        schedule = new ScheduleView(app);
        ta = new TAView(app);    
        controller = new CourseSiteGeneratorController(app);
        initButtons();
        KeyCombination ctrlZ = KeyCodeCombination.keyCombination("Ctrl+z");
        KeyCombination ctrlY = KeyCodeCombination.keyCombination("Ctrl+y");
        app.getGUI().getPrimaryScene().setOnKeyPressed(e ->{
            if(ctrlZ.match(e)){
                controller.handleCtrlz(); //Handle control z, now go back to TAController and finish that by 
                //undoing transactions in there, reference jTPS files for help.
            }
            if(ctrlY.match(e)){
                controller.handleCtrly();
            }
        });
        undoButton.setOnAction(e->{
            controller.handleCtrlz();
        });
        redoButton.setOnAction(e->{
            controller.handleCtrly();
        });
    }
    
    @Override
    public void resetWorkspace() {
        PropertiesManager props = PropertiesManager.getPropertiesManager(); 
        tp = new TabPane();
        Tab courseTab = new Tab(props.getProperty(CourseSiteGeneratorProp.COURSE_DETAILS_TEXT.toString()));
        courseTab.setClosable(false);
        Tab projectTab = new Tab(props.getProperty(CourseSiteGeneratorProp.PROJECT_DATA_TEXT.toString()));
        projectTab.setClosable(false);
        Tab recitationTab = new Tab(props.getProperty(CourseSiteGeneratorProp.RECITATION_DATA_TEXT.toString()));
        recitationTab.setClosable(false);
        Tab taTab = new Tab(props.getProperty(CourseSiteGeneratorProp.TA_DATA_TEXT.toString()));
        taTab.setClosable(false);
        Tab scheduleTab = new Tab(props.getProperty(CourseSiteGeneratorProp.SCHEDULE_DATA_TEXT.toString()));
        scheduleTab.setClosable(false);
        tp.getTabs().addAll(courseTab,taTab,recitationTab,scheduleTab,projectTab);
        workspace = new BorderPane();
        ((BorderPane) workspace).setCenter(tp);
        controller = new CourseSiteGeneratorController(app);
        //INITIALIZE ALL THE TABS
        course = new CourseView(app);
        courseTab.setContent(course.getGUI());
        recitation = new RecitationView(app);
        recitationTab.setContent(recitation.getGUI());
        schedule = new ScheduleView(app);
        scheduleTab.setContent(schedule.getGUI());
        project = new ProjectView(app);
        projectTab.setContent(project.getGUI());
        ta = new TAView(app);
        taTab.setContent(ta.getGUI());
        ((CourseSiteGeneratorStyle)(app.getStyleComponent())).initStyleSheet();
        app.getStack().clearStack();
    }

    public void initButtons(){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        undoRedoToolbar = new FlowPane();
        String undoPath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(UNDO_ICON.toString());
        String redoPath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(REDO_ICON.toString());
        String aboutPath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(ABOUT_ICON.toString());
        Image undoImage = new Image(undoPath);
        Image redoImage = new Image(redoPath);
        Image aboutImage = new Image(aboutPath);
        undoButton = new Button();
        redoButton = new Button();
        aboutButton = new Button();
        undoButton.setDisable(true);
        redoButton.setDisable(true);
        aboutButton.setDisable(false);
        undoButton.setGraphic(new ImageView(undoImage));
        redoButton.setGraphic(new ImageView(redoImage));
        aboutButton.setGraphic(new ImageView(aboutImage));
        Tooltip undoButtonTooltip = new Tooltip(props.getProperty(UNDO_TOOLTIP.toString()));
        Tooltip redoButtonTooltip = new Tooltip(props.getProperty(REDO_TOOLTIP.toString()));
        Tooltip aboutButtonTooltip = new Tooltip(props.getProperty(ABOUT_TOOLTIP.toString()));
        undoButton.setTooltip(undoButtonTooltip);
        redoButton.setTooltip(redoButtonTooltip);
        aboutButton.setTooltip(aboutButtonTooltip);
        // PUT THE BUTTON IN THE TOOLBAR
        undoRedoToolbar.getChildren().addAll(undoButton,redoButton,aboutButton);
        app.getGUI().getTopFlowPane().getChildren().add(undoRedoToolbar);
    }
    public void setUndo(boolean isNotFirst){
        undoButton.setDisable(!isNotFirst);
    }
    public void setRedo(boolean isNotLast){
        redoButton.setDisable(!isNotLast);
    }
    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        app.getGUI().getAppPane().setCenter(workspace);
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)dataComponent;
        ta.reloadWorkspace(data);
        
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
    public TabPane getTabPane(){
        return tp;
    }
}
