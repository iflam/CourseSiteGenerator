/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import javafx.collections.ObservableList;

/**
 *
 * @author Itai
 */
public class CourseData {
    CourseSiteGeneratorApp app;
    String subject;
    String number;
    String semester;
    String year;    
    String title;
    String iName;
    String iHome;
    String exDir;
    String tempDir;
    String bannerDir;
    String lFtDir;
    String rFtDir;
    String styleSheet;
    private ObservableList<SitePage> sitePages;
    
    public CourseData(CourseSiteGeneratorApp initApp){
        app = initApp;
    }
    
    public void addData(String subject, String number, String semester, String year, String title, String iName,
            String iHome, String exDir, String tempDir, String bannerDir, String lFtDir, String rFtDir, String styleSheet){
        this.subject = subject;
        this.number = number;
        this.semester = semester;
        this.year = year;
        this.title = title;
        this.iName = iName;
        this.iHome = iHome;
        this.exDir = exDir;
        this.tempDir = tempDir;
        this.bannerDir = bannerDir;
        this.lFtDir = lFtDir;
        this.rFtDir = rFtDir;
        this.styleSheet = styleSheet;
    }

    public void setSitePages(ObservableList<SitePage> sitePages) {
        this.sitePages = sitePages;
    }

    public ObservableList<SitePage> getSitePages() {
        return sitePages;
    }

    public String getStyleSheet(){
        return styleSheet;
    }
    public CourseSiteGeneratorApp getApp() {
        return app;
    }

    public String getSubject() {
        return subject;
    }

    public String getNumber() {
        return number;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getiName() {
        return iName;
    }

    public String getiHome() {
        return iHome;
    }

    public String getExDir() {
        return exDir;
    }

    public String getTempDir() {
        return tempDir;
    }

    public String getBannerDir() {
        return bannerDir;
    }

    public String getlFtDir() {
        return lFtDir;
    }

    public String getrFtDir() {
        return rFtDir;
    }

    public void setApp(CourseSiteGeneratorApp app) {
        this.app = app;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public void setiHome(String iHome) {
        this.iHome = iHome;
    }

    public void setExDir(String exDir) {
        this.exDir = exDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public void setBannerDir(String bannerDir) {
        this.bannerDir = bannerDir;
    }

    public void setlFtDir(String lFtDir) {
        this.lFtDir = lFtDir;
    }

    public void setrFtDir(String rFtDir) {
        this.rFtDir = rFtDir;
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }
    
    
    
}
