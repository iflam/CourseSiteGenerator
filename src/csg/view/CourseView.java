/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.CourseData;
import csg.data.CourseSiteGeneratorData;
import csg.data.SitePage;
import static djf.settings.AppPropertyType.EXPORT_WORK_TITLE;
import static djf.settings.AppPropertyType.IMG_FILE_EXT;
import static djf.settings.AppPropertyType.IMG_FILE_EXT_DESC;
import static djf.settings.AppPropertyType.JPG_FILE_EXT;
import static djf.settings.AppPropertyType.JPG_FILE_EXT_DESC;
import static djf.settings.AppPropertyType.LOAD_ERROR_MESSAGE;
import static djf.settings.AppPropertyType.LOAD_ERROR_TITLE;
import static djf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static djf.settings.AppPropertyType.WORK_FILE_EXT;
import static djf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_HERE;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import static djf.settings.AppStartupConstants.PATH_WORK;
import djf.ui.AppMessageDialogSingleton;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class CourseView{
    CourseSiteGeneratorApp app;
    private VBox vBox;
    private ScrollPane thisGUI;
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
    private TableView<SitePage> templateTable;
    
    //BOTTOM PANE STUFF
    private GridPane lowGridPane;
    private VBox lowvBox;
    private String bannerImageDir;
    private String leftFtDir;
    private String rightFtDir;
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
        CourseData data = ((CourseSiteGeneratorData)app.getDataComponent()).getCourseData();
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
        templateTable = new TableView<SitePage>();
        ObservableList<SitePage> sitePages = FXCollections.observableArrayList();
        TableColumn<SitePage, Boolean> useCol = new TableColumn(props.getProperty(CourseSiteGeneratorProp.USE_TEXT.toString()));
        useCol.setCellValueFactory(new PropertyValueFactory<SitePage,Boolean>("use"));
        useCol.setCellFactory(CheckBoxTableCell.forTableColumn(useCol));
        TableColumn<SitePage, String> titleCol = new TableColumn(props.getProperty(CourseSiteGeneratorProp.NAVBAR_TITLE_TEXT.toString()));
        titleCol.setCellValueFactory(new PropertyValueFactory<SitePage,String>("title"));
        TableColumn<SitePage, String> fileCol = new TableColumn(props.getProperty(CourseSiteGeneratorProp.FILE_NAME_TEXT.toString()));
        fileCol.setCellValueFactory(new PropertyValueFactory<SitePage,String>("file"));
        TableColumn<SitePage, String> scriptCol = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SCRIPT_TEXT.toString()));
        scriptCol.setCellValueFactory(new PropertyValueFactory<SitePage,String>("script"));
        useCol.prefWidthProperty().bind(templateTable.widthProperty().divide(4)); // w * 1/4
        titleCol.prefWidthProperty().bind(templateTable.widthProperty().divide(4)); // w * 1/4
        fileCol.prefWidthProperty().bind(templateTable.widthProperty().divide(4)); // w * 1/4
        scriptCol.prefWidthProperty().bind(templateTable.widthProperty().divide(4)); // w * 1/4
        templateTable.setEditable(true);
        templateTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        templateTable.getColumns().addAll(useCol, titleCol, fileCol, scriptCol);
        templateTable.setItems(sitePages);
        midvBox = new VBox();
        midvBox.getChildren().addAll(midvBoxLabel,midvBoxDesc,midvBoxDir,midTempDirButton,sitePageLabel,templateTable);
        //////////////////////////////////////////////////////////////
        //LOWER BOX
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON.toString());
        bannerImageDir = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON.toString());
        leftFtDir = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON.toString());
        rightFtDir = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProp.DEFAULT_PICTURES_ICON.toString());
        lowGridPane = new GridPane();
        lowvBox = new VBox();
        pageStyleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.PAGE_STYLE_TEXT.toString()));
        bannerImgLabel = new Label(props.getProperty(CourseSiteGeneratorProp.BANNER_IMG_TEXT.toString()));
        GridPane.setConstraints(bannerImgLabel,0,0);
        leftFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LEFT_IMG_TEXT.toString()));
        GridPane.setConstraints(leftFtLabel,0,1);
        rightFtLabel = new Label(props.getProperty(CourseSiteGeneratorProp.RIGHT_IMG_TEXT.toString()));
        GridPane.setConstraints(rightFtLabel,0,2);
        lowGridPane.getChildren().addAll(pageStyleLabel,bannerImgLabel,leftFtLabel,rightFtLabel);
        //
        bannerImage = new ImageView(imagePath);
        GridPane.setConstraints(bannerImage,1,0);
        bannerImage.setFitHeight(554/6);
        bannerImage.setFitWidth((1816/4)+100);
        leftFtImage = new ImageView(imagePath);
        GridPane.setConstraints(leftFtImage,1,1);
        leftFtImage.setFitHeight(554/6);
        leftFtImage.setFitWidth((1816/4)+100);
        rightFtImage = new ImageView(imagePath);
        GridPane.setConstraints(rightFtImage,1,2);
        rightFtImage.setFitHeight(554/6);
        rightFtImage.setFitWidth((1816/4)+100);
        lowGridPane.getChildren().addAll(bannerImage,leftFtImage,rightFtImage);
        changeBanner = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeBanner,3,0);
        changeLeft = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeLeft,3,1);
        changeRight = new Button(props.getProperty(CourseSiteGeneratorProp.CHANGE_TEXT.toString()));
        GridPane.setConstraints(changeRight,3,2);
        styleSheetLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_TEXT.toString()));
        stylesheetCB = new ComboBox();
        File[] f = new File("./work/css").listFiles();
        ObservableList<String> styleSheets = FXCollections.observableArrayList();
        for(File a : f){
            if(a.getName().endsWith(".css"))
                styleSheets.add(a.getName());
        }
        stylesheetCB.setItems(styleSheets);
        stylesheetCB.getSelectionModel().selectFirst();
        lowGridPane.getChildren().addAll(changeBanner,changeLeft,changeRight);
        lowGridPane.setHgap(10);
        lowGridPane.setVgap(5);
        lowGridPane.setPadding(new Insets(10,10,10,10));
        styleSheetMessageLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STYLESHEET_MESSAGE.toString()));
        ssHbox = new HBox(styleSheetLabel,stylesheetCB);
        lowvBox.getChildren().addAll(pageStyleLabel,lowGridPane,ssHbox, styleSheetMessageLabel);
        vBox.getChildren().addAll(topGridPane,midvBox,lowvBox);
        vBox.setPadding(new Insets(10,10,10,10));
        thisGUI = new ScrollPane(vBox);
        thisGUI.setFitToWidth(true);
        thisGUI.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thisGUI.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //ACTION IN HERE
        changeButton.setOnAction(e->{
            String directory = promptForDirectory(true);
            data.setExDir(directory);
            exportDirLabel.setText(directory);
        });
        midTempDirButton.setOnAction(e->{
            String directory = promptForDirectory(true);
            data.setTempDir(directory);
            midvBoxDir.setText(directory);
        });
        changeBanner.setOnAction(e->{
            String directory = promptForDirectory(false);
            data.setBannerDir(directory);
            bannerImage = new ImageView("file:"+directory);
        });
        subjectTF.setOnAction(e->{
            data.setSubject(subjectTF.getText());
        });
        numberTF.setOnAction(e->{
            data.setNumber(numberTF.getText());
        });
        semesterCB.setOnAction(e->{
            data.setSemester(semesterCB.getSelectionModel().getSelectedItem().toString());
        });
        yearCB.setOnAction(e->{
            data.setYear(yearCB.getSelectionModel().getSelectedItem().toString());
        });
        titleTF.setOnAction(e->{
            data.setTitle(titleTF.getText());
        });
        instructorNameTF.setOnAction(e->{
            data.setiName(instructorNameTF.getText());
        });
        instructorHomeTF.setOnAction(e->{
            data.setiHome(instructorHomeTF.getText());
        });
        
        
    }
    
    private String promptForDirectory(Boolean isDirectory){
        // WE'LL NEED THIS TO GET CUSTOM STUFF
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        if(!isDirectory){
        // PROMPT THE USER FOR A FILE NAME
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_WORK));
        fc.setTitle(props.getProperty(SAVE_WORK_TITLE));
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter(props.getProperty(IMG_FILE_EXT_DESC), props.getProperty(IMG_FILE_EXT)),
        new FileChooser.ExtensionFilter(props.getProperty(JPG_FILE_EXT_DESC),props.getProperty(JPG_FILE_EXT)));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
        return selectedFile.getAbsolutePath();
        }
        else{
        DirectoryChooser fc = new DirectoryChooser();
        fc.setInitialDirectory(new File(PATH_HERE));
	fc.setTitle(props.getProperty(EXPORT_WORK_TITLE));
        File selectedDirectory = fc.showDialog(app.getGUI().getWindow());
        return selectedDirectory.getAbsolutePath();
        }
    }
    public VBox getLowvBox(){
        return lowvBox;
    }

    public VBox getvBox() {
        return vBox;
    }

    public GridPane getTopGridPane() {
        return topGridPane;
    }

    public ComboBox getSemesterCB() {
        return semesterCB;
    }

    public ComboBox getYearCB() {
        return yearCB;
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public VBox getMidvBox() {
        return midvBox;
    }

    public Button getMidTempDirButton() {
        return midTempDirButton;
    }

    public TableView getTemplateTable() {
        return templateTable;
    }

    public GridPane getLowGridPane() {
        return lowGridPane;
    }

    public Button getChangeBanner() {
        return changeBanner;
    }

    public Button getChangeLeft() {
        return changeLeft;
    }

    public Button getChangeRight() {
        return changeRight;
    }

    public HBox getBannerHbox() {
        return bannerHbox;
    }

    public HBox getLeftHbox() {
        return leftHbox;
    }

    public HBox getRightHbox() {
        return rightHbox;
    }

    public HBox getSsHbox() {
        return ssHbox;
    }

    public ComboBox getStylesheetCB() {
        return stylesheetCB;
    }

    public Label getTopGPLabel() {
        return topGPLabel;
    }

    public Label getSubjectLabel() {
        return subjectLabel;
    }

    public Label getNumberLabel() {
        return numberLabel;
    }

    public Label getSemesterLabel() {
        return semesterLabel;
    }

    public Label getYearLabel() {
        return yearLabel;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getInstructorNameLabel() {
        return instructorNameLabel;
    }

    public Label getInstructorHomeLabel() {
        return instructorHomeLabel;
    }

    public Label getExportLabel() {
        return exportLabel;
    }

    public Label getExportDirLabel() {
        return exportDirLabel;
    }

    public TextField getSubjectTF() {
        return subjectTF;
    }

    public TextField getNumberTF() {
        return numberTF;
    }

    public TextField getTitleTF() {
        return titleTF;
    }

    public TextField getInstructorNameTF() {
        return instructorNameTF;
    }

    public TextField getInstructorHomeTF() {
        return instructorHomeTF;
    }

    public Label getMidvBoxLabel() {
        return midvBoxLabel;
    }

    public Label getMidvBoxDesc() {
        return midvBoxDesc;
    }

    public Label getMidvBoxDir() {
        return midvBoxDir;
    }

    public Label getSitePageLabel() {
        return sitePageLabel;
    }

    public Label getPageStyleLabel() {
        return pageStyleLabel;
    }

    public Label getBannerImgLabel() {
        return bannerImgLabel;
    }

    public Label getLeftFtLabel() {
        return leftFtLabel;
    }

    public Label getRightFtLabel() {
        return rightFtLabel;
    }

    public Label getStyleSheetLabel() {
        return styleSheetLabel;
    }

    public Label getStyleSheetMessageLabel() {
        return styleSheetMessageLabel;
    }

    public ImageView getBannerImage() {
        return bannerImage;
    }

    public ImageView getLeftFtImage() {
        return leftFtImage;
    }

    public ImageView getRightFtImage() {
        return rightFtImage;
    }
    
    public ScrollPane getGUI(){
        return thisGUI;
    }
    /**
     * 0 is Banner, 1 is Left, 2 is Right
     * @return 
     */
    public String[] getPaths(){
        String[] s = {bannerImageDir,leftFtDir,rightFtDir};
        return s;
    }
    
    public void setCourseItems(String subject, String number, String semester,
            String year, String title, String in, String ih, String eDir, String tDir, 
            String bPath, String lfPath, String rfPath, String styleSheet){
        subjectTF.setText(subject);
        numberTF.setText(number);
        semesterCB.getSelectionModel().select(semesterCB.getItems().indexOf(semester));
        yearCB.getSelectionModel().select(yearCB.getItems().indexOf(year));
        titleTF.setText(title);
        instructorNameTF.setText(in);
        instructorHomeTF.setText(ih);
        exportDirLabel.setText(eDir);
        midvBoxDir.setText(tDir);
        bannerImage.setImage(new Image(bPath));
        bannerImageDir = bPath;
        leftFtImage.setImage(new Image(lfPath));
        leftFtDir = lfPath;
        rightFtImage.setImage(new Image(rfPath));
        rightFtDir = rfPath;
        stylesheetCB.getSelectionModel().select(stylesheetCB.getItems().indexOf(styleSheet));
        
    }
}
