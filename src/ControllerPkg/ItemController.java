package ControllerPkg;

import ModelPkg.PkgItems.Items;

import java.util.ArrayList;

public class ItemController {

    MasterController masterController;
    ArrayList<Integer> objetBougthList = new ArrayList<Integer>();

    /**
     * Constructeur du controlleur pour les objets
     * @param masterController controller principal
     */
    public ItemController(MasterController masterController){
        this.masterController = masterController;

    }

    /**
     * Méthode pour avoir la liste des objets en vente au magasin.
     * @return un tableau avec le nom des objets
     */
    public String[] getItemList(){
        String[] itemNames = new String[Items.values().length];
        for(int i= 0; i < Items.values().length; i++){
            itemNames[i] = Items.values()[i].getName();
        }

        return itemNames;
    }

    /**
     * Méthode pour avoir un objet particulier dans la liste
     * @param i index de l'objet que l'on veut avoir
     * @return l'objet à l'index demandé
     */
    public Items getBougthItem(int i){
        Items item = Items.values()[i];
        return(item);
    }

    /**
     * Méthode pour ajouter l'index de l'objet à la liste d'objet acheté
     * @param i index de l'objet acheter
     */
    public void setObjetBougthList(int i) {
        this.objetBougthList.add(i);
    }

    /**
     * Méthode pour avoir un objet particulier dans la liste d'objet acheté
     * @param i index dans la liste de l'objet que l'on souhaite avoir
     * @return l'index de l'objet
     */
    public int getObjetBougth(int i){
        return objetBougthList.get(i);
    }

    /**
     * Méthode pour ajouter un objet à l'inventaire
     * @param items objet que l'on veut ajouté à l'inventaire
     */
    public void addItemToInventory(Items items) {
        this.masterController.getPlayerDataController().addItemToInventory(items);
    }
}
