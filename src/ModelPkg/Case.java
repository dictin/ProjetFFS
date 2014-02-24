package ModelPkg;



import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Case {

    private Point position;
    private Animal occupant;
    private WildObject terrain;
    private ArrayList<Smell> smellArrayList = new ArrayList<Smell>();

    public Case(Point location, Animal occupant , WildObject terrain){
        this.position = position;
        this.occupant = null;
        this.terrain = terrain;

    }

    public void addSmell(Smell smell){
        this.smellArrayList.add(smell);
        this.optimizeSmellArray();
    }

    public void dilluteSmell(){
        Iterator iterator = this.smellArrayList.iterator();

        while(iterator.hasNext()){
            (Smell)iterator.next().
        }
    }
}
