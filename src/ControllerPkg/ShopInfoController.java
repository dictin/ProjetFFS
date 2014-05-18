package ControllerPkg;

import ModelPkg.ShopItemInfoData;
import ObserverPkg.Observer;

public class ShopInfoController {

    ShopItemInfoData shopItemInfoData = new ShopItemInfoData();

    /**
     * Méthode pour avoir les information sur les objets dans le magasin
     * @return Les informations sur les objets
     */
    public ShopItemInfoData getShopItemInfoData() {
        return shopItemInfoData;
    }

    /**
     * Méthode pour avoir le nom de l'objet
     * @return le nom de l'objet
     */
    public String getName(){
        return this.shopItemInfoData.getName();
    }

    /**
     * Méthode pour avoir le coût de l'objet
     * @return le coût de l'objet
     */
    public int getCost(){
        return this.shopItemInfoData.getCost();
    }

    /**
     * Méthode pour avoir l'indentification de la statistique que l'objet modifie
     * @return
     */
    public int getStatID(){
        return this.shopItemInfoData.getModifiedStatID();
    }

    /**
     * Méthode pour avoir la valeur de la modification de l'objet
     * @return la valeur que l'objet modifie
     */
    public int getModValue(){
        return this.shopItemInfoData.getModifiedValue();
    }

    /**
     * Méthode pour savoir si l'objet est permanent ou non
     * @return si l'objet est permanent
     */
    public boolean isPermanent(){
        return this.shopItemInfoData.isPermanent();
    }
//TODO What to write here
    /**
     *
     * @param observer
     */
    public void addObserver(Observer observer){
        this.getShopItemInfoData().addObserver(observer);
    }
}
