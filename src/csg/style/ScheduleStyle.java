/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import csg.CourseSiteGeneratorApp;
import csg.view.CourseSiteGeneratorWorkspace;
import csg.view.ScheduleView;

/**
 *
 * @author Itai
 */
public class ScheduleStyle {
    public static String MAIN_PANE = "outer_pane";
    public static String INNER_PANE = "inner_pane";
    public static String NOT_BOLD = "not_bold";
    public static String BUTTON = "non_gui_button";
    public static String GRID = "grid";
    public static String TEXT = "text_prompt";
    public static String HEADER_TEXT = "text_header";
    public static String HEADER_BUTTON = "button_header";
    CourseSiteGeneratorApp app;
    ScheduleView page;
    public ScheduleStyle(CourseSiteGeneratorApp initApp){
        app = initApp;
        page = (ScheduleView)((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).getScheduleView();  
    }
    
    public void initScheduleStyle(){
        page.getGUI().getStyleClass().addAll(MAIN_PANE,GRID);
        page.getTopGrid().getStyleClass().add(INNER_PANE);
        page.getLowGrid().getStyleClass().addAll(INNER_PANE, GRID);
        page.getsLabel().getStyleClass().addAll(HEADER_TEXT, GRID);
        page.getSihBox().getStyleClass().addAll(HEADER_TEXT,HEADER_BUTTON,GRID);
    }
    
}
