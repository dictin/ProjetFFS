package ControllerPkg;

import ModelPkg.PkgItems.BoostEffect;
import ModelPkg.PkgItems.Items;
import ModelPkg.ShopItemInfoData;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ShopListHandler implements ListSelectionListener {
    /**
     * Information sur les objets du magasin
     */
    private ShopItemInfoData shopItemInfoData;

    /**
     * Constructeur du contrôleur de la liste d'objet du magasin
     * @param itemInfoData information sur les objets du magasin
     */
    public ShopListHandler(ShopItemInfoData itemInfoData){
        this.shopItemInfoData = itemInfoData;
    }

    /**
     * Méthode pour modifier les informations du magasin lorsque le joueur sélectionne un autre objet
     * @param e source du nouvel objet sélectionné
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList<String>) e.getSource();

        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex >= 0){
            Items selectedItem = Items.values()[selectedIndex];


        int statID;
        int statBoost;
        boolean isItemPermanent;
        if (selectedItem.getEffect() instanceof BoostEffect){
            statID = ((BoostEffect)selectedItem.getEffect()).getStatID();
            statBoost = ((BoostEffect)selectedItem.getEffect()).getBoostValue();
            if (((BoostEffect) selectedItem.getEffect()).getDuration() == -1){
                isItemPermanent = true;
            } else {
                isItemPermanent = false;
            }


        }else{
            statID = 0;
            statBoost = 0;
            isItemPermanent = false;
        }
        shopItemInfoData.changeValues(selectedItem.getName(), selectedItem.getPrice(), statID, statBoost, isItemPermanent);
        }
    }
}
