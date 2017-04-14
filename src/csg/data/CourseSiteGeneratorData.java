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
    
    /**
     * This constructor will setup the required data structures for
     * use.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp){
        app = initApp;
        course = new CourseData();
        project = new ProjectData();
        recitation = new RecitationData();
        schedule = new ScheduleData();
        ta = new TAData(app);
    }
    @Override
    public void resetData() {
        ta.getTeachingAssistants().clear();
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
    public TAData getTAData(){
        return ta;
    }
    /**
     * 
     */
    
}
