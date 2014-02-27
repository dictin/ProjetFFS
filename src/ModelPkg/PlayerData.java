package ModelPkg;

import ModelPkg.Items.Booster;
import ModelPkg.Items.Item;
import ModelPkg.Items.LotteryTicket;

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
            if(actualItem.equals(LotteryTicket)){
                numberLottery ++;
            }
            else if( actualItem.equals(Booster)){
                numberBooster ++;
            }
        }
        this.nbBooster = numberBooster;
        this.nbLottery = numberLottery;
        this.inventory = inventory;
    }
}
