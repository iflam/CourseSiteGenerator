/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.RecitationData;
import csg.data.SitePage;
import csg.data.TAData;
import csg.transactions.AddRec_Transaction;
import csg.transactions.AddTA_Transaction;
import csg.transactions.DeleteRec_Transaction;
import csg.transactions.UpdateRec_Transaction;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    Recitation selectedRecitation;
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
        RecitationData thisData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        recTitleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.RECITATION_TITLE_TEXT.toString()));
        deleteButton = new Button(props.getProperty(CourseSiteGeneratorProp.DELETE_BUTTON_TEXT.toString()));
        titlehBox = new HBox();
        titlehBox.getChildren().addAll(recTitleLabel,deleteButton);
        recitationTable = new TableView<Recitation>();
        sectionLabel = new Label(props.getProperty(CourseSiteGeneratorProp.SECTION_TEXT.toString()));
        instructorLabel = new Label(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_TEXT.toString()));
        dayTimeLabel = new Label(props.getProperty(CourseSiteGeneratorProp.DAY_TIME_TEXT.toString()));
        locationLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LOCATION_TEXT.toString()));
        taLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TA_TEXT.toString()));
        sectionCol = new TableColumn(sectionLabel.getText());
        sectionCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("section"));
        instructorCol = new TableColumn(instructorLabel.getText());
        instructorCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("instructor"));
        dayTimeCol = new TableColumn(dayTimeLabel.getText());
        dayTimeCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("dayTime"));
        locationCol = new TableColumn(locationLabel.getText());
        locationCol.setCellValueFactory(new PropertyValueFactory<Recitation,String>("location"));
        taCol1 = new TableColumn(taLabel.getText());
        taCol1.setCellValueFactory(new PropertyValueFactory<Recitation,String>("ta1"));
        taCol2 = new TableColumn(taLabel.getText());
        taCol2.setCellValueFactory(new PropertyValueFactory<Recitation,String>("ta2"));
        sectionCol.prefWidthProperty().bind(recitationTable.widthProperty().divide(6)); // w * 1/4
        instructorCol.prefWidthProperty().bind(recitationTable.widthProperty().divide(6)); // w * 1/4
        dayTimeCol.prefWidthProperty().bind(recitationTable.widthProperty().divide(6)); // w * 1/4
        locationCol.prefWidthProperty().bind(recitationTable.widthProperty().divide(6));
        taCol1.prefWidthProperty().bind(recitationTable.widthProperty().divide(6)); // w * 1/4
        taCol2.prefWidthProperty().bind(recitationTable.widthProperty().divide(6));
        recitationTable.getColumns().addAll(sectionCol,instructorCol,dayTimeCol,locationCol,taCol1,taCol2);
        recitationTable.setItems(thisData.getRecitations());
        recvBox = new VBox();
        //BOTTOM
        addEditLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ADD_EDIT_TEXT.toString()));
        sectionTF = new TextField();
        sectionTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.SECTION_SUG.toString()));
        instructorTF = new TextField();
        instructorTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.INSTRUCTOR_SUG.toString()));
        dayTimeTF = new TextField();
        dayTimeTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.DAY_TIME_SUG.toString()));
        locationTF = new TextField();
        locationTF.setPromptText(props.getProperty(CourseSiteGeneratorProp.LOCATION_SUG.toString()));
        supervisingTALabel = new Label(props.getProperty(CourseSiteGeneratorProp.SUPERVISING_TA_TEXT.toString()));
        supervisingTALabel2 = new Label(props.getProperty(CourseSiteGeneratorProp.SUPERVISING_TA_TEXT.toString()));
        TAData taData = ((CourseSiteGeneratorData)app.getDataComponent()).getTAData();
        supervisingTACB1 = new ComboBox(taData.getTeachingAssistants());
        supervisingTACB2 = new ComboBox(taData.getTeachingAssistants());
        addUpdateButton = new Button(props.getProperty(CourseSiteGeneratorProp.ADD_UPDATE_TEXT.toString()));
        clearButton = new Button(props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString()));
        GridPane.setConstraints(addEditLabel, 0, 0);
        GridPane.setConstraints(sectionLabel, 0, 1);
        GridPane.setConstraints(sectionTF, 1, 1);
        GridPane.setConstraints(instructorLabel, 0, 2);
        GridPane.setConstraints(instructorTF, 1, 2, 2, 1);
        GridPane.setConstraints(dayTimeLabel, 0, 3);
        GridPane.setConstraints(dayTimeTF, 1, 3, 3, 1);
        GridPane.setConstraints(locationLabel, 0, 4);
        GridPane.setConstraints(locationTF, 1, 4, 3, 1);
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
        RecitationData recitationData = ((CourseSiteGeneratorData)app.getDataComponent()).getRecitationData();
        selectedRecitation = null;
        recitationTable.setOnMouseClicked(e->{
            if(recitationTable.getSelectionModel().getSelectedItem() != null){
                selectedRecitation = (Recitation)recitationTable.getSelectionModel().getSelectedItem();
                sectionTF.setText(selectedRecitation.getSection());
                instructorTF.setText(selectedRecitation.getInstructor());
                dayTimeTF.setText(selectedRecitation.getDayTime());
                locationTF.setText(selectedRecitation.getLocation());
                supervisingTACB1.getSelectionModel().select(selectedRecitation.getTa1());
                supervisingTACB2.getSelectionModel().select(selectedRecitation.getTa2());
            }
        });
        clearButton.setOnAction(e->{
                selectedRecitation = null;
                sectionTF.setText("");
                instructorTF.setText("");
                dayTimeTF.setText("");
                locationTF.setText("");
                supervisingTACB1.getSelectionModel().selectFirst();
                supervisingTACB2.getSelectionModel().selectFirst();
                recitationTable.getSelectionModel().clearSelection(recitationTable.getSelectionModel().getFocusedIndex());
        });
        deleteButton.setOnAction(e->{
                handleDeleteRec(recitationData,(Recitation)recitationTable.getSelectionModel().getSelectedItem());
        });
        recitationTable.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.DELETE){
                handleDeleteRec(recitationData,(Recitation)recitationTable.getSelectionModel().getSelectedItem());
            }
        });
        addUpdateButton.setOnAction(e->{
                if(selectedRecitation == null){
                    handleAddRec(recitationData);
                    sectionTF.setText("");
                    instructorTF.setText("");
                    dayTimeTF.setText("");
                    locationTF.setText("");
                    supervisingTACB1.getSelectionModel().selectFirst();
                    supervisingTACB2.getSelectionModel().selectFirst();
                }
                else{
                    handleUpdateRec(recitationData);
                }
                    });
        CourseSiteGeneratorController controller = new CourseSiteGeneratorController(app);
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
    
    public void handleDeleteRec(RecitationData rD,Recitation r){
            jTPS_Transaction transaction = new DeleteRec_Transaction(rD,r);
            app.getStack().addTransaction(transaction);
    }
    
    public void handleAddRec(RecitationData rD){
        Recitation r = new Recitation(sectionTF.getText(), 
                            instructorTF.getText(),
                            dayTimeTF.getText(),
                            locationTF.getText(),
                            supervisingTACB1.getSelectionModel().getSelectedItem().toString(),
                            supervisingTACB2.getSelectionModel().getSelectedItem().toString());
            jTPS_Transaction transaction = new AddRec_Transaction(rD,r);
            app.getStack().addTransaction(transaction);
    }
    
    public void handleUpdateRec(RecitationData rD){
        Recitation r2 = new Recitation(sectionTF.getText(), 
                            instructorTF.getText(),
                            dayTimeTF.getText(),
                            locationTF.getText(),
                            supervisingTACB1.getSelectionModel().getSelectedItem().toString(),
                            supervisingTACB2.getSelectionModel().getSelectedItem().toString());
        for(Recitation r: rD.getRecitations()){
                        if(r == recitationTable.getSelectionModel().getSelectedItem()){
                            jTPS_Transaction transaction = new UpdateRec_Transaction(rD,r,r2);
                            app.getStack().addTransaction(transaction);
                            selectedRecitation = r2;
                            recitationTable.getSelectionModel().select(r2);
                            break;
                        }
                    }
    }

    public Label getRecTitleLabel() {
        return recTitleLabel;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAddUpdateButton() {
        return addUpdateButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public TableView getRecitationTable() {
        return recitationTable;
    }

    public GridPane getLowGridPane() {
        return lowGridPane;
    }

    public Label getAddEditLabel() {
        return addEditLabel;
    }

    public Label getSectionLabel() {
        return sectionLabel;
    }

    public Label getInstructorLabel() {
        return instructorLabel;
    }

    public Label getDayTimeLabel() {
        return dayTimeLabel;
    }

    public Label getLocationLabel() {
        return locationLabel;
    }

    public Label getTaLabel() {
        return taLabel;
    }

    public Label getSupervisingTALabel() {
        return supervisingTALabel;
    }

    public Label getSupervisingTALabel2() {
        return supervisingTALabel2;
    }

    public TextField getSectionTF() {
        return sectionTF;
    }

    public TextField getInstructorTF() {
        return instructorTF;
    }

    public TextField getDayTimeTF() {
        return dayTimeTF;
    }

    public TextField getLocationTF() {
        return locationTF;
    }

    public ComboBox getSupervisingTACB1() {
        return supervisingTACB1;
    }

    public ComboBox getSupervisingTACB2() {
        return supervisingTACB2;
    }

    public VBox getRecvBox() {
        return recvBox;
    }

    public HBox getTitlehBox() {
        return titlehBox;
    }

    public CourseSiteGeneratorApp getApp() {
        return app;
    }

    public TableColumn<Recitation, String> getSectionCol() {
        return sectionCol;
    }

    public TableColumn<Recitation, String> getInstructorCol() {
        return instructorCol;
    }

    public TableColumn<Recitation, String> getDayTimeCol() {
        return dayTimeCol;
    }

    public TableColumn<Recitation, String> getLocationCol() {
        return locationCol;
    }

    public TableColumn<Recitation, String> getTaCol1() {
        return taCol1;
    }

    public TableColumn<Recitation, String> getTaCol2() {
        return taCol2;
    }

    public ObservableList<Recitation> getRecitations() {
        return recitations;
    }
    
    public VBox getGUI(){
        return recvBox;
    }

}
