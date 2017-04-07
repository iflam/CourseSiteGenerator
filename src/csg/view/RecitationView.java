/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.Recitation;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class RecitationView {
    //ALL THE OBJECTS WE'LL NEED
    Label recTitleLabel;
    Button deleteButton;
    Button addUpdateButton;
    Button clearButton;
    TableView recitationTable;
    GridPane lowGridPane;
    //lower grid pane stuff
    Label addEditLabel;
    Label sectionLabel;
    Label instructorLabel;
    Label dayTimeLabel;
    Label locationLabel;
    Label taLabel;
    Label supervisingTALabel;
    Label supervisingTALabel2;
    TextField sectionTF;
    TextField instructorTF;
    TextField dayTimeTF;
    TextField locationTF;
    ComboBox supervisingTACB1;
    ComboBox supervisingTACB2;
    VBox recvBox;
    HBox titlehBox;
    CourseSiteGeneratorApp app;
    TableColumn<Recitation,String> sectionCol;
    TableColumn<Recitation,String> instructorCol;
    TableColumn<Recitation,String> dayTimeCol;
    TableColumn<Recitation,String> locationCol;
    TableColumn<Recitation,String> taCol1;
    TableColumn<Recitation,String> taCol2;
    ObservableList<Recitation> recitations = FXCollections.observableArrayList();
    
    public RecitationView(CourseSiteGeneratorApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        recTitleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.RECITATION_TITLE_TEXT.toString()));
        deleteButton = new Button(props.getProperty(CourseSiteGeneratorProp.DELETE_BUTTON_TEXT.toString()));
        titlehBox = new HBox();
        titlehBox.getChildren().addAll(recTitleLabel,deleteButton);
        recitationTable = new TableView();
        sectionLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SECTION_TEXT.toString()));
        instructorLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_TEXT.toString()));
        dayTimeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.DAY_TIME_TEXT.toString()));
        locationLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LOCATION_TEXT.toString()));
        taLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TA_TEXT.toString()));
        sectionCol = new TableColumn(sectionLabel.getText());
        instructorCol = new TableColumn(instructorLabel.getText());
        dayTimeCol = new TableColumn(dayTimeLabel.getText());
        taCol1 = new TableColumn(taLabel.getText());
        taCol2 = new TableColumn(taLabel.getText());
        recitationTable.getColumns().addAll(sectionCol,instructorCol,dayTimeCol,taCol1,taCol2);
        recvBox = new VBox();
        //BOTTOM
        addEditLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ADD_EDIT_TEXT.toString()));
        sectionTF = new TextField(props.getProperty(CourseSiteGeneratorProp.SECTION_SUG.toString()));
        instructorTF = new TextField(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_SUG.toString()));
        dayTimeTF = new TextField(props.getProperty(CourseSiteGeneratorProp.DAY_TIME_SUG.toString()));
        locationTF = new TextField(props.getProperty(CourseSiteGeneratorProp.LOCATION_SUG.toString()));
        supervisingTALabel = new Label(props.getProperty(CourseSiteGeneratorProp.SUPERVISING_TA_TEXT.toString()));
        supervisingTALabel2 = new Label(props.getProperty(CourseSiteGeneratorProp.SUPERVISING_TA_TEXT.toString()));
        supervisingTACB1 = new ComboBox();
        supervisingTACB2 = new ComboBox();
        addUpdateButton = new Button(props.getProperty(CourseSiteGeneratorProp.ADD_UPDATE_TEXT.toString()));
        clearButton = new Button(props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString()));
        GridPane.setConstraints(addEditLabel, 0, 0);
        GridPane.setConstraints(sectionLabel, 0, 1);
        GridPane.setConstraints(sectionTF, 1, 1);
        GridPane.setConstraints(instructorLabel, 0, 2);
        GridPane.setConstraints(instructorTF, 1, 2);
        GridPane.setConstraints(dayTimeLabel, 0, 3);
        GridPane.setConstraints(dayTimeTF, 1, 3);
        GridPane.setConstraints(locationLabel, 0, 4);
        GridPane.setConstraints(locationTF, 1, 4);
        GridPane.setConstraints(supervisingTALabel, 0, 5);
        GridPane.setConstraints(supervisingTALabel2, 0, 6);
        GridPane.setConstraints(supervisingTACB1, 1, 5);
        GridPane.setConstraints(supervisingTACB2, 1, 6);
        GridPane.setConstraints(addUpdateButton,0,7);
        GridPane.setConstraints(clearButton,1,7);
        lowGridPane = new GridPane();
        lowGridPane.getChildren().addAll(addEditLabel,sectionLabel,sectionTF,instructorLabel,instructorTF,dayTimeLabel,dayTimeTF,
                locationLabel,locationTF,supervisingTALabel,supervisingTALabel2,supervisingTACB1,supervisingTACB2,
                addUpdateButton, clearButton);
        recvBox.getChildren().addAll(titlehBox,recitationTable,lowGridPane);
        
    }
    
    public VBox getGUI(){
        return recvBox;
    }

}
