/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.test_bed;

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
import csg.file.TimeSlot;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.CourseView;
import csg.view.TAView;
import djf.components.AppDataComponent;
import static djf.settings.AppPropertyType.APP_TITLE;
import static djf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_MESSAGE;
import static djf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import static djf.settings.AppStartupConstants.PATH_DATA;
import static djf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import properties_manager.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class TestSave{
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
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";
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
    static final String JSON_PROJECT_STUDENTS_FN = "firstName";
    static final String JSON_PROJECT_STUDENTS_LN = "lastName";
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
    
    static CourseSiteGeneratorApp app;
    static ArrayList<TimeSlot> officeHours;
    public static void main(String[] args){
        app = new CourseSiteGeneratorApp();
        app.loadProperties(APP_PROPERTIES_FILE_NAME);
        try{
            app.buildAppComponentsHook();
        }
        catch(ExceptionInInitializerError e){
            System.out.println("Bloop");
        }
        //COURSE DETAILS
        CourseData courseData = ((CourseSiteGeneratorData)app.getDataComponent()).getCourseData();
        courseData.addData("CSE", "219", "Winter", "2017", "Titlething", "Itai Flam", "Xin Yang",
                "./work", "None", "file:./images/Exit.png", "file:./images/Save.png", "file:./images/Load.png","yourFace.css");
        RecitationData recitationData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
        //TADATA
        TAData taData = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        try{
            taData.initHours("2","18");
        }
        catch(NullPointerException e){
            System.out.println("Bloop initHours");
        }
        StringProperty testMonday = new SimpleStringProperty("MONDAY");
        StringProperty testTuesday = new SimpleStringProperty("TUESDAY");
        taData.addCellToGrid(testMonday, 2, 24);
        taData.addCellToGrid(testTuesday, 3, 14);
        taData.addTA("Xin Yang", "x.y@s.u", true);
        taData.addTA("Itai Flam","i.f@s.u",true);
        taData.addOfficeHoursReservation("MONDAY", "1_30pm", "Itai Flam");
        taData.addOfficeHoursReservation("TUESDAY", "8_30am", "Xin Yang");
        officeHours = new ArrayList();
        officeHours.add(new TimeSlot("MONDAY","1_30pm","Itai Flam"));
        officeHours.add(new TimeSlot("TUESDAY","8_30am","Xin Yang"));
        //RECITATION
        recitationData.addRecitation("A","B","C","D","E","F");
        recitationData.addRecitation("1","2","3","4","5","6");
        //SCHEDULE
        ScheduleData taSchedule = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        taSchedule.addItem("Holiday", "06_22", "C","D","E","F","G");
        taSchedule.addItem("Lecture","07_27","3","4","5","6","7");
        taSchedule.addItem("Recitation","05_02","my","face","is","very","cool");
        taSchedule.setStartDate("04_18_2018");
        taSchedule.setEndDate("08_24_2018");
        ProjectData projectData = ((CourseSiteGeneratorData)app.getDataComponent()).getProjectData();
        projectData.addTeam("Shwaftas", "#ffff00", "#000000", "www.myface.com");
        projectData.addTeam("Beluga Whales", "#b4e2AA", "#000000", "www.belugas.com");
        projectData.addStudent("Itai", "Flam", "Captain", "Shwaftas");
        projectData.addStudent("Xin","Yang", "Programmer", "Shwaftas");
        projectData.addStudent("Your", "Face", "Head", "Beluga Whales");
        try{
	saveData(app.getDataComponent(), "./work/SiteSaveTest.json");
        }
        catch(Exception e){
            System.out.println("Bloop save");
        }
    }
    
    public static void saveData(AppDataComponent data, String filePath) throws IOException {
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
        CourseData courseData = ((CourseSiteGeneratorData)app.getDataComponent()).getCourseData();
        JsonObject courseObject = Json.createObjectBuilder()
                .add(JSON_COURSE_SUBJECT, courseData.getSubject())
                .add(JSON_COURSE_NUMBER, courseData.getNumber())
                .add(JSON_COURSE_SEMESTER, courseData.getSemester())
                .add(JSON_COURSE_YEAR, courseData.getYear())
                .add(JSON_COURSE_TITLE, courseData.getTitle())
                .add(JSON_COURSE_IN, courseData.getiName())
                .add(JSON_COURSE_IH, courseData.getiHome())
                .add(JSON_COURSE_EXPORTDIR, courseData.getExDir())
                .add(JSON_COURSE_TEMPLATEDIR,courseData.getTempDir())
                .add(JSON_COURSE_BANNER, courseData.getBannerDir())
                .add(JSON_COURSE_LFOOT, courseData.getlFtDir())
                .add(JSON_COURSE_RFOOT, courseData.getrFtDir())
                .add(JSON_COURSE_SS, courseData.getStyleSheet())
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
}
