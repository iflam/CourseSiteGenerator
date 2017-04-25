
package csg;

import csg.data.CourseSiteGeneratorData;
import csg.file.CourseSiteGeneratorFiles;
import csg.style.CourseSiteGeneratorStyle;
import csg.view.CourseSiteGeneratorWorkspace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;
import jtps.jTPS;

/**
 * This class serves as the application class for our Course Site Generator program. 
 * Note that much of its behavior is inherited from AppTemplate, as defined in
 * the Desktop Java Framework. This app starts by loading all the UI-specific
 * settings like icon files and tooltips and other things, then the full 
 * User Interface is loaded using those settings. Note that this is a 
 * JavaFX application.
 * 
 * @author Itai Flam
 * @version 1.0
 */
public class CourseSiteGeneratorApp extends AppTemplate{
    private jTPS stack = new jTPS();
    public void buildAppComponentsHookNoWork(){
        dataComponent = new CourseSiteGeneratorData(this);
        fileComponent = new CourseSiteGeneratorFiles(this);
        styleComponent = new CourseSiteGeneratorStyle(this);
    }
    @Override
    public void buildAppComponentsHook() {
        // CONSTRUCT ALL FOUR COMPONENTS. NOTE THAT FOR THIS APP
        // THE WORKSPACE NEEDS THE DATA COMPONENT TO EXIST ALREADY
        // WHEN IT IS CONSTRUCTED, SO BE CAREFUL OF THE ORDER
        dataComponent = new CourseSiteGeneratorData(this);
        workspaceComponent = new CourseSiteGeneratorWorkspace(this);
        fileComponent = new CourseSiteGeneratorFiles(this);
        styleComponent = new CourseSiteGeneratorStyle(this);
    }
    public jTPS getStack(){
        return stack;
    }
    public void setStack(jTPS s){
        stack = s;
    }
    /**
     * This is where program execution begins. Since this is a JavaFX app it
     * will simply call launch, which gets JavaFX rolling, resulting in sending
     * the properly initialized Stage (i.e. window) to the start method inherited
     * from AppTemplate, defined in the Desktop Java Framework.
     * @param args
     */
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }
}
