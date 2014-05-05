package ModelPkg;

import ModelPkg.PkgItems.BoostEffect;
import ModelPkg.PkgItems.Items;
import ModelPkg.PkgItems.TempItemInstance;
import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerData implements Observable {
    private int food = 300;
    private int score = 0;
    private int niveau = 1;
    private int population = 0;
    private int dead = 0;
    private ArrayList<TempItemInstance> passiveInstances = new ArrayList<TempItemInstance>(); //Contient les effects des items innactifs de l'inventaire du joueur
    private ArrayList<String> consumablesInventory = new ArrayList<>();
    private ArrayList<String> permanentInventory = new ArrayList<>();
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    private static int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};

    private ArrayList<Observer> observers = new ArrayList<>();

    public PlayerData(){
    }

    public void addPassiveItem(TempItemInstance instance){
        this.passiveInstances.add(instance);

    }

    public void activateInstance(int index){
        this.addItemInstance(this.passiveInstances.get(index));
        this.passiveInstances.remove(index);
    }

    private void addItemInstance(TempItemInstance itemInstance){
        if (itemInstance.getDuration() < 0){
            this.permanentInstances.add(itemInstance);
        }else{
            this.tempItemInstances.add(itemInstance);
        }
    }

    public void tempItemTurn(){
        for(int i = 0; i < this.tempItemInstances.size(); i++){
            this.tempItemInstances.get(i).turn();
        }
    }

    public void cleanTempItemInstances(){
        ArrayList<TempItemInstance> toClean = new ArrayList<TempItemInstance>();
        Iterator<TempItemInstance> iterator = this.tempItemInstances.iterator();
        while(iterator.hasNext()){
            TempItemInstance instance = iterator.next();
            if (instance.getDuration() < 0){
                toClean.add(instance);
            }
        }
        this.tempItemInstances.removeAll(toClean);
    }

    public void activateInstances(){
        Iterator<TempItemInstance> iterator;
        iterator = this.tempItemInstances.iterator();
        while(iterator.hasNext()){
            iterator.next().activate();
        }
        iterator = this.permanentInstances.iterator();
        while(iterator.hasNext()){
            iterator.next().activate();
        }
    }

    public int getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public int getPopulation() {
        return population;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public void removeFood(int food){
        this.food -= food;
        if (this.food < 0){
            this.food =0;
        }
    }

    public void setScore(int score) {
        this.score = (this.food * this.niveau) - (2*this.dead);
    }

    public void newBorn(){
        this.population++;
    }

    public void death(){
        this.population--;
        this.dead++;
    }


    public static void addMod(int stat, int value){
        PlayerData.statModifiers[stat] += value;
    }

    public void clearStatMod(){
        for(int i = 0; i < this.statModifiers.length; i++){
            this.statModifiers[i] = 0;
        }
    }

    public int getStatMod(int stat){
        return this.statModifiers[stat];
    }

    public void addItemToInventory(Items item){
        TempItemInstance itemInstance;

        if (item.getEffect() instanceof BoostEffect){
            itemInstance = (((BoostEffect) item.getEffect()).getTempInstance());
            this.addPassiveItem(itemInstance);

            if (itemInstance.getDuration() < 0){
                this.permanentInventory.add(item.getName());
            }else{
                this.consumablesInventory.add(item.getName());
            }
        }

        this.updateObservers();

    }

    public ArrayList<String> getConsumablesInventory() {
        return consumablesInventory;
    }

    public ArrayList<String> getPermanentInventory() {
        return permanentInventory;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);

    }

    @Override
    public void updateObservers() {
        for(int i = 0; i < observers.size(); i++){
            observers.get(i).update();
        }

    }
}
