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
public class AddRec_Transaction implements jTPS_Transaction{
    RecitationData recData;
    ObservableList recitations;
    Recitation r;

    public AddRec_Transaction(RecitationData initRecData, Recitation initR){
        recData = initRecData;
        recitations = initRecData.getRecitations();
        r = initR;
    }

    @Override
    public void doTransaction() {
       recData.addRecitation(r);
    }

    @Override
    public void undoTransaction(){
        recData.removeRecitation(r);
        
    }
    
}
