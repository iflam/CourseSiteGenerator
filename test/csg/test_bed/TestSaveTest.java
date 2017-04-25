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
import csg.data.RecitationData;
import csg.data.ScheduleData;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import csg.file.CourseSiteGeneratorFiles;
import csg.file.TimeSlot;
import static csg.test_bed.TestSave.app;
import static csg.test_bed.TestSave.officeHours;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Itai
 */
public class TestSaveTest {
    static CourseSiteGeneratorApp app;
    static CourseData courseData;
    static TAData taData;
    static RecitationData recitationData;
    static ScheduleData scheduleData;
    static ProjectData projectData;
    static ArrayList<TimeSlot> officeHours;
    public TestSaveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        app = new CourseSiteGeneratorApp();
        app.loadProperties(APP_PROPERTIES_FILE_NAME);
        try{
            app.buildAppComponentsHookNoWork();
        }
        catch(NullPointerException e){}
        courseData = ((CourseSiteGeneratorData)app.getDataComponent()).getCourseData();
        taData = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        recitationData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
        scheduleData = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        projectData = ((CourseSiteGeneratorData)app.getDataComponent()).getProjectData();
        courseData.addData("CSE", "219", "Winter", "2017", "Titlething", "Itai Flam", "Xin Yang",
                "./work", "None", "file:./images/Exit.png", "file:./images/Save.png", "file:./images/Load.png","yourFace.css");
     
        //TADATA

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
        CourseSiteGeneratorFiles testFile = new CourseSiteGeneratorFiles(app);
        testFile.saveData(app.getDataComponent(), "./work/SiteSaveTest.json");
        try{
            testFile.loadDataTest(app.getDataComponent(), "./work/SiteSaveTest.json");
        }
        catch(Exception e){System.out.println("Could not load");}
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class TestSave.
     */
    @Test
    public void testMain() {
    }

    /**
     * Test of saveData method, of class TestSave.
     */
    @Test
    public void testSaveData() throws Exception {
    }
    
    //COURSE DETAILS TAB
    
    @Test
    public void subjectCheck(){
        assertEquals("CSE", courseData.getSubject());
    }
    @Test
    public void numberCheck(){
        assertEquals("219", courseData.getNumber());
    }
    @Test
    public void semesterCheck(){
        assertEquals("Winter", courseData.getSemester());
    }
    @Test
    public void yearCheck(){
        assertEquals("2017", courseData.getYear());
    }
    @Test
    public void titleCheck(){
        assertEquals("Titlething", courseData.getTitle());
    }
    @Test
    public void inCheck(){
        assertEquals("Itai Flam", courseData.getiName());
    }
    @Test
    public void ihCheck(){
        assertEquals("Xin Yang", courseData.getiHome());
    }
    @Test
    public void ExpDirCheck(){
        assertEquals("./work", courseData.getExDir());
    }
    @Test
    public void TempDirCheck(){
        assertEquals("None", courseData.getTempDir());
    }
    @Test
    public void bannerImgCheck(){
        assertEquals("file:./images/Exit.png", courseData.getBannerDir());
    }
    @Test
    public void lFtImgCheck(){
        assertEquals("file:./images/Save.png", courseData.getlFtDir());
    }
    @Test
    public void rFtDirCheck(){
        assertEquals("file:./images/Load.png", courseData.getrFtDir());
    }
    @Test
    public void stylesheetCheck(){
        assertEquals("yourFace.css", courseData.getStyleSheet());
    }
    
