/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.CourseSiteGeneratorData;
import csg.data.ScheduleData;
import csg.data.ScheduleItem;
import csg.transactions.AddSchedItem_Transaction;
import csg.transactions.RemoveSchedItem_Transaction;
import csg.transactions.ToggleTA_Transaction;
import csg.transactions.UpdateSchedItem_Transaction;
import djf.components.AppDataComponent;
import djf.ui.AppMessageDialogSingleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class ScheduleView {
    CourseSiteGeneratorApp app;
    CourseSiteGeneratorController controller;
    
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
    public static final LocalDate DEFAULT_START = LocalDate.now();
    public static final LocalDate DEFAULT_END = LocalDate.now();
    boolean isAdd;
    
    public ScheduleView(CourseSiteGeneratorApp initApp){
        app = initApp;
        isAdd = true;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ScheduleData thisData = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        //TOP
        sLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SCHEDULE_TEXT.toString()));
        cbLabel = new Label(props.getProperty(CourseSiteGeneratorProp.CB_TEXT.toString()));
        startingLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STARTING_TEXT.toString()));
        endingLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ENDING_TEXT.toString()));
        startDate = new DatePicker();
        endDate = new DatePicker();
        setStartDate(thisData.getStartDate());
        setEndDate(thisData.getEndDate());
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
        ObservableList typeItems = FXCollections.observableArrayList();
        typeItems.addAll(props.getProperty(CourseSiteGeneratorProp.CB_HOLIDAY.toString()),
                props.getProperty(CourseSiteGeneratorProp.CB_LECTURE.toString()),
                props.getProperty(CourseSiteGeneratorProp.CB_REF.toString()),
                props.getProperty(CourseSiteGeneratorProp.CB_REC.toString()),
                props.getProperty(CourseSiteGeneratorProp.CB_HWS.toString())
                );
        typeCB.setItems(typeItems);
        typeCB.getSelectionModel().selectFirst();
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
        typeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem,String>("type"));
        TableColumn<ScheduleItem, String> dateColumn = new TableColumn(dateLabel.getText());
        dateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem,String>("date"));
        TableColumn<ScheduleItem, String> titleColumn = new TableColumn(titleLabel.getText());
        titleColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem,String>("title"));
        TableColumn<ScheduleItem, String> topicColumn = new TableColumn(topicLabel.getText());
        topicColumn.setCellValueFactory(new PropertyValueFactory<ScheduleItem,String>("topic"));
        typeColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(4)); // w * 1/4
        dateColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(4)); // w * 1/4
        titleColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(4)); // w * 1/4
        topicColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(4)); // w * 1/4
        scheduleTable.getColumns().addAll(typeColumn, dateColumn, titleColumn, topicColumn);
        scheduleTable.setItems(thisData.getItems());
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
            titleTF,topicLabel,topicTF, linkLabel, linkTF, criteriaLabel, criteriaTF, addUpdateButton, clearButton);
        lowvBox = new VBox();
        lowvBox.getChildren().addAll(sihBox,scheduleTable,lowGrid);
        schedulevBox.getChildren().addAll(sLabel,topGrid,lowvBox);
        // NOW LET'S SETUP THE EVENT HANDLING
        controller = new CourseSiteGeneratorController(app);
        LocalDate tempStartDate = startDate.getValue();
        LocalDate tempEndDate = endDate.getValue();
        startDate.setOnAction(e->{
            if(startDate.getValue().compareTo(endDate.getValue()) > 0){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty("WRONG_HOURS_FORMAT_TITLE"), props.getProperty("WRONG_HOURS_FORMAT_MESSAGE"));
                startDate.setValue(tempStartDate);
            }
            else{
                controller.handleChangeStartDate(startDate.getValue());
            }
        });
        endDate.setOnAction(e->{
            if(endDate.getValue().compareTo(startDate.getValue()) < 0){
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty("WRONG_HOURS_FORMAT_TITLE"), props.getProperty("WRONG_HOURS_FORMAT_MESSAGE"));
                endDate.setValue(tempEndDate);
            }
            else{
                controller.handleChangeEndDate(endDate.getValue());
            }
        });
        clearButton.setOnAction(e->{
            typeCB.getSelectionModel().selectFirst();
            dateDP.setValue(DEFAULT_START);
            titleTF.setText("");
            timeTF.setText("");
            topicTF.setText("");
            linkTF.setText("");
            criteriaTF.setText("");
            isAdd = true;
            
        });
        scheduleTable.setOnMouseClicked(e->{
            if(scheduleTable.getSelectionModel().getSelectedItem() != null){
                ScheduleItem item = ((ScheduleItem)scheduleTable.getSelectionModel().getSelectedItem());
                typeCB.setValue(item.getType());
                dateDP.setValue(makeDate(item.getDate()));
                titleTF.setText(item.getTitle());
                timeTF.setText(item.getTime());
                topicTF.setText(item.getTopic());
                linkTF.setText(item.getLink());
                criteriaTF.setText(item.getCriteria());
                isAdd = false;
            }
        });
        controller = new CourseSiteGeneratorController(app);
        addUpdateButton.setOnAction(e->{
            if(isAdd){
                handleAddScheduleItem(thisData, new ScheduleItem(
                    typeCB.getValue().toString(),
                    dateDP.getValue().getMonthValue()+"_"
                    +dateDP.getValue().getDayOfMonth()+"_"+dateDP.getValue().getYear(),
                    titleTF.getText(),
                    timeTF.getText(),
                    topicTF.getText(),
                    linkTF.getText(),
                    criteriaTF.getText())
                );
                typeCB.getSelectionModel().selectFirst();
                dateDP.setValue(DEFAULT_START);
                titleTF.setText("");
                timeTF.setText("");
                topicTF.setText("");
                linkTF.setText("");
                criteriaTF.setText("");
            }
            else{
                handleUpdateScheduleItem(thisData,new ScheduleItem(
                    typeCB.getValue().toString(),
                    dateDP.getValue().getMonthValue()+"_"
                    +dateDP.getValue().getDayOfMonth()+"_"+dateDP.getValue().getYear(),
                    titleTF.getText(),
                    timeTF.getText(),
                    topicTF.getText(),
                    linkTF.getText(),
                    criteriaTF.getText()));
            }
        });
        deleteButton.setOnAction(e->{
            handleDeleteScheduleItem(thisData,(ScheduleItem)scheduleTable.getSelectionModel().getSelectedItem());
        });
        scheduleTable.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.DELETE){
                handleDeleteScheduleItem(thisData,(ScheduleItem)scheduleTable.getSelectionModel().getSelectedItem());
            }
        });
        
        
        KeyCombination ctrlZ = KeyCodeCombination.keyCombination("Ctrl+z");
        KeyCombination ctrlY = KeyCodeCombination.keyCombination("Ctrl+y");
        app.getGUI().getPrimaryScene().setOnKeyPressed(e ->{
            if(ctrlZ.match(e)){
                controller.handleCtrlz(); //Handle control z, now go back to TAController and finish that by 
                //undoing transactions in there, reference jTPS files for help.
            }
            if(ctrlY.match(e)){
                controller.handleCtrly();
            }
        });
    }
    public void handleDeleteScheduleItem(ScheduleData scheduleData, ScheduleItem s){
        jTPS_Transaction transaction = new RemoveSchedItem_Transaction(scheduleData, s);
            app.getStack().addTransaction(transaction);
    }
    public void handleUpdateScheduleItem(ScheduleData scheduleData, ScheduleItem s){
        for(ScheduleItem s2 : scheduleData.getItems()){
            if(s2.equals(scheduleTable.getSelectionModel().getSelectedItem())){
                jTPS_Transaction transaction = new UpdateSchedItem_Transaction(scheduleData,s2, s);
                    app.getStack().addTransaction(transaction);
            }
        }
    }
    
    public void handleAddScheduleItem(ScheduleData scheduleData, ScheduleItem s){
        jTPS_Transaction transaction = new AddSchedItem_Transaction(scheduleData, s);
            app.getStack().addTransaction(transaction);
    }
    public String getDefaultStart(){
        return DEFAULT_START.getMonthValue()+"_"+DEFAULT_START.getDayOfMonth()+"_"+DEFAULT_START.getYear();
    }
    
    public String getDefaultEnd(){
        return DEFAULT_END.getMonthValue()+"_"+DEFAULT_END.getDayOfMonth()+"_"+DEFAULT_END.getYear();
    }
    public static LocalDate makeDate(String dateString){
        DateTimeFormatter formatter;
        if(dateString.charAt(1) == '_'){
            if(dateString.charAt(3) == '_')
                formatter = DateTimeFormatter.ofPattern("M_d_yyyy");
            else{
                formatter = DateTimeFormatter.ofPattern("M_dd_yyyy");
            }
        }
        else{
            if(dateString.charAt(4) == '_')
            formatter = DateTimeFormatter.ofPattern("MM_d_yyyy");
            else{
                formatter = DateTimeFormatter.ofPattern("MM_dd_yyyy");
            }
        }
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    public CourseSiteGeneratorApp getApp() {
        return app;
    }

    public VBox getSchedulevBox() {
        return schedulevBox;
    }

    public GridPane getTopGrid() {
        return topGrid;
    }

    public VBox getLowvBox() {
        return lowvBox;
    }

    public GridPane getLowGrid() {
        return lowGrid;
    }

    public Label getsLabel() {
        return sLabel;
    }

    public Label getCbLabel() {
        return cbLabel;
    }

    public Label getStartingLabel() {
        return startingLabel;
    }

    public Label getEndingLabel() {
        return endingLabel;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public Label getSiLabel() {
        return siLabel;
    }

    public HBox getSihBox() {
        return sihBox;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public TableView getScheduleTable() {
        return scheduleTable;
    }

    public Label getAddEditLabel() {
        return addEditLabel;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public ComboBox getTypeCB() {
        return typeCB;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public DatePicker getDateDP() {
        return dateDP;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public TextField getTimeTF() {
        return timeTF;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public TextField getTitleTF() {
        return titleTF;
    }

    public Label getTopicLabel() {
        return topicLabel;
    }

    public TextField getTopicTF() {
        return topicTF;
    }

    public Label getLinkLabel() {
        return linkLabel;
    }

    public TextField getLinkTF() {
        return linkTF;
    }

    public Label getCriteriaLabel() {
        return criteriaLabel;
    }

    public TextField getCriteriaTF() {
        return criteriaTF;
    }

    public Button getAddUpdateButton() {
        return addUpdateButton;
    }

    public Button getClearButton() {
        return clearButton;
    }
    
    public VBox getGUI(){
        return schedulevBox;
    }
    
    public void resetDates(){
        ScheduleData thisData = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        startDate.setValue(DEFAULT_START);
        endDate.setValue(DEFAULT_END);
    }
    
    public void setStartDate(String d){
        startDate.setValue(makeDate(d));
        ScheduleData thisData = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        thisData.setStartDate(d);
        
    }
    
    public void setEndDate(String d){
        endDate.setValue(makeDate(d));
        ScheduleData thisData = ((CourseSiteGeneratorData)app.getDataComponent()).getScheduleData();
        thisData.setEndDate(d);
    }
}
