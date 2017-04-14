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
import csg.style.CourseSiteGeneratorStyle;
import csg.style.TAStyle;
import djf.components.AppDataComponent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
public class TAView {
    CourseSiteGeneratorApp app;
    CourseSiteGeneratorController controller;
    // FOR THE HEADER ON THE LEFT
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    // FOR THE TA TABLE
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;
    TableColumn<TeachingAssistant, Boolean> gradColumn;

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
    SplitPane sPane;
    
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
        TAData data = csgData.getTAData();
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        taTable.setItems(tableData);
        String nameColumnText = props.getProperty(CourseSiteGeneratorProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CourseSiteGeneratorProp.EMAIL_COLUMN_TEXT.toString());
        String gradColumnText = props.getProperty(CourseSiteGeneratorProp.GRAD_COLUMN_TEXT.toString());
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
        gradColumn = new TableColumn(gradColumnText);
        gradColumn.setCellValueFactory(
                new PropertyValueFactory<>("undergrad")
        );
        gradColumn.setCellFactory(CheckBoxTableCell.forTableColumn(gradColumn));
        taTable.setEditable(true);
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().divide(2)); //1/2
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().divide(3));
        gradColumn.prefWidthProperty().bind(taTable.widthProperty().divide(6)); //1/6 
        taTable.getColumns().add(gradColumn);
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
        String submitButtonText = props.getProperty(CourseSiteGeneratorProp.SUBMIT_HOURS_TEXT.toString());
        submitHoursButton = new Button(submitButtonText);
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
        sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
