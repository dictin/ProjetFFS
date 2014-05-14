package ControllerPkg;

import ModelPkg.PkgEvents.GameEvent;
import ModelPkg.PkgItems.Items;
import ModelPkg.PlayerData;
import ObserverPkg.Observer;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-03-24.
 */
public class PlayerDataController {

    private PlayerData playerData;

    public PlayerDataController(){
        this.playerData = new PlayerData();
    }

    public void spendFood(int price){
        this.playerData.removeFood(price);
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

    public int getFood(){
        return this.playerData.getFood();
    }

    public int getLevel(){
        return this.playerData.getLevel();
    }

    public int getScore(){
        return this.playerData.getScore();
    }

    public int getPopulation(){
        return this.playerData.getPopulation();
    }

    public int getDead(){
        return this.playerData.getDead();
    }

    public void newBorn(){
        this.playerData.newBorn();
    }

    public void newVictime()
    {
        this.playerData.death();
    }
    public boolean isSelectedItem(){
        return(this.playerData.isSelectInventory());
    }
    public void setSelectedItem(boolean select){
        this.playerData.setSelectInventory(select);

    }

    public GameEvent getCurrentEvent(){
        return this.playerData.getCurrentEvent();
    }

    public void setCurrentEvent(GameEvent gameEvent){
        this.playerData.setCurrentEvent(gameEvent);
    }

    public int getKarma() {
        return this.playerData.getKarma();
    }

    public int getNextEventGravtity() {
        return this.playerData.getNextEventGravity();
    }

    public void modifyKarma(int number) {
        this.playerData.modifyKarma(number);
    }

    public void setNextEventGravity(int value){
        this.playerData.setNextEventGravity(value);
    }

    public void increaseNextEventGravity(){
        this.playerData.increaseNextEventGravity();
    }





}