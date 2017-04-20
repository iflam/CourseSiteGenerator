package csg.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseData;
import csg.data.CourseSiteGeneratorData;
import csg.data.ProjectData;
import csg.data.Recitation;
import csg.data.RecitationData;
import csg.data.ScheduleData;
import csg.data.ScheduleItem;
import csg.data.Student;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.CourseView;
import csg.view.ScheduleView;
import java.math.BigDecimal;

/**
 * This class serves as the file component for the TA
 * manager app. It provides all saving and loading 
 * services for the application.
 * 
 * @author Richard McKenna
 * @coauthor Itai Flam
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
    // THIS IS THE APP ITSELF
    CourseSiteGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_TA_DATA = "ta_data";
    //RECITATION
    static final String JSON_RECITATION_DATA = "recitation_data";
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAYTIME = "day_time";
    static final String JSON_LOC = "location";
    static final String JSON_TA1 = "ta1";
    static final String JSON_TA2 = "ta2";
    //SCHEDULE
    static final String JSON_SCHEDULE_DATA = "schedule_data";
    static final String JSON_SCHEDULE_START = "startDate";
    static final String JSON_SCHEDULE_END = "endDate";
    static final String JSON_SCHEDULE_ITEMS = "schedule_items";
    static final String JSON_SCHEDULE_TYPE = "type";
    static final String JSON_SCHEDULE_DATE = "date";
    static final String JSON_SCHEDULE_TIME = "time";
    static final String JSON_SCHEDULE_TOPIC = "topic";
    static final String JSON_SCHEDULE_TITLE = "title";
    static final String JSON_SCHEDULE_LINK = "link";
    static final String JSON_SCHEDULE_CRITERIA = "criteria";
    //PROJECT
    static final String JSON_PROJECT_DATA = "project_data";
    static final String JSON_PROJECT_TEAMS = "teams";
    static final String JSON_PROJECT_TEAM_NAME = "name";
    static final String JSON_PROJECT_TEAM_COLOR = "color";
    static final String JSON_PROJECT_TEAM_TEXTCOLOR = "text_color";
    static final String JSON_PROJECT_TEAM_LINK = "link";
    static final String JSON_PROJECT_STUDENTS = "students";
    static final String JSON_PROJECT_STUDENTS_FN = "first_name";
    static final String JSON_PROJECT_STUDENTS_LN = "last_name";
    static final String JSON_PROJECT_STUDENTS_ROLE = "role";
    static final String JSON_PROJECT_STUDENTS_TEAM = "team";
    //COURSE
    static final String JSON_COURSE_DATA = "course_data";
    static final String JSON_COURSE_SUBJECT = "subject";
    static final String JSON_COURSE_NUMBER = "number";
    static final String JSON_COURSE_SEMESTER = "semester";
    static final String JSON_COURSE_YEAR = "year";
    static final String JSON_COURSE_TITLE = "title";
    static final String JSON_COURSE_IN = "instructor_name";
    static final String JSON_COURSE_IH = "instructor_home";
    static final String JSON_COURSE_EXPORTDIR = "export_dir";
    static final String JSON_COURSE_TEMPLATEDIR = "template_dir";
    static final String JSON_COURSE_BANNER = "banner_image";
    static final String JSON_COURSE_LFOOT = "left_footer_image";
    static final String JSON_COURSE_RFOOT = "right_footer_image";
    static final String JSON_COURSE_SS = "style_sheet";
    
    
    
    public CourseSiteGeneratorFiles(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
        ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getTAView().resetWorkspace();
	TAData dataManager = ((CourseSiteGeneratorData)data).getTAData();

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);

        // NOW GET THE TA DATA STUFF
        JsonObject jsonTAData = json.getJsonObject(JSON_TA_DATA);
        // LOAD THE START AND END HOURS
	String startHour = jsonTAData.getString(JSON_START_HOUR);
        String endHour = jsonTAData.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);
        // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
        app.getWorkspaceComponent().reloadWorkspace(app.getDataComponent());
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonUndergradTAArray = jsonTAData.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonUndergradTAArray.size(); i++) {
            JsonObject jsonTA = jsonUndergradTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = null;
            try{
                email = jsonTA.getString(JSON_EMAIL);
            }
            catch(NullPointerException e){
                email = null;
            }
            dataManager.addTA(name,email,true);
        }
        // NOW LOAD ALL THE GRAD TAs
        JsonArray jsonGradTAArray = jsonTAData.getJsonArray(JSON_GRAD_TAS);
        for (int i = 0; i < jsonGradTAArray.size(); i++) {
            JsonObject jsonTA = jsonGradTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = null;
            try{
                email = jsonTA.getString(JSON_EMAIL);
            }
            catch(NullPointerException e){
                email = null;
            }
            dataManager.addTA(name,email,false);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = jsonTAData.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        //LOAD RECITATIONS
        RecitationData recitationData = ((CourseSiteGeneratorData)data).getRecitationData();
        JsonObject jsonRecitationObject = json.getJsonObject(JSON_RECITATION_DATA);
        JsonArray jsonRecitationArray = jsonRecitationObject.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRec = jsonRecitationArray.getJsonObject(i);
            String section = jsonRec.getString(JSON_SECTION);
            String instructor = jsonRec.getString(JSON_INSTRUCTOR);
            String dayTime = jsonRec.getString(JSON_DAYTIME);
            String loc = jsonRec.getString(JSON_LOC);
            String ta1 = jsonRec.getString(JSON_TA1);
            String ta2 = jsonRec.getString(JSON_TA2);
            recitationData.addRecitation(section, instructor, dayTime, instructor, ta1, ta2);
        }
        
        //LOAD SCHEDULES
        ScheduleData scheduleData = ((CourseSiteGeneratorData)data).getScheduleData();
        ScheduleView scheduleView = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView();
        JsonObject jsonScheduleObject = json.getJsonObject(JSON_SCHEDULE_DATA);
        String start = jsonScheduleObject.getString(JSON_SCHEDULE_START);
        String end = jsonScheduleObject.getString(JSON_SCHEDULE_END);
        scheduleView.setStartDate(start);
        scheduleView.setEndDate(end);
        JsonArray jsonItemsArray = jsonScheduleObject.getJsonArray(JSON_SCHEDULE_ITEMS);
        for (int i = 0; i < jsonItemsArray.size(); i++) {
            JsonObject jsonItem = jsonItemsArray.getJsonObject(i);
            String type = jsonItem.getString(JSON_SCHEDULE_TYPE);
            String date = jsonItem.getString(JSON_SCHEDULE_DATE);
            String title = jsonItem.getString(JSON_SCHEDULE_TITLE);
            String time = jsonItem.getString(JSON_SCHEDULE_TIME);
            String topic = jsonItem.getString(JSON_SCHEDULE_TOPIC);
            String link = jsonItem.getString(JSON_SCHEDULE_LINK);
            String criteria = jsonItem.getString(JSON_SCHEDULE_CRITERIA);
            scheduleData.addItem(type,date,title,time,topic,link,criteria);
        }
        
        //LOAD PROJECT DATA
        ProjectData projectData = ((CourseSiteGeneratorData)data).getProjectData();
        JsonObject jsonProjectObject = json.getJsonObject(JSON_PROJECT_DATA);
        JsonArray jsonTeamsArray = jsonProjectObject.getJsonArray(JSON_PROJECT_TEAMS);
        for (int i = 0; i < jsonTeamsArray.size(); i++) {
            JsonObject jsonTeam = jsonTeamsArray.getJsonObject(i);
            String name = jsonTeam.getString(JSON_PROJECT_TEAM_NAME);
            String color = jsonTeam.getString(JSON_PROJECT_TEAM_COLOR);
            String textColor = jsonTeam.getString(JSON_PROJECT_TEAM_TEXTCOLOR);
            String link = jsonTeam.getString(JSON_PROJECT_TEAM_LINK);
            projectData.addTeam(name, color, textColor, link);
        }
        JsonArray jsonStudentsArray = jsonProjectObject.getJsonArray(JSON_PROJECT_STUDENTS);
        for (int i = 0; i < jsonStudentsArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentsArray.getJsonObject(i);
            String fn = jsonStudent.getString(JSON_PROJECT_STUDENTS_FN);
            String ln = jsonStudent.getString(JSON_PROJECT_STUDENTS_LN);
            String role = jsonStudent.getString(JSON_PROJECT_STUDENTS_ROLE);
            String team = jsonStudent.getString(JSON_PROJECT_STUDENTS_TEAM);
            projectData.addStudent(fn, ln, role, team);         
        }
        
        //LOAD COURSE DETAILS
        CourseView courseView = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getCourseView();
        JsonObject jsonCourseObject = json.getJsonObject(JSON_COURSE_DATA);
        String subject = jsonCourseObject.getString(JSON_COURSE_SUBJECT);
        String number = jsonCourseObject.getString(JSON_COURSE_NUMBER);
        String semester = jsonCourseObject.getString(JSON_COURSE_SEMESTER);
        String year = jsonCourseObject.getString(JSON_COURSE_YEAR);
        String title = jsonCourseObject.getString(JSON_COURSE_TITLE);
        String in = jsonCourseObject.getString(JSON_COURSE_IN);
        String ih = jsonCourseObject.getString(JSON_COURSE_IH);
        String eDir = jsonCourseObject.getString(JSON_COURSE_EXPORTDIR);
        String tDir = jsonCourseObject.getString(JSON_COURSE_TEMPLATEDIR);
        String bPath = jsonCourseObject.getString(JSON_COURSE_BANNER);
        String lfPath = jsonCourseObject.getString(JSON_COURSE_LFOOT);
        String rfPath = jsonCourseObject.getString(JSON_COURSE_RFOOT);
        String styleSheet = jsonCourseObject.getString(JSON_COURSE_SS);
        courseView.setCourseItems(subject, number, semester, year, title, in, ih, eDir, tDir, bPath, lfPath, rfPath, styleSheet);
    }
      
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	TAData dataManager = ((CourseSiteGeneratorData)data).getTAData();
	// NOW BUILD THE TA JSON OBJECTS TO SAVE
	JsonArrayBuilder taUndergradArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder taGradArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {
            if(ta.getUndergrad()){
                JsonObject taJson = Json.createObjectBuilder()
                     .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
                taUndergradArrayBuilder.add(taJson);
            }
            else{
               JsonObject taJson = Json.createObjectBuilder()
                     .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
                taGradArrayBuilder.add(taJson); 
            }
	}
	JsonArray undergradTAsArray = taUndergradArrayBuilder.build();
        JsonArray gradTAsArray = taGradArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject taDataJSO = Json.createObjectBuilder()
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
		.build();
        //RECITATION TAB
        RecitationData recitationData = ((CourseSiteGeneratorData)data).getRecitationData();
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
        for(Recitation r: recitationData.getRecitations()){
            JsonObject recitationJson = Json.createObjectBuilder()
                    .add(JSON_SECTION, r.getSection())
                    .add(JSON_INSTRUCTOR, r.getInstructor())
                    .add(JSON_DAYTIME, r.getDayTime())
                    .add(JSON_LOC, r.getLocation())
                    .add(JSON_TA1, r.getTa1())
                    .add(JSON_TA2, r.getTa2())
                    .build();                   
                recitationArrayBuilder.add(recitationJson);
        }
        JsonObject recitationDataJson = Json.createObjectBuilder().add(JSON_RECITATIONS, recitationArrayBuilder).build();
	
        //SCHEDULE TAB
        ScheduleData scheduleData = ((CourseSiteGeneratorData)data).getScheduleData();
        JsonArrayBuilder itemsArrayBuilder = Json.createArrayBuilder();
        for(ScheduleItem s: scheduleData.getItems()){
            JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_SCHEDULE_TYPE, s.getType())
                    .add(JSON_SCHEDULE_DATE, s.getDate())
                    .add(JSON_SCHEDULE_TITLE, s.getTitle())
                    .add(JSON_SCHEDULE_TIME, s.getTime())
                    .add(JSON_SCHEDULE_TOPIC, s.getTopic())
                    .add(JSON_SCHEDULE_LINK, s.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, s.getCriteria())
                    .build();
            itemsArrayBuilder.add(scheduleJson);
        }
        JsonObject scheduleObject = Json.createObjectBuilder()
                .add(JSON_SCHEDULE_START, scheduleData.getStartDate())
                .add(JSON_SCHEDULE_END, scheduleData.getEndDate())
                .add(JSON_SCHEDULE_ITEMS, itemsArrayBuilder)
                .build();
                
        //PROJECT TAB
        ProjectData projectData = ((CourseSiteGeneratorData)data).getProjectData();
        JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
        for(Team t: projectData.getTeams()){
            JsonObject teamJson = Json.createObjectBuilder()
                    .add(JSON_PROJECT_TEAM_NAME, t.getName())
                    .add(JSON_PROJECT_TEAM_COLOR,t.getColor())
                    .add(JSON_PROJECT_TEAM_TEXTCOLOR, t.getTextColor())
                    .add(JSON_PROJECT_TEAM_LINK, t.getLink())
                    .build();
            teamsArrayBuilder.add(teamJson);
        }
        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
        for(Student s: projectData.getStudents()){
            JsonObject studentJson = Json.createObjectBuilder()
                    .add(JSON_PROJECT_STUDENTS_FN, s.getFirstName())
                    .add(JSON_PROJECT_STUDENTS_LN, s.getLastName())
                    .add(JSON_PROJECT_STUDENTS_ROLE, s.getRole())
                    .add(JSON_PROJECT_STUDENTS_TEAM, s.getTeam())
                    .build();
            studentsArrayBuilder.add(studentJson);
        }
        JsonObject projectObject = Json.createObjectBuilder()
                .add(JSON_PROJECT_TEAMS, teamsArrayBuilder)
                .add(JSON_PROJECT_STUDENTS, studentsArrayBuilder)
                .build();
        
        //COURSE DETAILS
        CourseView courseView = ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getCourseView();
        JsonObject courseObject = Json.createObjectBuilder()
                .add(JSON_COURSE_SUBJECT, courseView.getSubjectTF().getText())
                .add(JSON_COURSE_NUMBER, courseView.getNumberTF().getText())
                .add(JSON_COURSE_SEMESTER, courseView.getSemesterCB().getSelectionModel().getSelectedItem().toString())
                .add(JSON_COURSE_YEAR, courseView.getYearCB().getSelectionModel().getSelectedItem().toString())
                .add(JSON_COURSE_TITLE, courseView.getTitleTF().getText())
                .add(JSON_COURSE_IN, courseView.getInstructorNameTF().getText())
                .add(JSON_COURSE_IH, courseView.getInstructorHomeTF().getText())
                .add(JSON_COURSE_EXPORTDIR, courseView.getExportDirLabel().getText())
                .add(JSON_COURSE_TEMPLATEDIR,courseView.getMidvBoxDir().getText())
                .add(JSON_COURSE_BANNER, courseView.getPaths()[0])
                .add(JSON_COURSE_LFOOT, courseView.getPaths()[1])
                .add(JSON_COURSE_RFOOT, courseView.getPaths()[2])
                .add(JSON_COURSE_SS, courseView.getStylesheetCB().getSelectionModel().getSelectedItem().toString())
                .build();
        
        //NOW PUT ALL IN MASTER OBJECT
        JsonObject masterObject = Json.createObjectBuilder()
                .add(JSON_COURSE_DATA, courseObject)
                .add(JSON_TA_DATA,taDataJSO)
                .add(JSON_RECITATION_DATA, recitationDataJson)
                .add(JSON_SCHEDULE_DATA, scheduleObject)
                .add(JSON_PROJECT_DATA, projectObject)
                .build();
        
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(masterObject);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(masterObject);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
        
        
    }
    
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}