//        // MAKE SURE THE TABLE EXTENDS DOWN FAR ENOUGH
        taTable.prefHeightProperty().bind(app.getGUI().getAppPane().heightProperty().multiply(1.9));
        officeHoursGridPane.prefWidthProperty().bind(app.getGUI().getAppPane().widthProperty().divide(2).subtract(5));
        
        // NOW LET'S SETUP THE EVENT HANDLING
        controller = new CourseSiteGeneratorController(app);

        // CONTROLS FOR ADDING TAs
        nameTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        emailTextField.setOnAction(e -> {
            controller.handleAddTA();
        });
        addButton.setOnAction(e -> {
            switch(addButton.getText()){
                case "Add TA":
                    controller.handleAddTA();
                    app.getGUI().updateToolbarControls(false);
                    break;
                case "Update TA":
                    String name = nameTextField.getText();
                    String email = emailTextField.getText();
                    controller.handleUpdateTA(selectedTA, name, email);
                    app.getGUI().updateToolbarControls(false);
                    ObservableList<TeachingAssistant> teachingAssistants = data.getTeachingAssistants();
                    for(TeachingAssistant t : teachingAssistants){
                        if(t.getName().equals(name)){
                            taTable.getSelectionModel().select(t);
                            selectedTA = t;
                            break;
                        }
                    }
                    break;
            }
        });
        //Delete TA
        taTable.setOnKeyPressed(e ->{
            if(e.getCode().getName().equals("Delete")){
                app.getGUI().updateToolbarControls(false);
                final TeachingAssistant taObj = taTable.getSelectionModel().getSelectedItem();
                controller.handleDeleteTA(taObj);
            }
            else if(e.getCode().getName().equals("z")){
                
            }
        });
        //TOGGLE
        taTable.setOnMouseClicked(e ->{
            if(!taTable.getItems().isEmpty()){
                addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.25));
                String updateButtonText = props.getProperty(CourseSiteGeneratorProp.UPDATE_BUTTON_TEXT.toString());
                addButton.setText(updateButtonText);
                final TeachingAssistant taObj = taTable.getSelectionModel().getSelectedItem();
                nameTextField.setText(taObj.getName());
                emailTextField.setText(taObj.getEmail());
                selectedTA = taObj;
            }
        });
        //CLEARING THE FIELDS
        clearButton.setOnAction(e ->{
            nameTextField.setText("");
            emailTextField.setText("");
            addButton.setText(addButtonText);
            addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.2));
            nameTextField.requestFocus();
            taTable.getSelectionModel().clearSelection();   
            
        });
        //SUBMIT BUTTON FOR CHANGING HOURS
        submitHoursButton.setOnAction(e ->{
            String start = (String)startHourButton.getSelectionModel().getSelectedItem();
            String end = (String)endHourButton.getSelectionModel().getSelectedItem();
            controller.handleHoursChange(start, end);
            app.getGUI().updateToolbarControls(false);
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
        nameTextField.setOnKeyPressed(e ->{
            if(ctrlZ.match(e)){
                controller.handleCtrlz();
            }
            if(ctrlY.match(e)){
                controller.handleCtrly();
            }
        });
        emailTextField.setOnKeyPressed(e ->{
            if(ctrlZ.match(e)){
                controller.handleCtrlz(); //Handle control z, now go back to TAController and finish that by 
                //undoing transactions in there, reference jTPS files for help.
            }
            if(ctrlY.match(e)){
                controller.handleCtrly();
            }
        });
        
    }
    
    
    // WE'LL PROVIDE AN ACCESSOR METHOD FOR EACH VISIBLE COMPONENT
    // IN CASE A CONTROLLER OR STYLE CLASS NEEDS TO CHANGE IT
    
    
    public HBox getTAsHeaderBox() {
        return tasHeaderBox;
    }

    public Label getTAsHeaderLabel() {
        return tasHeaderLabel;
    }

    public TableView getTATable() {
        return taTable;
    }

    public HBox getAddBox() {
        return addBox;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }
    
    public TextField getEmailTextField() {
        return emailTextField;
    }

    public Button getAddButton() {
        return addButton;
    }
    
    public Button getClearButton(){
        return clearButton;
    }

    public HBox getOfficeHoursSubheaderBox() {
        return officeHoursHeaderBox;
    }

    public Label getOfficeHoursSubheaderLabel() {
        return officeHoursHeaderLabel;
    }

    public GridPane getOfficeHoursGridPane() {
        return officeHoursGridPane;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {
        return officeHoursGridTimeHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {
        return officeHoursGridTimeHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {
        return officeHoursGridDayHeaderPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {
        return officeHoursGridDayHeaderLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {
        return officeHoursGridTimeCellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {
        return officeHoursGridTimeCellLabels;
    }

    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {
        return officeHoursGridTACellPanes;
    }

    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {
        return officeHoursGridTACellLabels;
    }
    
    public String getCellKey(Pane testPane) {
        for (String key : officeHoursGridTACellLabels.keySet()) {
            if (officeHoursGridTACellPanes.get(key) == testPane) {
                return key;
            }
        }
        return null;
    }

    public Label getTACellLabel(String cellKey) {
        return officeHoursGridTACellLabels.get(cellKey);
    }
    
    public SplitPane getGUI(){
        return sPane;
    }

    public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }

    public String buildCellKey(int col, int row) {
        return "" + col + "_" + row;
    }

    public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }

    public void resetWorkspace() {
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
    }
    
    public void resetWorkspaceNoStack() {
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
    }
    
    public void clearStack(){
        app.getStack().clearStack();
    }
    
    public void reloadWorkspace(CourseSiteGeneratorData dataComponent) {
        TAData taData = dataComponent.getTAData();
        reloadOfficeHoursGrid(taData);
        endHourButton.getSelectionModel().select((taData.getEndHour() == 24)?0:taData.getEndHour());
        startHourButton.getSelectionModel().select((taData.getStartHour() == 24)?0:taData.getStartHour());
        nameTextField.setText("");
        emailTextField.setText("");
    }
    
    public void clearOfficeHoursGrid(TAData dataComponent){
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
            row += 2;
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        TAStyle taStyle = ((CourseSiteGeneratorStyle)app.getStyleComponent()).getTAStyle();
        taStyle.initOfficeHoursGridStyle(); 
        
    }

    public void reloadOfficeHoursGrid(TAData dataComponent) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();
         
        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
            row += 2;
        }

        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            String pKey = getCellKey(p);
            String[] pItems = pKey.split("_");
            p.setOnMouseClicked(e -> {
                app.getGUI().updateToolbarControls(false);
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
               //set all of row
               p.getStyleClass().add(TAStyle.HIGHLIGHTED_GRID_CELL);
               for(Pane p2 : officeHoursGridTACellPanes.values()){
                   if(p2 == p){
                       continue;
                   }
                   String cellKey = getCellKey(p2);
                   String[] cellItems = cellKey.split("_");
                   //rows
                   if(pItems[0].equals(cellItems[0])){
                       if(Integer.parseInt(cellItems[1])<=Integer.parseInt(pItems[1]))
                            p2.getStyleClass().add(TAStyle.HIGHLIGHTED_GRID_ROW_OR_COLUMN);
                   }
                   if(pItems[1].equals(cellItems[1])){
                       if(Integer.parseInt(cellItems[0])<=Integer.parseInt(pItems[0]))
                            p2.getStyleClass().add(TAStyle.HIGHLIGHTED_GRID_ROW_OR_COLUMN);
                   }
               }
            });
            p.setOnMouseExited(e->{
               for(Pane p2: officeHoursGridTACellPanes.values()){
                   if(p2.getStyleClass().contains(TAStyle.HIGHLIGHTED_GRID_ROW_OR_COLUMN)){
                        p2.getStyleClass().remove(TAStyle.HIGHLIGHTED_GRID_ROW_OR_COLUMN);
                   }
               }
               p.getStyleClass().remove(TAStyle.HIGHLIGHTED_GRID_CELL);
            });
        }    
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        TAStyle taStyle = ((CourseSiteGeneratorStyle)app.getStyleComponent()).getTAStyle();
        taStyle.initOfficeHoursGridStyle(); 
    }
    
    public void addCellToGrid(TAData dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
   }

}

