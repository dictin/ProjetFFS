package ControllerPkg;

import ModelPkg.PkgItems.Items;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryActivateButtonHandler implements ActionListener {

    PlayerDataController playerDataController;

    public InventoryActivateButtonHandler(PlayerDataController playerDataController){
        this.playerDataController = playerDataController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(playerDataController.isSelectedItem()){
            System.out.println("LOL!");
        }
        else{
            System.out.println("Noup!");
        }
    }
}
