/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ProjectData;
import csg.data.Team;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class UpdateTeam_Transaction implements jTPS_Transaction{
    public final String viewType = "projectTab";
    ProjectData data;
    Team t;
    Team ogTeam;
    
    public UpdateTeam_Transaction(ProjectData initData,Team ogTeam, Team initT){
        data = initData;
        t = initT;
        this.ogTeam = ogTeam;
    }
    @Override
    public String getViewType() {
        return viewType;
    }

    @Override
    public void doTransaction() {
        data.updateTeam(ogTeam, t);
    }

    @Override
    public void undoTransaction() {
        data.updateTeam(t, ogTeam);
    }
    
}
