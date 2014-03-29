package ControllerPkg;

import ModelPkg.ShopItemInfoData;
import ObserverPkg.Observer;

public class ShopInfoController {

    ShopItemInfoData shopItemInfoData = new ShopItemInfoData();

    public ShopInfoController(){

    }

    public ShopItemInfoData getShopItemInfoData() {
        return shopItemInfoData;
    }

    public String getName(){
        return this.shopItemInfoData.getName();
    }

    public int getCost(){
        return this.shopItemInfoData.getCost();
    }

    public int getStatID(){
        return this.shopItemInfoData.getModifiedStatID();
    }

    public int getModValue(){
        return this.shopItemInfoData.getModifiedValue();
    }

    public boolean isPermanent(){
        return this.shopItemInfoData.isPermanent();
    }

    public void addObserver(Observer observer){
        this.getShopItemInfoData().addObserver(observer);
    }
}
