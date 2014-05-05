package ControllerPkg;

import ModelPkg.PkgItems.Items;
import ModelPkg.PlayerData;
import ObserverPkg.Observer;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-24.
 */
public class PlayerDataController {

    PlayerData playerData;

    public PlayerDataController(){
        this.playerData = new PlayerData();
    }

    public void spendFood(int price){

    }

    public ArrayList<String> getConsumablesInventory() {
        return playerData.getConsumablesInventory();
    }

    public ArrayList<String> getPermanentInventory() {
        return playerData.getConsumablesInventory();
    }

    public void addObserver(Observer observer){
        this.playerData.addObserver(observer);
    }

    public void addItemToInventory(Items items){
        this.playerData.addItemToInventory(items);
    }

    public ListModel<String> getConsumableInventoryDataModel(){
        DefaultListModel<String> listModel = new DefaultListModel();
        for(String v : this.playerData.getConsumablesInventory()){
            listModel.addElement(v);
        }

        return listModel;
    }

    public ListModel<String> getPermanentInventoryDataModel(){
        DefaultListModel<String> listModel = new DefaultListModel();
        for(String v : this.playerData.getPermanentInventory()){
            listModel.addElement(v);
        }

        return listModel;
    }

    public void addFood(int value){
        this.playerData.addFood(value);
    }




}