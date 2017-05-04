package csg.data;

import csg.CourseSiteGeneratorApp;
import djf.components.AppDataComponent;

/**
 * This class serves as the data class for our Course Site Generator program. 
 * This class is a general class that contains all of the data components from 
 * each tab in our Course Site Generator
 * 
 * @author Itai Flam
 * @version 1.0
 */

public class CourseSiteGeneratorData implements AppDataComponent{
    //THE APP FOR WHEN WE NEED IT
    CourseSiteGeneratorApp app;
    
    //ONE OF EACH DATA PIECE
    CourseData course;
    ProjectData project;
    RecitationData recitation;
    ScheduleData schedule;
    TAData ta;
    boolean undo;
    boolean redo;
    
    /**
     * This constructor will setup the required data structures for
     * use.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp){
        app = initApp;
        course = new CourseData(app);
        project = new ProjectData(app);
        recitation = new RecitationData(app);
        schedule = new ScheduleData(app);
        ta = new TAData(app);
        undo = false;
        redo = false;
    }
    public void setUndo(boolean bool){
        undo = bool;
    }
    public void setRedo(boolean bool){
        redo = bool;
    }
    @Override
    public void resetData() {
        ta.getTeachingAssistants().clear();
        recitation.getRecitations().clear();
        schedule.getItems().clear();
        schedule.resetDates();
        project.getStudents().clear();
        project.getTeams().clear();
    }
    public CourseData getCourseData(){
        return course;
    }
    public ProjectData getProjectData(){
        return project;
    }
    public RecitationData getRecitationData(){
        return recitation;
    }
    public ScheduleData getScheduleData(){
        return schedule;
    }
    public TAData getTAData(){
        return ta;
    }
    /**
     * 
     */
    
}
