package ModelPkg;

import ModelPkg.PkgItems.TempItemInstance;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerData {
    //TODO modifier le nombre de nourritures de départ selon la difficultée du jeu :)
    private int food = 300;
    // Que représente Score???
    //Dictin: une valeur numerique qui depend de la nourriture recupere, du nombre fourmilliers perdus et du niveau atteint. TODO determiner l'algorithme qui calcule le score
    private int score = 0;
    private int population = 0;
    private int nbLottery = 0;
    private int nbBooster = 0;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    private int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};









    public void addItemInstance(TempItemInstance itemInstance){
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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setInventory(ArrayList<Item> inventory) {
        int numberLottery = 0;
        int numberBooster = 0;
       Iterator<Item> list = this.inventory.iterator();
        while(list.hasNext()){
            Item actualItem = list.next();
            if(actualItem instanceof LotteryTicket){            //Verifie si le actual item est une instance de la classe "LotteryTicket"
                numberLottery ++;
            }
            else if( actualItem instanceof Booster){           //Voir ci-haut
                numberBooster ++;
            }
        }
        this.nbBooster = numberBooster;
        this.nbLottery = numberLottery;
        this.inventory = inventory;

        //TODO optimiser cette methode pour faciliter l'implementation d'une multitude d'items different. Je pensais un modele "fork/joint". (voir tutoriel sdz)
    }

    public void addMod(int stat, int value){
        this.statModifiers[stat] += value;
    }

    public void clearStatMod(){
        for(int i = 0; i < this.statModifiers.length; i++){
            this.statModifiers[i] = 0;
        }
    }

    public int getStatMod(int stat){
        return this.statModifiers[stat];
    }
}
