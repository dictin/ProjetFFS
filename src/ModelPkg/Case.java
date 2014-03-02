package ModelPkg;



import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Case {

    private Point position;
    private Animal occupant;
    private WildObject terrain;
    private ArrayList<Smell> smellArrayList = new ArrayList<Smell>();
    private ArrayList<Smell> sortedSmellArrayList = new ArrayList<Smell>();
    private Smell dominantSmell = null;

    public Case(Point location, Animal occupant , WildObject terrain){
        this.position = position;
        this.occupant = null;
        this.terrain = terrain;

    }

    public void addSmell(Smell smell){
        this.smellArrayList.add(smell);
        this.optimizeSmellArray();
        this.setStrongestSmell();
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

    public void setStrongestSmell(){
        this.dominantSmell = this.sortedSmellArrayList.get(0);
    }
}
