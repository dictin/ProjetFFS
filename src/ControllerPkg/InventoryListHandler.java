package ControllerPkg;


import ModelPkg.PkgItems.Items;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryListHandler implements ListSelectionListener {

    private MasterController masterController;
    private int selectedItemIndex = -1;

    /**
     * Constructeur du controller pour les objets du magasin
     * @param masterController Controller principal
     */
    public InventoryListHandler(MasterController masterController){
        this.masterController = masterController;
}

    /**
     * Pour gérer lorsqu'un objet est sélectionné dans le magasin
     * @param e source de l'objet clické
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList<String>) e.getSource();
        selectedItemIndex = list.getSelectedIndex();
    }

    /**
     * Méthode pour avoir l'index dans la liste de l'objet sélectionné
     * @return  le chiffre de l'index de l'objet
     */
    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}


