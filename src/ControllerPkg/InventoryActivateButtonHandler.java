package ControllerPkg;

import ModelPkg.PkgItems.Items;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryActivateButtonHandler implements ActionListener {
    /**
     * Contrôleur principal
     */
    MasterController masterController;
    /**
     * Liste des objets du type sélectionné (permanent ou non)
     */
    JList<String> associatedList;
    /**
     * Position de l'objet sélectionné dans la liste
     */
    int indexOfItemToBuy;
    /**
     * Numéro du bouton
     */
    private int buttonType;

    /**
     * Constructeur du controller pour les boutons de l'inventaire
     * @param masterController le controller principal
     * @param associatedList liste qui est associé au bouton (soit les objets consommables, soit les objets permanents)
     * @param buttonType type du bouton clické (0 ou 1)
     */
    public InventoryActivateButtonHandler(MasterController masterController, JList<String> associatedList, int buttonType){
        this.masterController = masterController;
        this.associatedList = associatedList;
        this.buttonType = buttonType;

    }

    /**
     * Pour gérer lorsque les boutons sont clickés.
     * @param e source du bouton clické
     */
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
