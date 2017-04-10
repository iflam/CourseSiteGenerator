/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.CourseSiteGeneratorApp;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.CourseView;
import javafx.geometry.Pos;

/**
 *
 * @author Itai
 */
public class CourseStyle {
    public static String MAIN_PANE = "outer_pane";
    public static String INNER_PANE = "inner_pane";
    public static String NOT_BOLD = "not_bold";
    public static String ITALIC = "italic_text";
    public static String GRID = "grid";
    public static String HBOX = "hbox";
    CourseSiteGeneratorApp app;
    CourseView page;
    public CourseStyle(CourseSiteGeneratorApp initApp){
        app = initApp;
        page = (CourseView)((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getCourseView();  
    }
    
    public void initCourseStyle(){
        page.getvBox().getStyleClass().add(MAIN_PANE);
        page.getTopGridPane().getStyleClass().addAll(INNER_PANE,GRID);
        page.getLowvBox().getStyleClass().addAll(INNER_PANE,GRID);
        page.getMidvBox().getStyleClass().addAll(INNER_PANE,GRID);
        page.getLowGridPane().getStyleClass().addAll(INNER_PANE,GRID);
        page.getStyleSheetMessageLabel().getStyleClass().add(ITALIC);
        page.getMidvBoxDesc().getStyleClass().add(ITALIC);
        page.getSsHbox().getStyleClass().add(HBOX);
    }
    
}
