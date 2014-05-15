package ControllerPkg;

import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryActivateButtonHandler implements ActionListener {

    MasterController masterController;

    public InventoryActivateButtonHandler(MasterController masterController){
        this.masterController = masterController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(((JButton)e.getSource()).getText());
        if(this.masterController.getPlayerDataController().isSelectedItem()){
        }
        else{
        }
    }
}
