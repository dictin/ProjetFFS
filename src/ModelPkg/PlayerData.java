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
    /**
     * Nourriture que possède le joueur
     */
    private int food = 300;
    /**
     * Score du joueur
     */
    private int score = 0;
    /**
     * Niveau du joueur
     */
    private int level = 1;
    /**
     * Population du joueur
     */
    private int population = 0;
    /**
     * Nombre de morts sous le joueur
     */
    private int dead = 0;
    /**
     * Un item de l'inventaire a-t-il été sélectionné
     */
    private boolean selectInventory = false;
    /**
     * Évènement courrant
     */
    private GameEvent currentEvent;
    /**
     * Question à laque;;e est rendue le joueur
     */
    private int questionNumber = 1;
    /**
     * Contient les effets des items permanents innactifs de l'inventaire du joueur
     */
    private ArrayList<TempItemInstance> passivePermInstances = new ArrayList<TempItemInstance>();
    /**
     * Contient les effects des items permanants innactifs de l'inventaire du joueur
     */
    private ArrayList<TempItemInstance> passiveTempInstances = new ArrayList<TempItemInstance>();
    /**
     * Liste des noms des items temporaires dans l'inventaire du joueur
     */
    private ArrayList<String> consumablesInventory = new ArrayList<>();
    /**
     * Liste des noms des items permanents dans l'inventaire du joueur
     */
    private ArrayList<String> permanentInventory = new ArrayList<>();
    /**
     * Contient les effects actifs des items permanants de l'inventaire du joueur
     */
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    /**
     * Contient les effects actifs des items temporaires de l'inventaire du joueur
     */
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    /**
     * Modificateurs des statistiques liés aux items
     */
    private static int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};
    /**
     * Nourriture à obtenir avant le prochain niveau
     */
    private static int[] numberFoodGoToNextLevel = new int[]{1000,2000,3000,4000};
    /**
     * Nourriture ramassée, en total, par le joueur
     */
    private int pickUpFood = 0;
    /**
     * Karma du joueur
     */
    private int karma=0;
    /**
     * Gravité du prochain évènement pour le joueur
     */
    private int nextEventGravity=1;
    /**
     * Faut-il poser une question
     */
    private boolean isItTimeForChaman = false;

    /**
     * Le niveau est-il terminé
     */
    private boolean isTheLevelFinished = false;

    /**
     * Identifiant pour les items temporaires
     */
    public static final int TEMP_ITEM = 0;
    /**
     * Identifiant pour les items temporaires
     */
    public static final int PERM_ITEM = 1;


    //Identifiants pour chaque statistiques-----------------------------------------------------------------------------

    public static final int HP_STATID = 0;
    public static final int SPD_STATID = 1;
    public static final int ATK_STATID = 2;
    public static final int SMS_STATID = 3;
    public static final int SMT_STATID = 4;
    public static final int DEF_STATID = 5;
    public static final int END_STATID = 6;
    public static final int GBTQ_STATID = 7;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Observateurs de la classe
     */
    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Méthode qui ajoute un objet temporaire à la bonne liste
     * @param itemInstance objet à ajouter à la bonne liste
     */
    public void addPassiveItem(TempItemInstance itemInstance){
        if (itemInstance.getDuration() < 0){
            this.passivePermInstances.add(itemInstance);
        }else{
            this.passiveTempInstances.add(itemInstance);
        }

    }

    /**
     * Méthode qui active les effets d'un objet
     * @param index position de l'objet dans la liste d'objet
     * @param activatedType type de l'objet
     */
    public void activateInstance(int index, int activatedType){
        if (activatedType == PlayerData.PERM_ITEM){
            this.permanentInstances.add(this.passivePermInstances.get(index));
            this.passivePermInstances.remove(index);

        }else if (activatedType == PlayerData.TEMP_ITEM){
            this.permanentInstances.add(this.passiveTempInstances.get(index));
            this.passiveTempInstances.remove(index);
        }

        this.removeItemFromInventory(index, activatedType);

    }

    /**
     * Méthode qui enlève un objet utilisé de l'inventaire
     * @param index position de l'objet dans la liste d'objet
     * @param activatedType type de l'objet
     */
    public void removeItemFromInventory(int index, int activatedType) {

        if (activatedType == PlayerData.TEMP_ITEM){
            this.consumablesInventory.remove(index);
        }else if (activatedType == PlayerData.PERM_ITEM){
            this.permanentInventory.remove(index);
        }

        this.updateObservers();
    }

    /**
     * Réduit d'un tour la durée restante des items
     */
    public void tempItemTurn(){
        for(int i = 0; i < this.tempItemInstances.size(); i++){
            this.tempItemInstances.get(i).turn();
        }
    }

    /**
     * Méthode qui retourne le karma du joueur
     * @return le karma du joueur
     */
    public int getKarma() {
        return karma;
    }

    /**
     * Méthode qui retourne la gravité du prochain événement
     * @return la gravité du prochain événement
     */
    public int getNextEventGravity() {
        return nextEventGravity;
    }

    /**
     * Méthode qui modifie la gravité du prochain événement
     * @param nextEventGravity  gravité du prochain événement
     */
    public void setNextEventGravity(int nextEventGravity) {
        this.nextEventGravity=nextEventGravity;
    }

    /**
     * Méthode qui augmente la gravité du prochain événement
     */
    public void increaseNextEventGravity() {
        this.nextEventGravity+=1;
    }

    /**
     * Méthode qui modifie le karma du joueur
     * @param number nombre de karma à rajouter
     */
    public void modifyKarma(int number) {
        this.karma+=number;
    }

    /**
     * Active les effets des instances pour le tour
     */
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

    /**
     * Méthode qui supprime toute les instance temporaire
     */
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

    /**
     * Méthode qui ajoute de la nourriture au joueur et qui diminue la quantité de nourriture à ramasser pour passer
     * au prochain niveau
     * @param food quantité de nourriture à rajouter
     */
    public void addFood(int food) {
        this.food += food;
        if((pickUpFood += food) > getLevel()*1000){
            this.pickUpFood = 1000;

        }
        else{
        this.pickUpFood += food;
        }
        this.updateObservers();
    }

    /**
     * Méthode qui enlève de la nourriture
     * @param food quantité de nourriture à enlever
     */
    public void removeFood(int food){
        this.food -= food;
        if (this.food < 0){
            this.food =0;
        }
        this.updateObservers();
    }


    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = (this.food * this.level) - (2*this.dead);
        this.updateObservers();
    }

    /**
     * Méthode qui augmente la population lorsqu'il y a un nouveau fourmilier de créer
     */
    public void newBorn(){
        this.population++;
        this.updateObservers();
    }

    /**
     * Méthode qui augmente le nombre de mort et qui diminue le nombre de population
     */
    public void death(){
        this.population--;
        this.dead++;
        this.updateObservers();
    }

    /**
     * Méthode qui ajoute une modification de statistiques
     * @param stat statistique à modifier
     * @param value valeur à rajouter à cette statistique
     */
    public static void addMod(int stat, int value){
        PlayerData.statModifiers[stat] += value;
    }

    /**
     * Méthode qui enlève toutes les modifications de statistique
     */
    public void clearStatMod(){
        for(int i = 0; i < this.statModifiers.length; i++){
            this.statModifiers[i] = 0;
        }
    }

    /**
     *
     */
    public void endOfTurnCleanUp(){
        this.cleanTempItemInstances();
        this.clearStatMod();
    }

    /**
     * Méthode qui retourne la modification d'un statistique
     * @param stat statistique qu'on veut avoir la modification
     * @return la modification de la statistique
     */
    public int getStatMod(int stat){
        return this.statModifiers[stat];
    }

    /**
     * Méthode qui ajoute un objet à l'inventaire
     * @param item objet acheté
     */
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
        }else if (item.getEffect() instanceof LotteryEffects){
            this.addFood(item.getWinnings());
        }

        this.updateObservers();

    }

    /**
     * Méthode qui retourne la liste d'objet consumables dans l'inventaire
     * @return la liste d'objet consumables dans l'inventaire
     */
    public ArrayList<String> getConsumablesInventory() {
        return consumablesInventory;
    }

    /**
     * Méthode qui retourne la liste d'objet permanent dans l'inventaire
     * @return la liste d'objet permanent dans l'inventaire
     */
    public ArrayList<String> getPermanentInventory() {
        return permanentInventory;
    }

    /**
     * Méthode qui retourne la quantité de nourriture du joueur
     * @return la quantité de nourriture du joueur
     */
    public int getFood() {
        return food;
    }

    /**
     * Méthode qui retourne le pointage du joueur
     * @return le pointage du joueur
     */
    public int getScore() {
        return score;
    }
    /**
     * Méthode qui retourne la quantité de nourriture nécessaire pour passer au prochain niveau
     * @return la quantité de nourriture nécessaire pour passer au prochain niveau
     */
    public int getNumberFoodToGo(){
        return (this.numberFoodGoToNextLevel[(this.level)-1]-this.pickUpFood);
    }
    /**
     * Méthode qui retourne le niveau du joueur
     * @return le niveau du joueur
     */
    public int getLevel() {
        return level;
    }
    /**
     * Méthode qui retourne la population de la fourmilière
     * @return la population de la fourmilière
     */
    public int getPopulation() {
        return population;
    }
    /**
     * Méthode qui retourne le nombre de mort
     * @return le nombre de mort
     */
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

    /**
     * Méthode qui vérifie si un objet a été selectionner dans l'inventaire
     * @return true si un objet a été sélectionné, false sinon
     */
    public boolean isSelectInventory() {
        return selectInventory;
    }

    /**
     * Méthode qui modifie la valeur de si le joueur a sélectionné un objet dans l'inventaire
     * @param selectInventory nouvelle valeur de si le joueur a sélectionné un objet dans l'inventaire
     */
    public void setSelectInventory(boolean selectInventory) {
        this.selectInventory = selectInventory;
    }

    /**
     * Méthode qui retourne l'événement en cours
     * @return l'événement en cours
     */
    public GameEvent getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Méthode qui modifie l'événement en cours
     * @param currentEvent nouvel événement en cours
     */
    public void setCurrentEvent(GameEvent currentEvent) {
        this.currentEvent = currentEvent;
    }

    /**
     * Méthode qui retourne le numéro de la question en cours
     * @return le numéro de la question
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Méthode qui modifie le numéro de la question en cours
     * @param questionNumber nouveau numéro de la question en cours
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * Méthode qui modifie le niveau en cours
     * @param newLevel nouveau niveau
     */
    public void setLevel(int newLevel){
        this.level = newLevel;
        this.pickUpFood = 0;
    }

    /**
     * Méthode qui retourne si le niveau est terminé ou non
     * @return si le niveau est terminé ou non
     */
    public boolean isTheLevelFinished() {
        return isTheLevelFinished;
    }

    /**
     * Méthode qui modifie si le niveau est terminé ou non
     * @param isTheLevelFinish nouvelle valeurs de si le niveau est terminé ou non
     */
    public void setTheLevelFinished(boolean isTheLevelFinish) {
        this.isTheLevelFinished = isTheLevelFinish;
    }

    /**
     * Méthode qui retourne si c'est le temps pour afficher les questions du chaman
     * @return si c'est le temps pour afficher les questions du chaman
     */
    public boolean isItTimeForChaman() {
        return isItTimeForChaman;
    }

    /**
     * Méthode qui modifie si c'est le temps pour afficher les questions du chaman
     * @param isItTimeForChaman nouvelle valeur pour si c'est le temps pour afficher les questions du chaman
     */
    public void setItTimeForChaman(boolean isItTimeForChaman) {
        this.isItTimeForChaman = isItTimeForChaman;
    }
}
