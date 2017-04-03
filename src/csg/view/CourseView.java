/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import static csg.CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class CourseView{
    CourseSiteGeneratorApp app;
    private VBox vBox;
    //TOP PANE STUFFS
    private VBox topvBox;
    private HBox subNum;
    private HBox semYe;
    private HBox titleBox;
    private HBox inBox;
    private HBox ihBox;
    private HBox exBox;
    private Label topvBoxLabel;
    private Label subjectLabel;
    private Label numberLabel;
    private Label semesterLabel;
    private Label yearLabel;
    private Label titleLabel;
    private Label instructorNameLabel;
    private Label instructorHomeLabel;
    private Label exportLabel;
    private Label exportDirLabel;
    private TextField subjectTF;
    private TextField numberTF;
    private TextField titleTF;
    private TextField instructorNameTF;
    private TextField instructorHomeTF;
    private ComboBox semesterCB;
    private ComboBox yearCB;
    private Button changeButton;
    
    //MIDDLE PANE STUFF
    private VBox midvBox;
    private Label midvBoxLabel;
    private Label midvBoxDesc;
    private Label midvBoxDir;
    private Button midTempDirButton;
    private Label sitePageLabel;
    private TableView templateTable;
    
    //BOTTOM PANE STUFF
    private VBox lowvBox;
    private Label pageStyleLabel;
    private Label bannerImgLabel;
    private Label leftFtLabel;
    private Label rightFtLabel;
    private Label styleSheetLabel;
    private Label styleSheetMessageLabel;
    private ImageView bannerImage;
    private ImageView leftFtImage;
    private ImageView rightFtImage;
    private Button changeBanner;
    private Button changeLeft;
    private Button changeRight;
    private HBox bannerHbox;
    private HBox leftHbox;
    private HBox rightHbox;
    private HBox ssHbox;
    private ComboBox stylesheetCB;
    
    public CourseView(CourseSiteGeneratorApp initApp){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //vertical box, with 3 children
        vBox = new VBox();
        //FIRST CHILD: TOP PANE
        topvBox = new VBox();
        topvBoxLabel = new Label("Course Info");
        topvBox.getChildren().add(topvBoxLabel);
        //comboboxes and textfields in the order they appear
        //props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString())
        subjectLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString()));
        subjectTF = new TextField();
        subjectTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString()));
        numberLabel = new Label(props.getProperty(CourseSiteGeneratorProp.NUMBER_TEXT.toString()));
        numberTF = new TextField();
        numberTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.NUMBER_TEXT.toString()));
        subNum = new HBox();
        subNum.getChildren().addAll(subjectLabel,subjectTF,numberLabel,numberTF);
        //next
        semesterLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SEMESTER_TEXT.toString()));
        semesterCB = new ComboBox();
        semesterCB.getItems().addAll(
                props.getProperty(CourseSiteGeneratorProp.SUMMER_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.FALL_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.WINTER_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.SPRING_TEXT.toString()));
        yearLabel = new Label(props.getProperty(CourseSiteGeneratorProp.YEAR_TEXT.toString()));
        yearCB = new ComboBox();
        for(int i = 2017; i < 2100; i++){
            yearCB.getItems().add(i+"");
        }
        semYe = new HBox();
        semYe.getChildren().addAll(semesterLabel,semesterCB,yearLabel,yearCB);
        //next
        titleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TITLE_TEXT.toString()));
        titleTF = new TextField();
        titleTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.TITLE_DEFAULT_TEXT.toString()));
        titleBox = new HBox();
        titleBox.getChildren().addAll(titleLabel,titleTF);
        //next
        instructorNameLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_NAME_TEXT.toString()));
        instructorNameTF = new TextField();
        instructorNameTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_NAME_DEFAULT_TEXT.toString()));
        inBox = new HBox();
        inBox.getChildren().addAll(instructorNameLabel,instructorNameTF);
        //next
        instructorHomeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_HOME_TEXT.toString()));
        instructorHomeTF = new TextField();
        instructorHomeTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_HOME_DEFAULT_TEXT.toString()));
        ihBox = new HBox();
        ihBox.getChildren().addAll(instructorHomeLabel,instructorHomeTF);
        //next
        exportLabel = new Label(props.getProperty(CourseSiteGeneratorProp.EXPORT_DIRECTORY_TEXT.toString()));
        exportDirLabel = new Label(props.getProperty(CourseSiteGeneratorProp.EXPORT_DIRECTORY_DEFAULT_TEXT.toString()));
        changeButton = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        exBox = new HBox();
        exBox.getChildren().addAll(exportLabel,exportDirLabel,changeButton);
        //next
        topvBox.getChildren().addAll(subNum,semYe,titleBox,inBox,ihBox,exBox);
        //MIDDLE BOX
        midvBoxLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SITE_TEMPLATE_TEXT.toString()));
        midvBoxDesc = new Label(props.getProperty(CourseSiteGeneratorProp.SELECTED_TEMPLATE_TEXT.toString()));
        midvBoxDir = new Label(props.getProperty(CourseSiteGeneratorProp.TEMPLATE_DEFAULT_TEXT.toString()));
        midTempDirButton = new Button(props.getProperty(CourseSiteGeneratorProp.SELECT_TEMPLATE_TEXT.toString()));
        sitePageLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SITE_PAGES_TEXT.toString()));
        templateTable = new TableView();
        midvBox = new VBox();
        midvBox.getChildren().addAll(midvBoxLabel,midvBoxDesc,midvBoxDir,midTempDirButton,sitePageLabel,templateTable);
        //LOWER BOX
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + DEFAULT_PICTURES_ICON.toString();
        pageStyleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.PAGE_STYLE_TEXT.toString()));
        bannerImgLabel = new Label(props.getProperty(CourseSiteGeneratorProp.BANNER_IMG_TEXT.toString()));
        leftFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LEFT_IMG_TEXT.toString()));
        rightFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.RIGHT_IMG_TEXT.toString()));
        styleSheetLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_TEXT.toString()));
        styleSheetMessageLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_MESSAGE.toString()));
        bannerImage = new ImageView(imagePath);
        leftFtImage = new ImageView(imagePath);
        rightFtImage = new ImageView(imagePath);
        changeBanner = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        changeLeft = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        changeRight = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        lowvBox = new VBox();
        bannerHbox = new HBox(bannerImgLabel,bannerImage,changeBanner);
        leftHbox = new HBox(leftFtLabel,leftFtImage,changeLeft);
        rightHbox = new HBox(rightFtLabel,rightFtImage,changeRight);
        stylesheetCB = new ComboBox();
        ssHbox = new HBox(styleSheetLabel,stylesheetCB);
        lowvBox.getChildren().addAll(pageStyleLabel,bannerHbox,leftHbox,rightHbox,ssHbox,styleSheetMessageLabel);
        vBox.getChildren().addAll(topvBox,midvBox,lowvBox);
        
                
    }
    public VBox getvBox(){
        return vBox;
    }
}
