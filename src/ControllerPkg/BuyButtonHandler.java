package ControllerPkg;

import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyButtonHandler implements ActionListener {
    /**
     * le Contrôleur principal
     */
    MasterController masterController;
    /**
     * liste des objets dans le magasin
     */
    JList<String> list;
    /**
     * Contrôleur des objets du magasin
     */
    ItemController itemController;

    /**
     * Constructeur pour le controller du bouton d'achat dans le magasin
     * @param list liste des objets dans le magasin
     * @param controller le Contrôleur principal
     */
    public BuyButtonHandler(JList<String> list, final MasterController controller){
        super();
        this.masterController = controller;
        this.itemController = masterController.getItemController();
        this.list = list;


    }

    /**
     * Pour gérer lorsque le bouton d'achat est clické dans le magasin
     * @param e source
     */
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
