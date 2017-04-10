/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.view;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.data.Student;
import csg.data.Team;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

public class ProjectView{
    CourseSiteGeneratorApp app;
    private VBox projectvBox;
    private ScrollPane thisGUI;
    //TOP
    VBox topvBox;
    Label projectsLabel;
    Label teamsLabel;
    GridPane topGP;
    Button deleteButton;
    HBox tophBox;
    TableView topTable;
    Label addEditLabel;
    Label nameLabel;
    TextField nameTF;
    Label colorLabel;
    ColorPicker colorPicker;
    int colorInt;
    Label textColorLabel;
    ColorPicker textColorPicker;
    String textColor;
    Label linkLabel;
    TextField linkTF;
    Button addUpdateButton;
    Button clearButton;
    //BOTTOM
    VBox lowvBox;
    HBox lowhBox;
    GridPane lowGP;
    Label sLabel;
    Button deleteButton2;
    TableView lowTable;
    Label firstNameLabel;
    TextField firstNameTF;
    Label lastNameLabel;
    TextField lastNameTF;
    Label teamLabel;
    ComboBox teamCB;
    Label roleLabel;
    TextField roleTF;
    Button addUpdateButton2;
    Button clearButton2;
    
    public ProjectView(CourseSiteGeneratorApp initApp){
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //TOP
        projectsLabel = new Label(props.getProperty(CourseSiteGeneratorProp.PROJECTS_TEXT.toString()));
        teamsLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TEAMS_TEXT.toString()));
        deleteButton = new Button(props.getProperty(CourseSiteGeneratorProp.DELETE_BUTTON_TEXT.toString()));
        tophBox = new HBox();
        tophBox.getChildren().addAll(teamsLabel,deleteButton);
        addEditLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ADD_EDIT_TEXT.toString()));
        nameLabel = new Label(props.getProperty(CourseSiteGeneratorProp.NAME_PROMPT_TEXT.toString()));
        nameTF = new TextField();
        colorLabel = new Label(props.getProperty(CourseSiteGeneratorProp.COLOR_TEXT.toString()));
        colorPicker = new ColorPicker();
        textColorLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TEXT_COLOR_TEXT.toString()));
        textColorPicker = new ColorPicker();
        linkLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LINK_TEXT.toString()));
        linkTF = new TextField();
        addUpdateButton = new Button(props.getProperty(CourseSiteGeneratorProp.ADD_UPDATE_TEXT.toString()));
        clearButton = new Button(props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString()));
        topTable = new TableView();
        TableColumn<Team, String> nameCol = new TableColumn<>(nameLabel.getText());
        TableColumn<Team, String> colorCol = new TableColumn<>(colorLabel.getText());
        TableColumn<Team, String> textColorCol = new TableColumn<>(textColorLabel.getText());
        TableColumn<Team, String> linkCol = new TableColumn<>(linkLabel.getText());
        nameCol.prefWidthProperty().bind(topTable.widthProperty().divide(4)); // w * 1/4
        colorCol.prefWidthProperty().bind(topTable.widthProperty().divide(4)); // w * 1/4
        textColorCol.prefWidthProperty().bind(topTable.widthProperty().divide(4)); // w * 1/4
        linkCol.prefWidthProperty().bind(topTable.widthProperty().divide(4)); // w * 1/4
        topTable.getColumns().addAll(nameCol,colorCol,textColorCol,linkCol);
        topvBox = new VBox();
        topGP = new GridPane();
        GridPane.setConstraints(addEditLabel,0,0);
        GridPane.setConstraints(nameLabel,0,1);
        GridPane.setConstraints(nameTF,1,1);
        GridPane.setConstraints(colorLabel,0,2);
        GridPane.setConstraints(colorPicker,1,2);
        GridPane.setConstraints(textColorLabel,2,2);
        GridPane.setConstraints(textColorPicker,3,2);
        GridPane.setConstraints(linkLabel,0,3);
        GridPane.setConstraints(linkTF,1,3);
        GridPane.setConstraints(addUpdateButton,0,4);
        GridPane.setConstraints(clearButton,1,4);
        topGP.getChildren().addAll(addEditLabel,nameLabel,nameTF,colorLabel,colorPicker,textColorLabel,
                textColorPicker,linkLabel,linkTF,addUpdateButton,clearButton);
        topvBox.getChildren().addAll(tophBox,topTable,topGP);
        //BOTTOM
        sLabel = new Label(props.getProperty(CourseSiteGeneratorProp.STUDENTS_TEXT.toString()));
        deleteButton2 = new Button(props.getProperty(CourseSiteGeneratorProp.DELETE_BUTTON_TEXT.toString()));
        lowhBox = new HBox();
        lowhBox.getChildren().addAll(sLabel, deleteButton2);
        firstNameLabel = new Label(props.getProperty(CourseSiteGeneratorProp.FIRST_NAME_TEXT.toString()));
        firstNameTF = new TextField();
        lastNameLabel = new Label(props.getProperty(CourseSiteGeneratorProp.LAST_NAME_TEXT.toString()));
        lastNameTF = new TextField();
        teamLabel = new Label(props.getProperty(CourseSiteGeneratorProp.TEAM_TEXT.toString()));
        teamCB = new ComboBox();
        roleLabel = new Label(props.getProperty(CourseSiteGeneratorProp.ROLE_TEXT.toString()));
        roleTF = new TextField();
        addUpdateButton2 = new Button(props.getProperty(CourseSiteGeneratorProp.ADD_UPDATE_TEXT.toString()));
        clearButton2 = new Button(props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString()));
        lowTable = new TableView();
        TableColumn<Student, String> fnCol = new TableColumn(firstNameLabel.getText());
        TableColumn<Student, String> lnCol = new TableColumn(lastNameLabel.getText());
        TableColumn<Student, String> teamCol = new TableColumn(teamLabel.getText());
        TableColumn<Student, String> roleCol = new TableColumn(roleLabel.getText());
        fnCol.prefWidthProperty().bind(lowTable.widthProperty().divide(4)); // w * 1/4
        lnCol.prefWidthProperty().bind(lowTable.widthProperty().divide(4)); // w * 1/4
        teamCol.prefWidthProperty().bind(lowTable.widthProperty().divide(4)); // w * 1/4
        roleCol.prefWidthProperty().bind(lowTable.widthProperty().divide(4)); // w * 1/4
        lowTable.getColumns().addAll(fnCol,lnCol,teamCol,roleCol);
        lowGP = new GridPane();
        GridPane.setConstraints(addEditLabel, 0, 0);
        GridPane.setConstraints(firstNameLabel, 0, 1);
        GridPane.setConstraints(firstNameTF, 1, 1);
        GridPane.setConstraints(lastNameLabel, 0, 2);
        GridPane.setConstraints(lastNameTF, 1, 2);
        GridPane.setConstraints(teamLabel, 0, 3);
        GridPane.setConstraints(teamCB, 1, 3);
        GridPane.setConstraints(roleLabel, 0, 4);
        GridPane.setConstraints(roleTF, 1, 4);
        GridPane.setConstraints(addUpdateButton2, 0, 5);
        GridPane.setConstraints(clearButton2, 1, 5);
        lowGP.getChildren().addAll(addEditLabel,firstNameLabel,firstNameTF,lastNameLabel,lastNameTF,teamLabel,teamCB,
                roleLabel,roleTF,addUpdateButton2,clearButton2);
        lowvBox = new VBox();
        lowvBox.getChildren().addAll(lowhBox,lowTable,lowGP);
        projectvBox = new VBox();
        projectvBox.getChildren().addAll(topvBox,lowTable,lowGP);
        thisGUI = new ScrollPane();
        thisGUI.setFitToWidth(true);
        thisGUI.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        thisGUI.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        thisGUI.setContent(projectvBox);
    }

    public CourseSiteGeneratorApp getApp() {
        return app;
    }

    public void setApp(CourseSiteGeneratorApp app) {
        this.app = app;
    }

    public VBox getProjectvBox() {
        return projectvBox;
    }

    public void setProjectvBox(VBox projectvBox) {
        this.projectvBox = projectvBox;
    }

    public VBox getTopvBox() {
        return topvBox;
    }

    public void setTopvBox(VBox topvBox) {
        this.topvBox = topvBox;
    }

    public Label getProjectsLabel() {
        return projectsLabel;
    }

    public void setProjectsLabel(Label projectsLabel) {
        this.projectsLabel = projectsLabel;
    }

    public Label getTeamsLabel() {
        return teamsLabel;
    }

    public void setTeamsLabel(Label teamsLabel) {
        this.teamsLabel = teamsLabel;
    }

    public GridPane getTopGP() {
        return topGP;
    }

    public void setTopGP(GridPane topGP) {
        this.topGP = topGP;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public HBox getTophBox() {
        return tophBox;
    }

    public void setTophBox(HBox tophBox) {
        this.tophBox = tophBox;
    }

    public TableView getTopTable() {
        return topTable;
    }

    public void setTopTable(TableView topTable) {
        this.topTable = topTable;
    }

    public Label getAddEditLabel() {
        return addEditLabel;
    }

    public void setAddEditLabel(Label addEditLabel) {
        this.addEditLabel = addEditLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public TextField getNameTF() {
        return nameTF;
    }

    public void setNameTF(TextField nameTF) {
        this.nameTF = nameTF;
    }

    public Label getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(Label colorLabel) {
        this.colorLabel = colorLabel;
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public int getColorInt() {
        return colorInt;
    }

    public void setColorInt(int colorInt) {
        this.colorInt = colorInt;
    }

    public Label getTextColorLabel() {
        return textColorLabel;
    }

    public void setTextColorLabel(Label textColorLabel) {
        this.textColorLabel = textColorLabel;
    }

    public ColorPicker getTextColorPicker() {
        return textColorPicker;
    }

    public void setTextColorPicker(ColorPicker textColorPicker) {
        this.textColorPicker = textColorPicker;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Label getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(Label linkLabel) {
        this.linkLabel = linkLabel;
    }

    public TextField getLinkTF() {
        return linkTF;
    }

    public void setLinkTF(TextField linkTF) {
        this.linkTF = linkTF;
    }

    public Button getAddUpdateButton() {
        return addUpdateButton;
    }

    public void setAddUpdateButton(Button addUpdateButton) {
        this.addUpdateButton = addUpdateButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public void setClearButton(Button clearButton) {
        this.clearButton = clearButton;
    }

    public VBox getLowvBox() {
        return lowvBox;
    }

    public void setLowvBox(VBox lowvBox) {
        this.lowvBox = lowvBox;
    }

    public HBox getLowhBox() {
        return lowhBox;
    }

    public void setLowhBox(HBox lowhBox) {
        this.lowhBox = lowhBox;
    }

    public GridPane getLowGP() {
        return lowGP;
    }

    public void setLowGP(GridPane lowGP) {
        this.lowGP = lowGP;
    }

    public Label getsLabel() {
        return sLabel;
    }

    public void setsLabel(Label sLabel) {
        this.sLabel = sLabel;
    }

    public Button getDeleteButton2() {
        return deleteButton2;
    }

    public void setDeleteButton2(Button deleteButton2) {
        this.deleteButton2 = deleteButton2;
    }

    public TableView getLowTable() {
        return lowTable;
    }

    public void setLowTable(TableView lowTable) {
        this.lowTable = lowTable;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    public TextField getFirstNameTF() {
        return firstNameTF;
    }

    public void setFirstNameTF(TextField firstNameTF) {
        this.firstNameTF = firstNameTF;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public void setLastNameLabel(Label lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }

    public TextField getLastNameTF() {
        return lastNameTF;
    }

    public void setLastNameTF(TextField lastNameTF) {
        this.lastNameTF = lastNameTF;
    }

    public Label getTeamLabel() {
        return teamLabel;
    }

    public void setTeamLabel(Label teamLabel) {
        this.teamLabel = teamLabel;
    }

    public ComboBox getTeamCB() {
        return teamCB;
    }

    public void setTeamCB(ComboBox teamCB) {
        this.teamCB = teamCB;
    }

    public Label getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(Label roleLabel) {
        this.roleLabel = roleLabel;
    }

    public TextField getRoleTF() {
        return roleTF;
    }

    public void setRoleTF(TextField roleTF) {
        this.roleTF = roleTF;
    }

    public Button getAddUpdateButton2() {
        return addUpdateButton2;
    }

    public void setAddUpdateButton2(Button addUpdateButton2) {
        this.addUpdateButton2 = addUpdateButton2;
    }

    public Button getClearButton2() {
        return clearButton2;
    }

    public void setClearButton2(Button clearButton2) {
        this.clearButton2 = clearButton2;
    }
    
    
    public ScrollPane getGUI(){
        return thisGUI;
    }
}
