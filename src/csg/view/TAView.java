/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.CourseSiteGeneratorData;
import csg.data.TAData;
import csg.data.TeachingAssistant;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Itai
 */
public class TAView extends View{
    CourseSiteGeneratorApp app;
    // FOR THE HEADER ON THE LEFT
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    // FOR THE TA TABLE
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;

    // THE TA INPUT
    HBox addBox;
    HBox clearBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button clearButton;
    // THE HEADER ON THE RIGHT
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    TeachingAssistant selectedTA;
    
    // THE OFFICE HOURS GRID
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    
    ComboBox startHourButton;
    ComboBox endHourButton;
    Button submitHoursButton;
    HBox hoursBox;
    
    public TAView(CourseSiteGeneratorApp initApp){
        app = initApp;
        CourseSiteGeneratorData csgData = (CourseSiteGeneratorData)app.getDataComponent();
        // WE'LL NEED THIS TO GET LANGUAGE PROPERTIES FOR OUR UI
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        // INIT THE HEADER ON THE LEFT
        tasHeaderBox = new HBox();
        String tasHeaderText = props.getProperty(CourseSiteGeneratorProp.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        tasHeaderBox.getChildren().add(tasHeaderLabel);

        // MAKE THE TABLE AND SETUP THE DATA MODEL
        taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TAData data = (TAData)csgData.getCurrentData();
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        taTable.setItems(tableData);
        String nameColumnText = props.getProperty(CourseSiteGeneratorProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CourseSiteGeneratorProp.EMAIL_COLUMN_TEXT.toString());
        nameColumn = new TableColumn(nameColumnText);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        nameColumn.setPrefWidth(100);
        emailColumn = new TableColumn(emailColumnText);
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        emailColumn.setPrefWidth(325);
        taTable.getColumns().add(nameColumn);
        taTable.getColumns().add(emailColumn);
        // ADD BOX FOR ADDING A TA
        String namePromptText = props.getProperty(CourseSiteGeneratorProp.NAME_PROMPT_TEXT.toString());
        String emailPromptText = props.getProperty(CourseSiteGeneratorProp.EMAIL_PROMPT_TEXT.toString());
        String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_BUTTON_TEXT.toString());
        String clearButtonText = props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString());
        nameTextField = new TextField();
        nameTextField.setPromptText(namePromptText);
        emailTextField = new TextField();
        emailTextField.setPromptText(emailPromptText);
        addButton = new Button(addButtonText);
        addBox = new HBox();
        clearButton = new Button(clearButtonText);
        nameTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.4));
        emailTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.55));
        addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        clearButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
        addBox.getChildren().add(nameTextField);
        addBox.getChildren().add(emailTextField);
        addBox.getChildren().add(addButton);
        addBox.getChildren().add(clearButton);
        
        

        // INIT THE HEADER ON THE RIGHT
        officeHoursHeaderBox = new HBox();
        String officeHoursGridText = props.getProperty(CourseSiteGeneratorProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.getChildren().add(officeHoursHeaderLabel);
        
        //INIT BUTTONS FOR COMBOBOX
        startHourButton = new ComboBox();
        endHourButton = new ComboBox();
        startHourButton.getStyleClass().add(TAStyle.HOURS_BOX);
        endHourButton.getStyleClass().add(TAStyle.HOURS_BOX);
        String submitButtonText = props.getProperty(CourseSiteGeneratorProp.SUBMIT_HOURS_TEXT.toString());
        submitHoursButton = new Button(submitButtonText);
        submitHoursButton.getStyleClass().add(TAStyle.HOURS_SUBMIT_BUTTON);
        officeHoursHeaderBox.getChildren().addAll(startHourButton, endHourButton, submitHoursButton);
        for(int i = 0; i < 24; i++){
            if(i <12){
                if(i == 0){
                    startHourButton.getItems().add("12:00am");
                    endHourButton.getItems().add("12:00am");
                }
                else{
                  startHourButton.getItems().add(i + ":00am");
                  endHourButton.getItems().add(i+":00am");  
                }
            }
            else{
                if(i == 12){
                   startHourButton.getItems().add("12:00pm");
                   endHourButton.getItems().add("12:00pm"); 
                }
                else{
                    startHourButton.getItems().add(i-12 + ":00pm");
                  endHourButton.getItems().add(i-12+":00pm");  
                }
            }
        }      
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();
        
        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox();
        leftPane.getChildren().add(tasHeaderBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        VBox rightPane = new VBox();
        rightPane.getChildren().add(officeHoursHeaderBox);
        rightPane.getChildren().add(officeHoursGridPane);
        
        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        SplitPane sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
        workspace = new BorderPane();
        
        // AND PUT EVERYTHING IN THE WORKSPACE
        ((BorderPane) workspace).setCenter(sPane);

        // MAKE SURE THE TABLE EXTENDS DOWN FAR ENOUGH
        taTable.prefHeightProperty().bind(workspace.heightProperty().multiply(1.9));
        officeHoursGridPane.prefWidthProperty().bind(workspace.widthProperty().divide(2).subtract(5));
        
    }
}
