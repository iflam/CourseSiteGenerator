package csg.style;

import csg.CourseSiteGeneratorApp;
import djf.AppTemplate;
import djf.components.AppStyleComponent;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import csg.data.TeachingAssistant;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.TAView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * This class manages all CSS style for this application.
 * 
 * @author Richard McKenna
 * @version 1.0
 */
public class CourseSiteGeneratorStyle extends AppStyleComponent {
    // THIS PROVIDES ACCESS TO OTHER COMPONENTS
    private CourseSiteGeneratorApp app;
    private TAStyle ta;
    private ScheduleStyle schedule;
    private RecitationStyle recitation;
    private ProjectStyle project;
    private CourseStyle course;
    public static String APP_TAB = "tab";
    public static String BACKGROUND = "back_pane";
    
    /**
     * This constructor initializes all style for the application.
     * 
     * @param initApp The application to be stylized.
     */
    public CourseSiteGeneratorStyle(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
        super.initStylesheet(app);
        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        BorderPane workspace = (BorderPane)app.getWorkspaceComponent().getWorkspace();
    }
    
    public void initStyleSheet(){
        course = new CourseStyle(app);
        course.initCourseStyle();
        project = new ProjectStyle(app);
        project.initProjectStyle();
        recitation = new RecitationStyle(app);
        recitation.initRecitationStyle();
        schedule = new ScheduleStyle(app);
        schedule.initScheduleStyle();
        ta = new TAStyle(app);
        ta.initTAStyle();
        
    }

    public TAStyle getTAStyle() {
        return ta;
    }

    public ScheduleStyle getScheduleStyle() {
        return schedule;
    }

    public RecitationStyle getRecitationStyle() {
        return recitation;
    }

    public ProjectStyle getProjectStyle() {
        return project;
    }

    public CourseStyle getCourseStyle() {
        return course;
    }
    
    
}