    //TA DATA TAB
    @Test
    public void taNameCheck(){
        assertEquals("Itai Flam",((TeachingAssistant)taData.getTeachingAssistants().get(0)).getName());
        assertEquals("Xin Yang",((TeachingAssistant)taData.getTeachingAssistants().get(2)).getName());
    }
    @Test
    public void taEmailCheck(){
        assertEquals("i.f@s.u",((TeachingAssistant)taData.getTeachingAssistants().get(0)).getEmail());
        assertEquals("x.y@s.u",((TeachingAssistant)taData.getTeachingAssistants().get(2)).getEmail());
    }
    @Test
    public void startTimeCheck(){
        assertEquals(2, taData.getStartHour());
    }
    @Test
    public void endTimeCheck(){
        assertEquals(18, taData.getEndHour());
    }
    //RECITATION DATA
    @Test
    public void sectionCheck(){
        assertEquals("A", (recitationData.getRecitations().get(0)).getSection());
        assertEquals("1", (recitationData.getRecitations().get(1)).getSection());
    }
    @Test
    public void instructorCheck(){
        assertEquals("B", (recitationData.getRecitations().get(0)).getInstructor());
        assertEquals("2", (recitationData.getRecitations().get(1)).getInstructor());
    }
    @Test
    public void dayTimeCheck(){
        assertEquals("C", (recitationData.getRecitations().get(0)).getDayTime());
        assertEquals("3", (recitationData.getRecitations().get(1)).getDayTime());
    }
    @Test
    public void locCheck(){
        assertEquals("D", (recitationData.getRecitations().get(0)).getLocation());
        assertEquals("4", (recitationData.getRecitations().get(1)).getLocation());
    }
    @Test
    public void ta1Check(){
        assertEquals("E", (recitationData.getRecitations().get(0)).getTa1());
        assertEquals("5", (recitationData.getRecitations().get(1)).getTa1());
    }
    @Test
    public void ta2Check(){
        assertEquals("F", (recitationData.getRecitations().get(0)).getTa2());
        assertEquals("6", (recitationData.getRecitations().get(1)).getTa2());
    }
    @Test
    public void startDateCheck(){
        assertEquals("04_18_2018", scheduleData.getStartDate());
    }
    @Test
    public void endDateCheck(){
        assertEquals("08_24_2018", scheduleData.getEndDate());
    }
    @Test
    public void typeCheck(){
        assertEquals("Holiday", scheduleData.getItems().get(0).getType());
        assertEquals("Lecture", scheduleData.getItems().get(1).getType());
        assertEquals("Recitation", scheduleData.getItems().get(2).getType());
    }
    @Test
    public void dateCheck(){
        assertEquals("06_22", scheduleData.getItems().get(0).getDate());
        assertEquals("07_27", scheduleData.getItems().get(1).getDate());
        assertEquals("05_02", scheduleData.getItems().get(2).getDate());
    }
    @Test
    public void titleSchedCheck(){
        assertEquals("C", scheduleData.getItems().get(0).getTitle());
        assertEquals("3", scheduleData.getItems().get(1).getTitle());
        assertEquals("my", scheduleData.getItems().get(2).getTitle());
    }
    @Test
    public void timeCheck(){
        assertEquals("D", scheduleData.getItems().get(0).getTime());
        assertEquals("4", scheduleData.getItems().get(1).getTime());
        assertEquals("face", scheduleData.getItems().get(2).getTime());
    }
    @Test
    public void topicCheck(){
        assertEquals("E", scheduleData.getItems().get(0).getTopic());
        assertEquals("5", scheduleData.getItems().get(1).getTopic());
        assertEquals("is", scheduleData.getItems().get(2).getTopic());
    }
    @Test
    public void linkCheck(){
        assertEquals("F", scheduleData.getItems().get(0).getLink());
        assertEquals("6", scheduleData.getItems().get(1).getLink());
        assertEquals("very", scheduleData.getItems().get(2).getLink());
    }
    @Test
    public void criteriaCheck(){
        assertEquals("G", scheduleData.getItems().get(0).getCriteria());
        assertEquals("7", scheduleData.getItems().get(1).getCriteria());
        assertEquals("cool", scheduleData.getItems().get(2).getCriteria());
    }
    //PROJECT
    @Test
    public void teamNameCheck(){
        assertEquals("Shwaftas", projectData.getTeams().get(0).getName());
        assertEquals("Beluga Whales", projectData.getTeams().get(1).getName());
    }
    @Test
    public void teamColorCheck(){
        assertEquals("#ffff00", projectData.getTeams().get(0).getColor());
        assertEquals("#b4e2AA", projectData.getTeams().get(1).getColor());
    }
    @Test
    public void teamTColorCheck(){
        assertEquals("#000000", projectData.getTeams().get(0).getTextColor());
        assertEquals("#000000", projectData.getTeams().get(1).getTextColor());
    }
    @Test
    public void teamLinkCheck(){
        assertEquals("www.myface.com", projectData.getTeams().get(0).getLink());
        assertEquals("www.belugas.com", projectData.getTeams().get(1).getLink());
    }
    @Test
    public void fnCheck(){
        assertEquals("Itai", projectData.getStudents().get(0).getFirstName());
        assertEquals("Xin", projectData.getStudents().get(1).getFirstName());
        assertEquals("Your", projectData.getStudents().get(2).getFirstName());
    }
    @Test
    public void lnCheck(){
        assertEquals("Flam", projectData.getStudents().get(0).getLastName());
        assertEquals("Yang", projectData.getStudents().get(1).getLastName());
        assertEquals("Face", projectData.getStudents().get(2).getLastName());
    }
    @Test
    public void roleCheck(){
        assertEquals("Captain", projectData.getStudents().get(0).getRole());
        assertEquals("Programmer", projectData.getStudents().get(1).getRole());
        assertEquals("Head", projectData.getStudents().get(2).getRole());
    }
    @Test
    public void teamCheck(){
        assertEquals("Shwaftas", projectData.getStudents().get(0).getTeam());
        assertEquals("Shwaftas", projectData.getStudents().get(1).getTeam());
        assertEquals("Beluga Whales", projectData.getStudents().get(2).getTeam());
    }
    
}
