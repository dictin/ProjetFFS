package ModelPkg;

import ModelPkg.PkgItems.TempItemInstance;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerData {
    //TODO modifier le nombre de nourritures de départ selon la difficultée du jeu :)
    private static int food = 300;
    // Que représente Score???
    //Dictin: une valeur numerique qui depend de la nourriture recupere over all, du nombre fourmilliers perdus et du niveau atteint. TODO determiner l'algorithme qui calcule le score
    private int score = 0;
    private int niveau = 1;
    private int population = 0;
    private int dead = 0;
    private ArrayList<TempItemInstance> passiveInstances = new ArrayList<TempItemInstance>(); //Contient les items innactifs de l'inventaire du joueur
    private ArrayList<TempItemInstance> permanentInstances = new ArrayList<TempItemInstance>();
    private ArrayList<TempItemInstance> tempItemInstances = new ArrayList<TempItemInstance>();
    private static int[] statModifiers = new int[]{0,0,0,0,0,0,0,0};

    public void addPassiveItem(TempItemInstance instance){
        this.passiveInstances.add(instance);

    }

    public void activateInstance(int index){
        this.addItemInstance(this.passiveInstances.get(index));
        this.passiveInstances.remove(index);
    }

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

    public static void addFood(int food) {
        PlayerData.food += food;
    }

    public void removeFood(int food){
        PlayerData.food -= food;
        if (PlayerData.food < 0){
            PlayerData.food =0;
        }
    }

    public void setScore(int score) {
        this.score = (PlayerData.food * this.niveau) - (2*this.dead);
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
}
