package ModelPkg;



import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Case implements Observable {

    private Point position;
    private Animal occupant;
    private WildObject terrain;
    private ArrayList<Smell> smellArrayList = new ArrayList<Smell>();
    private ArrayList<Smell> sortedSmellArrayList = new ArrayList<Smell>();

    private ArrayList<Observer> observers = new ArrayList<Observer>();


    public Case(Point location, Animal occupant , WildObject terrain){
        this.position = location;
        this.occupant = null;
        this.terrain = terrain;

    }

    public boolean caseContains(String thing){
        boolean answer=false;
        try{
        if (thing.equals("fourmilier")&&this.getOccupant() instanceof Fourmillier){
            answer=true;
        }
        else if (thing.equals("predator")&&this.getOccupant() instanceof Critter){
                answer=true;
        }
        else if (thing.equals("item")&&this.getWildObject().getType()==8){
            answer=true;
        }
        else if (thing.equals("food")&&this.getWildObject().getType()==7) {
            answer=true;
        }
    }
        catch(NullPointerException npe){
        }
        return answer;
    }

    public void addSmell(Smell smell){
        this.smellArrayList.add(smell);
        this.optimizeSmellArray();
    }

    private void optimizeSmellArray() {
        int maxSmellType = 0;
        int maxSmellStrength = 0;
        Iterator<Smell> iterator = this.smellArrayList.iterator();
        ArrayList<Smell> toRemove = new ArrayList<Smell>();

        this.sortedSmellArrayList.clear();

        while(iterator.hasNext()){
            Smell activeSmell = iterator.next();
            if (activeSmell.getIntensity() > maxSmellStrength){
                maxSmellType = activeSmell.getType();
                maxSmellStrength = activeSmell.getIntensity();
            }

        }

        iterator = this.smellArrayList.iterator(); //On reset l'iterator

        while(iterator.hasNext()){
            Smell activeSmell = iterator.next();
            if (activeSmell.getIntensity() != 0){
                if (activeSmell.getType() == maxSmellType){
                    this.sortedSmellArrayList.add(activeSmell);
                }
            }
            else{
                toRemove.add(activeSmell);
            }

        }

        this.smellArrayList.removeAll(toRemove);

    }

    public void dilluteSmell(){
        ArrayList<Smell> dilutedSmells = new ArrayList<Smell>();
        Smell tempSmell = null;
        for(int i = 0; i < this.smellArrayList.size(); i++){
            tempSmell = this.smellArrayList.get(i);
            tempSmell.diminish();
            dilutedSmells.add(tempSmell);

        }

        this.smellArrayList.clear();
        this.smellArrayList.addAll(dilutedSmells);
        this.optimizeSmellArray();
    }


    public Animal getOccupant() {
        return occupant;
    }

    public WildObject getWildObject(){
        return this.terrain;
    }

    public ArrayList<Smell> getSortedSmellArrayList() {
        return sortedSmellArrayList;
    }

    public void setOccupant(Animal occupant) {
        this.occupant = occupant;
        this.updateObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void updateObservers() {
        for(int i = 0; i< observers.size(); i++){
            this.observers.get(i).update();
        }
    }
}
