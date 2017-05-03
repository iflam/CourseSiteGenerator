/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.Recitation;
import csg.data.RecitationData;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;

/**
 *
 * @author Itai
 */
public class UpdateRec_Transaction implements jTPS_Transaction{
    RecitationData recData;
    Recitation oldRec;
    Recitation newRec;
    ObservableList recitations;
    
    public UpdateRec_Transaction(RecitationData rD, Recitation oldRec, Recitation newRec){
        recData = rD;
        this.oldRec = oldRec;
        this.newRec = newRec;
        recitations = rD.getRecitations();
    }

    @Override
    public void doTransaction() {
        recData.removeRecitation(oldRec);
        recData.addRecitation(newRec);
    }

    @Override
    public void undoTransaction() {
        recData.removeRecitation(newRec);
        recData.addRecitation(oldRec);
    }
    
}
