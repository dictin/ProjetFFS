package ModelPkg;

import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.util.ArrayList;

public class ShopItemInfoData implements Observable {

    private String name = "";
    private int cost = 0;
    private int modifiedStatID = 0;
    private int modifiedValue = 0;
    private boolean isPermanent = false;

    private ArrayList<Observer> observers = new ArrayList<Observer>();


    public ShopItemInfoData(){

    }

    public void changeValues(String name, int cost, int modifiedStatID, int modifiedValue, boolean isPermanent){
        this.name = name;
        this.cost = cost;
        this.modifiedStatID = modifiedStatID;
        this.modifiedValue = modifiedValue;
        this.isPermanent = isPermanent;
        this.updateObservers();

    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getModifiedStatID() {
        return modifiedStatID;
    }

    public int getModifiedValue() {
        return modifiedValue;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);

    }

    @Override
    public void updateObservers() {
        for (int i =0; i< this.observers.size(); i++){
            this.observers.get(i).update();
        }

    }
}
