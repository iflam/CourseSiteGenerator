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
import static djf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import static djf.settings.AppStartupConstants.PATH_WORK;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class CourseView{
    CourseSiteGeneratorApp app;
    private VBox vBox;
    //TOP PANE STUFFS
    private GridPane topGridPane;
    private Label topGPLabel;
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
    private GridPane lowGridPane;
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
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //vertical box, with 3 children
        vBox = new VBox();
        topGridPane = new GridPane();
        topGPLabel = new Label(props.getProperty(CourseSiteGeneratorProp.COURSE_INFO_TEXT.toString()));
        topGridPane.getChildren().add(topGPLabel);
        //FIRST CHILD: TOP GRIDPANE
        //comboboxes and textfields in the order they appear
        //props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString())
        subjectLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString()));
        GridPane.setConstraints(subjectLabel, 0, 1);
        subjectTF = new TextField();
        GridPane.setConstraints(subjectTF,1,1);
        subjectTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.SUBJECT_TEXT.toString()));
        numberLabel = new Label(props.getProperty(CourseSiteGeneratorProp.NUMBER_TEXT.toString()));
        GridPane.setConstraints(numberLabel, 3, 1);
        numberTF = new TextField();
        GridPane.setConstraints(numberTF, 4, 1);
        numberTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.NUMBER_TEXT.toString()));
        topGridPane.getChildren().addAll(subjectLabel,subjectTF,numberLabel,numberTF);
        //next
        semesterLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SEMESTER_TEXT.toString()));
        GridPane.setConstraints(semesterLabel, 0, 2);
        semesterCB = new ComboBox();
        GridPane.setConstraints(semesterCB, 1, 2);
        semesterCB.getItems().addAll(
                props.getProperty(CourseSiteGeneratorProp.SUMMER_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.FALL_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.WINTER_TEXT.toString())
                ,props.getProperty(CourseSiteGeneratorProp.SPRING_TEXT.toString()));
        semesterCB.getSelectionModel().selectFirst();
        yearLabel = new Label(props.getProperty(CourseSiteGeneratorProp.YEAR_TEXT.toString()));
        GridPane.setConstraints(yearLabel, 3, 2);
        yearCB = new ComboBox();
        GridPane.setConstraints(yearCB, 4, 2);
        for(int i = 2017; i < 2100; i++){
            yearCB.getItems().add(i+"");
        }
        yearCB.getSelectionModel().selectFirst();
        topGridPane.getChildren().addAll(semesterLabel,semesterCB,yearLabel,yearCB);
        //next
        titleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TITLE_TEXT.toString()));
        GridPane.setConstraints(titleLabel, 0, 3);
        titleTF = new TextField();
        GridPane.setConstraints(titleTF, 1, 4, 3, 1);
        titleTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.TITLE_DEFAULT_TEXT.toString()));
        topGridPane.getChildren().addAll(titleLabel,titleTF);
        //next
        instructorNameLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_NAME_TEXT.toString()));
        GridPane.setConstraints(instructorNameLabel,0,5);
        instructorNameTF = new TextField();
        GridPane.setConstraints(instructorNameTF, 1, 5, 3, 1);
        instructorNameTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_NAME_DEFAULT_TEXT.toString()));
        topGridPane.getChildren().addAll(instructorNameLabel,instructorNameTF);
        //next
        instructorHomeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_HOME_TEXT.toString()));
        GridPane.setConstraints(instructorHomeLabel, 0, 6);
        instructorHomeTF = new TextField();
        GridPane.setConstraints(instructorHomeTF, 1, 6, 3, 1);
        instructorHomeTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_HOME_DEFAULT_TEXT.toString()));
        topGridPane.getChildren().addAll(instructorHomeLabel,instructorHomeTF);
        //next
        exportLabel = new Label(props.getProperty(CourseSiteGeneratorProp.EXPORT_DIRECTORY_TEXT.toString()));
        GridPane.setConstraints(exportLabel, 0, 7);
        exportDirLabel = new Label(props.getProperty(CourseSiteGeneratorProp.EXPORT_DIRECTORY_DEFAULT_TEXT.toString()));
        GridPane.setConstraints(exportDirLabel,1,7,3,1);
        changeButton = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeButton, 4, 7);
        topGridPane.setHgap(10);
        topGridPane.setVgap(5);
        topGridPane.setPadding(new Insets(10,10,10,10));
        topGridPane.getChildren().addAll(exportLabel,exportDirLabel,changeButton);
        ////////////////////////////////////////////////////////////////////////////////
        //MIDDLE BOX
        midvBoxLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SITE_TEMPLATE_TEXT.toString()));
        midvBoxDesc = new Label(props.getProperty(CourseSiteGeneratorProp.SELECTED_TEMPLATE_TEXT.toString()));
        midvBoxDir = new Label(props.getProperty(CourseSiteGeneratorProp.TEMPLATE_DEFAULT_TEXT.toString()));
        midTempDirButton = new Button(props.getProperty(CourseSiteGeneratorProp.SELECT_TEMPLATE_TEXT.toString()));
        sitePageLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SITE_PAGES_TEXT.toString()));
        templateTable = new TableView();
        midvBox = new VBox();
        midvBox.getChildren().addAll(midvBoxLabel,midvBoxDesc,midvBoxDir,midTempDirButton,sitePageLabel,templateTable);
        //////////////////////////////////////////////////////////////
        //LOWER BOX
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON.toString());
        lowGridPane = new GridPane();
        pageStyleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.PAGE_STYLE_TEXT.toString()));
        GridPane.setConstraints(pageStyleLabel, 0, 0);
        bannerImgLabel = new Label(props.getProperty(CourseSiteGeneratorProp.BANNER_IMG_TEXT.toString()));
        GridPane.setConstraints(bannerImgLabel,0,1);
        leftFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LEFT_IMG_TEXT.toString()));
        GridPane.setConstraints(leftFtLabel,0,2);
        rightFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.RIGHT_IMG_TEXT.toString()));
        GridPane.setConstraints(rightFtLabel,0,3);
        lowGridPane.getChildren().addAll(pageStyleLabel,bannerImgLabel,leftFtLabel,rightFtLabel);
        //
        styleSheetLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_TEXT.toString()));
        GridPane.setConstraints(styleSheetLabel,0,4);
        styleSheetMessageLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_MESSAGE.toString()));
        GridPane.setConstraints(styleSheetMessageLabel,0,5);
        lowGridPane.getChildren().addAll(styleSheetLabel,styleSheetMessageLabel);
        bannerImage = new ImageView(imagePath);
        GridPane.setConstraints(bannerImage,1,1);
        bannerImage.setFitHeight(554/6);
        bannerImage.setFitWidth((1816/4)+100);
        leftFtImage = new ImageView(imagePath);
        GridPane.setConstraints(leftFtImage,1,2);
        leftFtImage.setFitHeight(554/6);
        leftFtImage.setFitWidth((1816/4)+100);
        rightFtImage = new ImageView(imagePath);
        GridPane.setConstraints(rightFtImage,1,3);
        rightFtImage.setFitHeight(554/6);
        rightFtImage.setFitWidth((1816/4)+100);
        lowGridPane.getChildren().addAll(bannerImage,leftFtImage,rightFtImage);
        changeBanner = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeBanner,3,1);
        changeLeft = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeLeft,3,2);
        changeRight = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeRight,3,3);
        stylesheetCB = new ComboBox();
        GridPane.setConstraints(stylesheetCB, 2, 4);
        lowGridPane.getChildren().addAll(changeBanner,changeLeft,changeRight,stylesheetCB);
        lowGridPane.setHgap(10);
        lowGridPane.setVgap(5);
        lowGridPane.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(topGridPane,midvBox,lowGridPane);
        vBox.setPadding(new Insets(10,10,10,10));
        
        //ACTION IN HERE
        changeButton.setOnAction(e->{
	
        });
        
                
    }
    public VBox getvBox(){
        return vBox;
    }
}
