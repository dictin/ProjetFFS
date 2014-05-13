package ControllerPkg;


import ModelPkg.PkgItems.Items;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryListHandler implements ListSelectionListener {
    MasterController masterController;
    JList<String> shopList;
    Items actualItem;

    public InventoryListHandler(MasterController masterController){
        this.masterController = masterController;
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        masterController.getItemController().getItemList();

        System.out.println("Click Click");
        JList<String> list = (JList<String>) e.getSource();
        int selectedIndex = list.getSelectedIndex();
        System.out.println("Index: "+ selectedIndex);

        if (selectedIndex >= 0){
            masterController.getPlayerDataController().setSelectedItem(true);
            System.out.println(this.masterController.getPlayerDataController().getConsumablesInventory().get(selectedIndex));
           //actualItem = Items.valueOf(this.masterController.getPlayerDataController().getConsumablesInventory().get(selectedIndex));
              Items.GENMOD_ATK_6.firstActivation();
//            System.out.println(actualItem.getName());
        }

        }
}


