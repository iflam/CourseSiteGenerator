/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.ScheduleItem;
import djf.components.AppDataComponent;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ScheduleView {
    CourseSiteGeneratorApp app;
    private VBox schedulevBox;
    GridPane topGrid;
    VBox lowvBox;
    GridPane lowGrid;
    //TOP
    Label sLabel;
    Label cbLabel;
    Label startingLabel;
    Label endingLabel;
    DatePicker startDate;
    DatePicker endDate;
    //BOTTOM
    Label siLabel;
    HBox sihBox;
    Button deleteButton;
    TableView scheduleTable;
    Label addEditLabel;
    Label typeLabel;
    ComboBox typeCB;
    Label dateLabel;
    DatePicker dateDP;
    Label timeLabel;
    TextField timeTF;
    Label titleLabel;
    TextField titleTF;
    Label topicLabel;
    TextField topicTF;
    Label linkLabel;
    TextField linkTF;
    Label criteriaLabel;
    TextField criteriaTF;
    Button addUpdateButton;
    Button clearButton;
    
    public ScheduleView(CourseSiteGeneratorApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //TOP
        sLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SCHEDULE_TEXT.toString()));
        cbLabel = new Label(props.getProperty(CourseSiteGeneratorProp.CB_TEXT.toString()));
        startingLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STARTING_TEXT.toString()));
        endingLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ENDING_TEXT.toString()));
        startDate = new DatePicker();
        endDate = new DatePicker();
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
        GridPane.setConstraints(cbLabel, 0, 0);
        GridPane.setConstraints(startingLabel, 0, 1);
        GridPane.setConstraints(endingLabel, 2, 1);
        GridPane.setConstraints(startDate, 1, 1);
        GridPane.setConstraints(endDate, 3, 1);
        topGrid = new GridPane();
        topGrid.getChildren().addAll(cbLabel, startingLabel, endingLabel, startDate, endDate);
        topGrid.setVgap(10);
        topGrid.setHgap(15);
        //BOTTOM
        siLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SI_TEXT.toString()));
        deleteButton = new Button(props.getProperty(CourseSiteGeneratorProp.DELETE_BUTTON_TEXT.toString()));
        sihBox = new HBox();
        sihBox.getChildren().addAll(siLabel,deleteButton);
        addEditLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ADD_EDIT_TEXT.toString()));
        typeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TYPE_TEXT.toString()));
        typeCB = new ComboBox();
        dateLabel = new Label(props.getProperty(CourseSiteGeneratorProp.DATE_TEXT.toString()));
        dateDP = new DatePicker();
        dateDP.setValue(LocalDate.now());
        timeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TIME_TEXT.toString()));
        timeTF = new TextField();
        titleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TITLE_TEXT.toString()));
        titleTF = new TextField();
        topicLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TOPIC_TEXT.toString()));
        topicTF = new TextField();
        linkLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LINK_TEXT.toString()));
        linkTF = new TextField();
        criteriaLabel = new Label(props.getProperty(CourseSiteGeneratorProp.CRITERIA_TEXT.toString()));
        criteriaTF = new TextField();
        addUpdateButton = new Button(props.getProperty(CourseSiteGeneratorProp.ADD_UPDATE_TEXT.toString()));
        clearButton = new Button(props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString()));
        lowGrid = new GridPane();
        schedulevBox = new VBox();
        scheduleTable = new TableView();
        TableColumn<ScheduleItem, String> typeColumn = new TableColumn(typeLabel.getText());
        TableColumn<ScheduleItem, Date> dateColumn = new TableColumn(dateLabel.getText());
        TableColumn<ScheduleItem, String> titleColumn = new TableColumn(titleLabel.getText());
        TableColumn<ScheduleItem, String> topicColumn = new TableColumn(topicLabel.getText());
        scheduleTable.getColumns().addAll(typeColumn, dateColumn, titleColumn, topicColumn);
        GridPane.setConstraints(addEditLabel, 0, 0);
        GridPane.setConstraints(typeLabel, 0, 1);
        GridPane.setConstraints(typeCB, 1, 1);
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(dateDP, 1, 2);
        GridPane.setConstraints(timeLabel, 0, 3);
        GridPane.setConstraints(timeTF, 1, 3);
        GridPane.setConstraints(titleLabel, 0, 4);
        GridPane.setConstraints(titleTF, 1, 4);
        GridPane.setConstraints(topicLabel, 0, 5);
        GridPane.setConstraints(topicTF, 1, 5);
        GridPane.setConstraints(linkLabel, 0, 6);
        GridPane.setConstraints(linkTF, 1, 6);
        GridPane.setConstraints(criteriaLabel, 0, 7);
        GridPane.setConstraints(criteriaTF, 1, 7);
        GridPane.setConstraints(addUpdateButton, 0, 8);
        GridPane.setConstraints(clearButton, 1, 8);
        lowGrid.getChildren().addAll(
            addEditLabel, typeLabel, typeCB, dateLabel, dateDP, timeLabel, timeTF, titleLabel,
            titleTF, linkLabel, linkTF, criteriaLabel, criteriaTF, addUpdateButton, clearButton);
        lowvBox = new VBox();
        lowvBox.getChildren().addAll(sihBox,scheduleTable,lowGrid);
        schedulevBox.getChildren().addAll(sLabel,topGrid,lowvBox);
    }
    public VBox getGUI(){
        return schedulevBox;
    }
}
