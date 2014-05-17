package ControllerPkg;


import ModelPkg.PkgItems.Items;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryListHandler implements ListSelectionListener {

    private MasterController masterController;
    private int selectedItemIndex = -1;


    public InventoryListHandler(MasterController masterController){
        this.masterController = masterController;
}


    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList<String>) e.getSource();
        selectedItemIndex = list.getSelectedIndex();
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}


