/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.CourseSiteGeneratorApp;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.ProjectView;

/**
 *
 * @author Itai
 */
public class ProjectStyle {
    public static String MAIN_PANE = "outer_pane";
    public static String INNER_PANE = "inner_pane";
    public static String NOT_BOLD = "not_bold";
    public static String GRID = "grid";
    public static String TEXT = "text_prompt";
    public static String HEADER_TEXT = "text_header";
    public static String HEADER_BUTTON = "button_header";
    CourseSiteGeneratorApp app;
    ProjectView page;
    public ProjectStyle(CourseSiteGeneratorApp initApp){
        app = initApp;
        page = (ProjectView)((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getProjectView();
        
    }
    
    public void initProjectStyle(){
        page.getProjectvBox().getStyleClass().add(MAIN_PANE);
        page.getTopGP().getStyleClass().add(GRID);
        page.getLowGP().getStyleClass().add(GRID);
        page.getTopGP().getStyleClass().add(TEXT);
        page.getLowGP().getStyleClass().add(TEXT);
        page.getTophBox().getStyleClass().addAll(HEADER_TEXT,HEADER_BUTTON,GRID);
    }
    
}
