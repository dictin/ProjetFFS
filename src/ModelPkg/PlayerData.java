package ModelPkg;

import ModelPkg.PkgEvents.GameEvent;
import ModelPkg.PkgItems.BoostEffect;
import ModelPkg.PkgItems.Items;
import ModelPkg.PkgItems.LotteryEffects;
import ModelPkg.PkgItems.TempItemInstance;
import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerData implements Observable {
    private int food = 300;
    private int score = 0;
    private int level = 1;
    private int population = 0;
    private int dead = 0;
    private boolean selectInventory = false;
    private GameEvent currentEvent;
    private int questionNumber = 1;
    private ArrayList<TempItemInstance> passiveInstances = new ArrayList<TempItemInstance>(); //Contient les effects des items innactifs de l'inventaire du joueur
    private ArrayList<String> consumablesInventory = new ArrayList<>();
    private ArrayList<String> permanentInventory = new ArrayList<>();
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    private static int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};
    private static int[] numberFoodGoToNextLevel = new int[]{1000,2000,3000,4000};
    private int pickUpFood = 0;
    private int karma=0;
    private int nextEventGravity=1;

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

    public int getKarma() {
        return karma;
    }

    public int getNextEventGravity() {
        return nextEventGravity;
    }
    public void setNextEventGravity(int nextEventGravity) {
        this.nextEventGravity=nextEventGravity;
    }

    public void increaseNextEventGravity() {
        this.nextEventGravity+=1;
    }

    public void modifyKarma(int number) {
        this.karma+=number;
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

    public void addFood(int food) {
        this.food += food;
        this.pickUpFood += food;
        this.updateObservers();
    }

    public void removeFood(int food){
        this.food -= food;
        if (this.food < 0){
            this.food =0;
        }
        this.updateObservers();
    }

    public void setScore(int score) {
        this.score = (this.food * this.level) - (2*this.dead);
        this.updateObservers();
    }

    public void newBorn(){
        this.population++;
        this.updateObservers();
    }

    public void death(){
        this.population--;
        this.dead++;
        this.updateObservers();
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
            item.firstActivation();
            itemInstance = (((BoostEffect) item.getEffect()).getTempInstance());
            this.addPassiveItem(itemInstance);

            if (itemInstance.getDuration() < 0){
                this.permanentInventory.add(item.getName());
            }else{
                this.consumablesInventory.add(item.getName());
            }
        }else if (item.getEffect() instanceof LotteryEffects){
            this.addFood(item.getWinnings());
        }

        this.updateObservers();

    }

    public ArrayList<String> getConsumablesInventory() {
        return consumablesInventory;
    }

    public ArrayList<String> getPermanentInventory() {
        return permanentInventory;
    }

    public int getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }
    public int getNumberFoodToGo(){
        return (this.numberFoodGoToNextLevel[(this.level)-1]-this.pickUpFood);
    }

    public int getLevel() {
        return level;
    }

    public int getPopulation() {
        return population;
    }

    public int getDead() {
        return dead;
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

    public boolean isSelectInventory() {
        return selectInventory;
    }

    public void setSelectInventory(boolean selectInventory) {
        this.selectInventory = selectInventory;
    }

    public GameEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(GameEvent currentEvent) {
        this.currentEvent = currentEvent;
    }

    public int getPickUpFood() {
        return pickUpFood;
    }

    public void setPickUpFood(int pickUpFood) {
        this.pickUpFood = pickUpFood;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}
