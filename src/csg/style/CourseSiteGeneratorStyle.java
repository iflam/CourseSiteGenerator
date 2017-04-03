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
    private Style currentStyle;
    
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
        ta = new TAStyle(app);
        currentStyle = ta;
        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        currentStyle.initWorkspaceStyle();
    }
    
    public Style getCurrentStyle(){
        return currentStyle;
    }
    
}