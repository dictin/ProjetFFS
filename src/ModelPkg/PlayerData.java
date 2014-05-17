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
    private ArrayList<TempItemInstance> passivePermInstances = new ArrayList<TempItemInstance>(); //Contient les effects des items innactifs de l'inventaire du joueur
    private ArrayList<TempItemInstance> passiveTempInstances = new ArrayList<TempItemInstance>(); //Contient les effects des items innactifs de l'inventaire du joueur
    private ArrayList<String> consumablesInventory = new ArrayList<>();
    private ArrayList<String> permanentInventory = new ArrayList<>();
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    private static int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};
    private static int[] numberFoodGoToNextLevel = new int[]{1000,2000,3000,4000};
    private int pickUpFood = 0;
    private int karma=0;
    private int nextEventGravity=1;
    private boolean isItTimeForChaman = false;



    private boolean isTheLevelFinish = false;

    public static final int TEMP_ITEM = 0;
    public static final int PERM_ITEM = 1;

    public static final int HP_STATID = 0;
    public static final int SPD_STATID = 1;
    public static final int ATK_STATID = 2;
    public static final int SMS_STATID = 3;
    public static final int SMT_STATID = 4;
    public static final int DEF_STATID = 5;
    public static final int END_STATID = 6;
    public static final int GBTQ_STATID = 7;

    private ArrayList<Observer> observers = new ArrayList<>();

    public PlayerData(){
    }

    public void addPassiveItem(TempItemInstance itemInstance){
        if (itemInstance.getDuration() < 0){
            this.passivePermInstances.add(itemInstance);
        }else{
            this.passiveTempInstances.add(itemInstance);
        }

    }

    public void activateInstance(int index, int activatedType){
        if (activatedType == PlayerData.PERM_ITEM){
            this.permanentInstances.add(this.passivePermInstances.get(index));
            this.passivePermInstances.remove(index);
            System.out.printf("Item permanent active    ");

        }else if (activatedType == PlayerData.TEMP_ITEM){
            this.permanentInstances.add(this.passiveTempInstances.get(index));
            this.passiveTempInstances.remove(index);
            System.out.printf("Item temporary active    ");
        }

        this.removeItemFromInventory(index, activatedType);

    }

    public void removeItemFromInventory(int index, int activatedType) {

        if (activatedType == PlayerData.TEMP_ITEM){
            this.consumablesInventory.remove(index);
        }else if (activatedType == PlayerData.PERM_ITEM){
            this.permanentInventory.remove(index);
        }

        this.updateObservers();
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

    public void activateInstancesForTurn(){
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

    public void endOfTurnCleanUp(){
        this.cleanTempItemInstances();
        this.clearStatMod();
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

    public void setLevel(int newLevel){
        this.level = newLevel;
    }
    public boolean isTheLevelFinish() {
        return isTheLevelFinish;
    }

    public void setTheLevelFinish(boolean isTheLevelFinish) {
        this.isTheLevelFinish = isTheLevelFinish;
    }

    public boolean isItTimeForChaman() {
        return isItTimeForChaman;
    }

    public void setItTimeForChaman(boolean isItTimeForChaman) {
        this.isItTimeForChaman = isItTimeForChaman;
    }
}
