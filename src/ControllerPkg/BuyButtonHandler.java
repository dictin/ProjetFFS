package ControllerPkg;

import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyButtonHandler implements ActionListener {

    MasterController masterController;
    JList<String> list;
    ItemController itemController;

    public BuyButtonHandler(JList<String> list, final MasterController controller){
        super();
        this.masterController = controller;
        this.itemController = masterController.getItemController();
        this.list = list;


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = this.list.getSelectedIndex();
        if (selectedIndex != -1){
            int playerFood = this.masterController.getPlayerDataController().getFood();
            int price = Items.values()[selectedIndex].getPrice();
            if (playerFood >= price){
                itemController.addItemToInventory(Items.values()[selectedIndex]);
                masterController.getPlayerDataController().spendFood(Items.values()[selectedIndex].getPrice());
            }else {
                JOptionPane.showMessageDialog(null, "Nourriture insuffisante");
            }
        }
    }
}
