package ControllerPkg;

import ModelPkg.PkgEvents.GameEvent;
import ModelPkg.PkgItems.Items;
import ModelPkg.PlayerData;
import ObserverPkg.Observer;

import javax.swing.*;
import java.util.ArrayList;


public class PlayerDataController {

    private PlayerData playerData;

    /**
     * Constructeur du contrôleur des données du joueur
     */
    public PlayerDataController(){
        this.playerData = new PlayerData();
    }

    /**
     * Méthode pour diminuer la quantité de nourriture du joueur
     * @param price quantité de nourriture à supprimer
     */
    public void spendFood(int price){
        this.playerData.removeFood(price);
    }
//Delete?
    /**
     *
     * @return
     */
    public ArrayList<String> getConsumablesInventory() {
        return playerData.getConsumablesInventory();
    }
//Delete?
    /**
     *
     * @return
     */
    public ArrayList<String> getPermanentInventory() {
        return playerData.getConsumablesInventory();
    }
//TODO What to write here?
    /**
     *
     * @param observer
     */
    public void addObserver(Observer observer){
        this.playerData.addObserver(observer);
    }

    /**
     * Méthode pour ajouter un objet à l'inventaire
     * @param items objet que l'on souhaite ajouté à l'inventaire
     */
    public void addItemToInventory(Items items){
        this.playerData.addItemToInventory(items);
    }

    //TODO What to write here?
    /**
     *
     * @return
     */
    public ListModel<String> getConsumableInventoryDataModel(){
        DefaultListModel<String> listModel = new DefaultListModel();
        for(String v : this.playerData.getConsumablesInventory()){
            listModel.addElement(v);
        }

        return listModel;
    }
    //TODO What to write here?
    /**
     *
     * @return
     */
    public ListModel<String> getPermanentInventoryDataModel(){
        DefaultListModel<String> listModel = new DefaultListModel();
        for(String v : this.playerData.getPermanentInventory()){
            listModel.addElement(v);
        }

        return listModel;
    }

    /**
     * Méthode pour ajouter de la nourriture au joueur
     * @param value quantité de nourriture à ajouter
     */
    public void addFood(int value){
        this.playerData.addFood(value);
    }

    /**
     * Méthode pour avoir la quantité de nourriture du joueur
     * @return la quantité de nourriture
     */
    public int getFood(){
        return this.playerData.getFood();
    }

    /**
     * Méthode pour avoir le niveau actuel du joueur
     * @return le niveau du joueur
     */
    public int getLevel(){
        return this.playerData.getLevel();
    }

    /**
     * Méthode pour avoir le pointage du joueur
     * @return le pointage du joueur
     */
    public int getScore(){
        return this.playerData.getScore();
    }

    /**
     * Méthode pour avoir la population de la fourmilière
     * @return la population actuelle
     */
    public int getPopulation(){
        return this.playerData.getPopulation();
    }

    /**
     * Méthode pour avoir la quantité de fourmilier morts
     * @return le nombre de mort
     */
    public int getDead(){
        return this.playerData.getDead();
    }

    /**
     * Méthode pour ajouter un fourmilier
     */
    public void newBorn(){
        this.playerData.newBorn();
    }

    /**
     * Méthode pour augmenter le nombre de mort et diminué la population
     */
    public void newVictime()
    {
        this.playerData.death();
    }
    //Delete?
    /**
     *
     * @return
     */
    public boolean isSelectedItem(){
        return(this.playerData.isSelectInventory());
    }
    //Delete?
    /**
     *
     * @param select
     */
    public void setSelectedItem(boolean select){
        this.playerData.setSelectInventory(select);

    }

    /**
     * Méthode pour avoir l'événement en cours
     * @return l'événement en cours
     */
    public GameEvent getCurrentEvent(){
        return this.playerData.getCurrentEvent();
    }

    /**
     * Méthode pour modifier l'événement en cours
     * @param gameEvent le nouvel événement
     */
    public void setCurrentEvent(GameEvent gameEvent){
        this.playerData.setCurrentEvent(gameEvent);
    }

    /**
     * Méthode pour avoir la quantité de Karma du joueur
     * @return le karma du joueur
     */
    public int getKarma() {
        return this.playerData.getKarma();
    }

    /**
     * Méthode pour avoir la gravité du prochain événement
     * @return la gravité du prochain événement
     */
    public int getNextEventGravtity() {
        return this.playerData.getNextEventGravity();
    }

    /**
     * Méthode pour modifié le karma du joueur
     * @param number nouvelle quantité de karma
     */
    public void modifyKarma(int number) {
        this.playerData.modifyKarma(number);
    }

    /**
     * Méthode pour modifier la gravité du prochain événement
     * @param value gravité du prochain événement
     */
    public void setNextEventGravity(int value){
        this.playerData.setNextEventGravity(value);
    }

    /**
     * Méthode pour augmenter la gravité du prochain événemnet
     */
    public void increaseNextEventGravity(){
        this.playerData.increaseNextEventGravity();
    }

    /**
     * Méthode pour avoir la quantité de nourriture nécessaire pour passer au prochain niveau
     * @return la quantité de nourriture qu'il reste à ramasser
     */
    public int getNumberFoodToGo(){
       return(this.playerData.getNumberFoodToGo());
    }

    /**
     * Méthode pour avoir le numéro de la question du Chaman en cours
     * @return le numéro de la question
     */
    public int getQuestionNumber() {
        return this.playerData.getQuestionNumber();
    }

    /**
     * Méthode pour modifier le numéro de la question du Chaman
     * @param questionNumber nouveau numéro
     */
    public void setQuestionNumber(int questionNumber) {
        this.playerData.setQuestionNumber(questionNumber);
    }

    //TODO what to write here?
    /**
     *
     * @param index
     * @param activatedType
     */
    public void activateInstance(int index, int activatedType){
        this.playerData.activateInstance(index, activatedType);
    }

    /**
     * Méthode pour modifier le niveau du joueur
     * @param level nouveau niveau du joueur
     */
    public void setLevel(int level) {
        this.playerData.setLevel(level);
    }

    /**
     * Méthode pour savoir si le niveau est terminé
     * @return si le niveau est terminé ou non
     */
    public boolean isTheLevelFinish() {
        return this.playerData.isTheLevelFinish();
    }

    /**
     * Méthode pour modifier si le niveau est terminé
     * @param finish nouvelle valeur qui détermine si le niveau est terminé
     */
    public void setTheLevelFinish(boolean finish) {
        this.playerData.setTheLevelFinish(finish);
    }

    /**
     * Méthode pour savoir s'il est le temps d'afficher les questions du Chaman
     * @return s'il est le temps pour les questions du Chaman
     */
    public boolean isItTimeForChaman() {
        return this.playerData.isItTimeForChaman();
    }

    /**
     * Méthode pour modifier s'il est le temps pour le Chaman
     * @param isItTime nouvelle valeur qui détermine si c'est le temps pour le Chaman
     */
    public void setItTimeForChaman(boolean isItTime) {
        this.playerData.setItTimeForChaman(isItTime);
    }

    /**
     * Méthode pour avoir les statistiques modifiés des fourmiliers
     * @param statID identification de la statistique modifiée
     * @return la statistique modifiée
     */
    public int getStatMod(int statID){
        return this.playerData.getStatMod(statID);
    }

}