package ControllerPkg;

import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryActivateButtonHandler implements ActionListener {

    MasterController masterController;
    JList<String> associatedList;
    int indexOfItemToBuy;
    private int buttonType;

    public InventoryActivateButtonHandler(MasterController masterController, JList<String> associatedList, int buttonType){
        this.masterController = masterController;
        this.associatedList = associatedList;
        this.buttonType = buttonType;


    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (associatedList.getListSelectionListeners()[0] instanceof InventoryListHandler){
            indexOfItemToBuy = ((InventoryListHandler)associatedList.getListSelectionListeners()[0]).getSelectedItemIndex();
            if (indexOfItemToBuy != -1){
                this.masterController.getPlayerDataController().activateInstance(indexOfItemToBuy,buttonType);
            }

        }

    }
}
